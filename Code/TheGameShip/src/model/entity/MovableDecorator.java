package model.entity;

public class MovableDecorator extends EntityDecorator {
    float speed;

    public MovableDecorator(Entity entity, float speed) {
        super(entity);
        this.speed = speed;
    }

    @Override
    public void draw() {
        decoratedEntity.draw();
        System.out.print("Movable "); //DEBUG
        // ...
    }

    public float getSpeed() { return speed; }

    public void setSpeed() { this.speed = speed; }
}
