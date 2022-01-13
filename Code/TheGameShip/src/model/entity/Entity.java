package model.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class Entity implements IEntity {

    //Base
    private final UUID id;
        public UUID getId(){return id;}

    private final String name;
        public String getName() { return name; }

    private final String type;
        public String getType() { return type;}

    //Coordonate
    private final DoubleProperty x = new SimpleDoubleProperty();
        public double getX() {return x.get();}
        public void setX(double x) {this.x.set(x);}
        public DoubleProperty xProperty(){return x;}

    private final DoubleProperty y = new SimpleDoubleProperty();
        public double getY() { return y.get(); }
        public void setY(double y) { this.y.set(y); }
        public DoubleProperty yProperty() { return y; }

    //Life
    private final DoubleProperty hp = new SimpleDoubleProperty();
        public double getHp() { return hp.get(); }
        public void setHp(double hp) { this.hp.set(hp); }
        public DoubleProperty hpProperty() { return hp; }

    //Sprite
    private URI sprite;
        public URI getSprite() {
        return sprite;
    }
        public void setSprite(URI sprite) {
        this.sprite = sprite;
    }

    private final DoubleProperty hitbox_radius = new SimpleDoubleProperty();
        public double getHitbox_radius() { return hitbox_radius.get(); }
        public void setHitbox_radius(double hitbox_radius) { this.hitbox_radius.set(hitbox_radius); }
        public DoubleProperty hitbox_radiusProperty(){return hitbox_radius;}

    public Entity(String sprite, String nom, String type) {
        try {
            this.sprite = new URI(sprite);
        }
        catch(URISyntaxException err){
            err.printStackTrace();
        }
        this.id = UUID.randomUUID();
        this.name = nom;
        this.type = type;
        setX(0);
        setY(0);
        setHp(10);
        setHitbox_radius(10);
    }

    public Entity(String sprite, String nom, String type, double x, double y, double hitbox_radius, double hp) {
        this(sprite,nom,type);
        setX(x);
        setY(y);
        setHp(hp);
        setHitbox_radius(hitbox_radius);
    }

    //JAVA
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
    public String toString() {
        return "\nId : "+id.toString() + "\nNom : "+ name + "\nType : "+type + "\nSprite : "+sprite.toString() + "\nX : "+ x + "\nY : "+ y+ "\nRadius : "+ hitbox_radius;
    }
}
