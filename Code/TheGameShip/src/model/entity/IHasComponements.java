package model.entity;

public interface IHasComponements {
    void addComponement(Componement c);
    Componement getComponement(EComponementType type);
    boolean isTypeOf(EComponementType type);
}
