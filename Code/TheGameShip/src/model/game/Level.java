package model.game;

import javafx.collections.ObservableSet;
import model.collider.Collider;
import model.collider.ColliderInfo;
import model.collider.ICollider;
import model.entity.*;
import model.entity.componement.*;
import model.move.IMove;
import model.move.Move;
import model.util.loop.Loop;
import model.util.loop.IObserver;
import model.util.input.ECommand;
import model.util.input.IInput;

import java.util.UUID;

//TODO: A la place faire une fabrique, qui se basera sur un fichier xml/json qui spécifie toute les caractéristiques

public class Level implements ILevel, IObserver {

    private Loop loop;

    private IInput input;

    private EntityManager entityManager;
    private final EntityFabric entityFabric;

    @Override public ObservableSet<IEntity> getEntityCollection() {
        return entityManager.getEntityCollection();
    }

    private IMove move;

    private ICollider collider;

    public int getScore() {
        Score score = (Score) entityManager.getPlayer().getComponement(EComponementType.Score);
        return score.getScore();
    }

    public Level(Loop loop, IInput input) {
        this.loop = loop;
        this.input = input;
        entityManager = new EntityManager();
        entityFabric = new EntityFabric();
        move = new Move();
        collider = new Collider(this);
    }

    @Override
    public void init() {
        //ENTITIES
        entityManager.addEntity(entityFabric.createPlayer("Vaisseau", "/Sprites/Spaceship.png", 70, 70, 1, 0, 250, 10, 10));
        //entityManager.add(new Entity("Obstacle1","file://test.jpg", EType.Obstacle,35,5,500,500));
        entityManager.addEntity(entityFabric.createEnnemy("Ennemy1", "/Sprites/Enemy.png",70, 70, 5, 650, 300));
    }

    @Override
    public void start() {
        loop.subscribe(this);
    }

    @Override
    public void exit() {
        //TODO: Unscribe les événement ajouter aux boucle (créer une méthode destroy dans boucle)
    }

    public void updateShoot(IEntity e, ECommand key) { //Ne vas pas, un tir a de la vie comme tout le monde
        ColliderInfo ci = move.move(e, collider, key);
        if (ci.IsCollision()) {
            entityManager.removeEntity(e);
            if (ci.getEntity().isTypeOf(EComponementType.Life)) {
                Life.cast(ci.getEntity()).decreaseHp();
            }
        }
    }

    public void createShoot(UUID id, Location l, ECommand key){
        if (loop.getTimer() >= 500) {
            entityManager.addEntity(entityFabric.createShoot(id, l, key));
            loop.resetTimer();
        }
    }
    //TODO: Au lieu de faire if/else, il faudrait trouver un autre moyen
    // Le switch est compliqué
    @Override
    public void update() {
        try {
            for (IEntity e : getEntityCollection()) {
                if (e.isTypeOf(EEntityType.Player)) {                              //Gestion mouvement du joueur
                    for (ECommand key : input.getKeyPressed()) {
                        move.move(e, collider, key);
                        if (key.equals(ECommand.SHOOT)) {
                            createShoot(e.getId(),Location.cast(e), ECommand.RIGHT);
                        }
                    }
                   //Life.cast(e).setDead(true); //DEBUG : Pour tester l'écran de game over
                }
                else if (e.isTypeOf(EEntityType.Shoot)) {                 //Gestion des tirs
                    UUID ownerId = Shoot.cast(e).getOwnerId();
                    for (IEntity e2 : getEntityCollection()) {
                        if (e2.getId().equals(ownerId)) {
                            if (e2.isTypeOf(EEntityType.Player)) { updateShoot(e, ECommand.RIGHT); }
                            else { updateShoot(e, ECommand.LEFT); }
                        }
                    }
                }
                else if (e.isTypeOf(EComponementType.Life)) {             //Gestion de la vie
                    //Si l'entité a de la vie
                    if (Life.cast(e).isDead()) { entityManager.removeEntity(e); }
                }
            }
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
}