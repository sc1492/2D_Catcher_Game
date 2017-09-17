package com.mycompany.a3;

import java.util.ArrayList;

/**
 * Created by Sean on 3/14/2016.
 */
public class GameObjectCollection implements ICollection {
    private ArrayList<GameObject> gameObjects;
    public GameObjectCollection () {
        gameObjects = new ArrayList<>();
    }

    @Override
    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    @Override
    public IIterator getIterator() {
        return new GameObjectIterator();
    }

    public int size() {
        return gameObjects.size();
    }

    public void remove(int i) {
        gameObjects.remove(i);
    }

    public void clear() {
        gameObjects.clear();
    }

    public GameObject get(int i) {
        return gameObjects.get(i);
    }





    private class GameObjectIterator implements IIterator {

        private int index;

        public GameObjectIterator(){
            index = -1;
        }

        @Override
        public boolean hasNext() {
            if (gameObjects.size() <= 0) {
                return false;
            }
            if (index == gameObjects.size() - 1) {
                return false;
            }
            return true;
        }

        @Override
        public Object getNext() {
            if (gameObjects.size() <= index++) {
                index++;
            }
            return gameObjects.get(index);
        }

        @Override
        public void remove() {
            gameObjects.remove(index);
        }
    }
}
