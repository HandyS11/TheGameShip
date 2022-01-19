package model.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import java.util.Collection;
import java.util.HashSet;

public class EntityManager implements IHasEntityCollection {

    private final ObservableSet<IEntity> unUsedEntities;
        @Override public Collection<IEntity> getUnusedEntityCollection() { return unUsedEntities; }

    private final ObservableSet<IEntity> usedEntities;
        @Override public Collection<IEntity> getUsedEntityCollection() { return usedEntities; }

    public EntityManager() {
        this.unUsedEntities = FXCollections.observableSet(new HashSet<>());
        this.usedEntities = FXCollections.observableSet(new HashSet<>());
    }

    //TODO : fusionner les mêmes algo et ajouter un boolean isUsed pour trancher entre les 2 collections
    //TODO : Mauvais plan de faire deux listes, il faut en faire qu'une mais observable et la bind sur la vue
    //Used Entities
    public void setUsedEntity(String name){
        IEntity e = getUnUsedEntity(name);
        e.setVisible(true);
        getUsedEntityCollection().add(e);
    }

    public void setUsedEntity(IEntity e) {
        e.setVisible(true);
        getUsedEntityCollection().add(e);
    }

    public IEntity getUsedEntity(String name) {
        for (IEntity e: getUsedEntityCollection()) {
            if (name.equals(e.getName())) {
                return e;
            }
        }
        newError(name);
        return null;
    }

    public IEntity getUsedEntity(EType type) {
        for (IEntity e: getUsedEntityCollection()) {
            if (type.equals(e.getType())) {
                return e;
            }
        }
        newError(type);
        return null;
    }

    //UnUsed Entities
    public void add(IEntity e) {
        e.setVisible(false); //Au cas où
        getUnusedEntityCollection().add(e);
    }

    public void setUnUsedEntity(IEntity e) {
        e.setVisible(false);
        getUsedEntityCollection().remove(e);
        e.reset(); //Il met tous les autres paramètres par défauts sauf visible
        getUnusedEntityCollection().add(e);
    }

    public IEntity getUnUsedEntity(EType type) {
        for (IEntity e : getUnusedEntityCollection()) {
            if (type.equals(e.getType())) {
                getUnusedEntityCollection().remove(e);
                return e;
            }
        }
        newError(type);
        return null;
    }

    public IEntity getUnUsedEntity(String name) {
        for (IEntity e: getUnusedEntityCollection()) {
            if (name.equals(e.getName())) {
                return e;
            }
        }
        newError(name);
        return null;
    }

    //General
    //TODO : la refaire pour mettre toute les infos

    @Override
    public String toString() {
            /*
        System.out.println("Used Entity :\n");
        for (IEntity e : getUsedEntityCollection()) {
            System.out.println(e);
        }
        System.out.println("Un-used Entity :\n");
        for (IEntity e : getUnusedEntityCollection()) {
            System.out.println(e);
        }*/
        return super.toString();
    }

    public void newError(String name){
        System.err.println("Il n'y a pas d'entité de nom : \""+ name+"\"");
    }
    public void newError(EType type){
        System.err.println("Il n'y a pas d'entité de type : \""+ type.toString()+"\" disponible");
    }
}
