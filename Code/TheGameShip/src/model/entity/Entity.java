package model.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class Entity implements IEntity {
    private UUID id;
    private URI sprite;
    private final DoubleProperty x = new SimpleDoubleProperty();
        public double getX() {return x.get();}
        public void setX(double x) {this.x.set(x);}
        public DoubleProperty xProperty(){return x;}
    private final DoubleProperty y = new SimpleDoubleProperty();
        public double getY() {return y.get();}
        public void setY(double y) {this.y.set(y);}
        public DoubleProperty yProperty(){return y;}
    private String name;
    private String type;
    private double hitbox_radius;

    public Entity(String sprite, String nom, String type) throws URISyntaxException {
        this.id = UUID.randomUUID();
        this.sprite = new URI(sprite);
        this.name = nom;
        this.type = type;
        this.hitbox_radius = 10;
    }

    public Entity(String sprite, String nom, String type, double x, double y, double hitbox_radius) throws URISyntaxException {
        this(sprite,nom,type);
        setX(x);
        setY(y);
        this.hitbox_radius=hitbox_radius;
    }

    @Override
    public void printDecorationName() {
        System.out.print("Entity "); //DEBUG
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    //TODO:Equals trop simple, le refaire bien
    @Override
    public boolean equals(IEntity obj) {
        return super.equals(obj);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\nId : "+id.toString() + "\nNom : "+ name + "\nType : "+type + "\nSprite : "+sprite.toString() + "\nX : "+ x + "\nY : "+ y+ "\nRadius : "+ hitbox_radius;
    }

    public URI getSprite() {
        return sprite;
    }

    public void setSprite(URI sprite) {
        this.sprite = sprite;
    }

    public double getHitbox_radius() {
        return hitbox_radius;
    }
}