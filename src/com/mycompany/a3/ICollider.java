package com.mycompany.a3;


public interface ICollider {

    boolean collidesWith(ICollider object);

    void handleCollision(ICollider object, GameWorld gameWorld);
}
