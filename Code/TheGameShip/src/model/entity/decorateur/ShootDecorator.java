package model.entity.decorateur;

import model.entity.IEntity;

import java.util.UUID;

public class ShootDecorator extends EntityDecorator {
    UUID OwnerId;

    public ShootDecorator(IEntity IEntity, UUID OwnerID) {
        super(IEntity);
        this.OwnerId = OwnerID;
    }

    @Override
    public void printDecorationName() {
        entity.printDecorationName();
        System.out.print("Shoot "); //DEBUG
        // ...
    }

    public UUID getOwnerId() { return OwnerId; }
    public void setOwnerId() { this.OwnerId = OwnerId; }
}