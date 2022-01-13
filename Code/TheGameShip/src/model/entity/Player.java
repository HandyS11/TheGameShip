package model.entity;

public class Player extends Entity implements IHasLife, IMovable {

    //LIFE
    double hp;
        @Override public double getHp() { return hp; }
        @Override public void setHp(double hp) {
        this.hp = hp;
    }

    boolean isDead = false;
        @Override public boolean isDead() {
        return isDead;
    }
        @Override public void setDead(boolean dead) {
        isDead = dead;
    }

    //Movable
    private float speedX;
        @Override public float getSpeedX() {
            return speedX;
        }
        @Override public void setSpeedX(float speedX) {
            this.speedX = speedX;
        }

    private float speedY;
        @Override public float getSpeedY() {
            return speedY;
        }
        @Override public void setSpeedY(float speedY) {
            this.speedY = speedY;
        }

    public Player(String sprite, String nom, double x, double y, double hitbox_radius) {
        super(sprite, nom, "Joueur", x, y, hitbox_radius, 5);
        this.speedX = 5;
        this.speedY = 10;
    }

    public Player(String sprite, String nom, double x, double y, double hitbox_radius, double hp, float speedX, float speedY) {
        this(sprite, nom, x, y, hitbox_radius);
        this.hp = hp;
        this.speedX = speedX;
        this.speedY = speedY;
    }

}