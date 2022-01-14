package view;

import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import launch.Launcher;
import model.GameManager;
import model.entity.IEntity;
import model.entity.Type;

public class MainWindow {

    @FXML
    private Pane pane;

    private GameManager gameManager;

    public void initialize() {

        gameManager = new GameManager();

        gameManager.getSetEntity().addListener((SetChangeListener<IEntity>) change -> {
            if (change.wasAdded()) {
                addEntity(change.getElementAdded());
            }
        });
        gameManager.init();
        gameManager.start();

        Launcher.getStage().setOnCloseRequest(e -> gameManager.exit());

    }

    public void addEntity(IEntity e) {
        Color color;
        switch(e.getType()){
            case Ennemy -> color=Color.RED;
            case Obstacle -> color=Color.GRAY;
            default -> color=Color.BLACK;
        }

        Circle c = new Circle(e.getHitbox_radius(),color);
        c.centerXProperty().bind(e.xProperty());
        c.centerYProperty().bind(e.yProperty());
        c.radiusProperty().bind(e.hitbox_radiusProperty());
        c.visibleProperty().bind(e.getVisibleBooleanProperty());
        pane.getChildren().add(c);
    }
}

//TODO: Code + Docs + Video (une demo vidéo d'1 min max) + Preuve de compétence (avec la feuille de compétence en expliquant)
