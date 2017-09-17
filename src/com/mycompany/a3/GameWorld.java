package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.location.Location;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.util.MathUtil;


import java.util.Observable;
import java.util.Random;
/**
 * Created by Sean on 2/11/2016.
 */
public class GameWorld extends Observable{


    private int initCats, initDogs, catsScooped, dogsScooped, catsRemaining, dogsRemaining;
    private int gameScore;
    private int gwWidth, gwHeight;
    private Random rand;
    private boolean sound, gamePaused, soundCheckBox;
    private Sound scoopSwoop;
    private Sound catMeow;
    private Sound dogBark;
    private BGMusic bgMusic;

    private GameObjectCollection gameObjects;


    public void initLayout(int width, int height) {
        sound = false;
        gamePaused = false;
        soundCheckBox = false;
        initCats = 4;
        initDogs = 6;

        catsScooped = 0;
        dogsScooped = 0;


        catsRemaining = 0;
        dogsRemaining = 0;

        gameScore = 0;

        gwWidth = width;
        gwHeight = height;

        gameObjects = new GameObjectCollection();   
        rand = new Random();

        createCats();
        createDogs();
//        test();
        createNet();
        initSounds();
    }

    private void initSounds() {
        catMeow = new Sound("catMeow.wav");
        dogBark = new Sound("dogBark.wav");
        scoopSwoop = new Sound("swoop.wav");
        bgMusic = new BGMusic("schemingWeasel.wav");
    }
//    private void test() {
//        int size = rand.nextInt(30)+20;
//        // minX is size, maxX is gwWidth
//        // minY is size, maxY is gwHeight
//
//        float x = -5000;
//        float y =-5000;
//
//        int direction = 105;
//        int color = ColorUtil.rgb(0,0,0);
//        int speed = 5;
//        boolean isKitten = false;
//
//        gameObjects.add(new Cat(x,y,size,direction,color,speed, isKitten));
//        catsRemaining++;
//        dataChanged();
//         size = rand.nextInt(30)+20;
//        // minX is size, maxX is gwWidth
//        // minY is size, maxY is gwHeight
//
//        x = -5000;
//        y = -5000;
//
//
//        direction = (int) MathUtil.atan(90);
//        color = ColorUtil.rgb(0,0,0);
//        speed = 5;
//        gameObjects.add(new Cat(x,y,size,direction,color,speed, isKitten));
//        catsRemaining++;
//        dataChanged();
//    }

    public int getGameScore() {
    	return gameScore;
    }
    
    public int getCatsScooped() {
    	return catsScooped;
    }
    
    public int getDogsScooped() {
    	return dogsScooped;
    }
    
    public int getCatsRemaining() {
    	return catsRemaining;
    }
    
    public int getDogsRemaining() {
    	return dogsRemaining;
    }

    public GameObjectCollection getCollection() {return gameObjects; }

    private void createCats() {
        for (int i = 0; i < initCats; i++) {
           createCat();
        }
    }

    private void createCat() {
        if (catsRemaining < 30) {
            int size = rand.nextInt(30) + 20;
            // minX is size, maxX is gwWidth
            // minY is size, maxY is gwHeight
            float x = rand.nextInt((gwWidth - size / 2 - 4) - (size / 2 + 4)) + (size / 2 + 4);
            float y = rand.nextInt((gwHeight - size / 2 - 4) - (size / 2 + 4) * 2) + (size / 2 + 4);


            int direction = rand.nextInt(360);
            int color = ColorUtil.rgb(204, 204, 0);
            int speed = 4;
            boolean isKitten = false;
            gameObjects.add(new Cat(x, y, size, direction, color, speed, isKitten));
            catsRemaining++;
            dataChanged();
        } else {
            System.out.println("Can't create any more cats.");
        }
    }

    private void createDogs() {
        for (int i = 0; i < initDogs; i++) {
            createDog();
        }
    }

    private void createDog() {
        int size = rand.nextInt(30) + 20;
        // minX is size/2 + 4ize, maxX is gwWidth - size/2 - 4
        // minY is size/2 + 4, maxY is gwHeight - size/2 - 4

        float x = rand.nextInt((gwWidth - size/2 - 4) - (size/2 + 4)) + (size/2 + 4);
        float y = rand.nextInt((gwHeight - size/2 - 4) - (size/2 + 4) * 2) + (size/2 + 4);
        int direction = rand.nextInt(359);
        int color = ColorUtil.rgb(222,184,135);
        int speed = 6;
        gameObjects.add(new Dog(x,y,size,direction,color,speed));
        dogsRemaining++;
        dataChanged();
    }


    private void createNet() {
        Net net = new Net();
        net.setSize(100);
        net.setColor(ColorUtil.rgb(0,0,0)); // set net color to BLACK
        net.setLocation(new Point(rand.nextInt(1000), rand.nextInt(610)));
        gameObjects.add(net);
    }

    public void expandNet() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                if (gameObjects.get(i).getSize() + 50 <= 1000) {
                    gameObjects.get(i).setSize(gameObjects.get(i).getSize() + 50);
                    System.out.print("Net expanded. ");

                } else {
                    System.out.print("Can't expand. ");
                }
                System.out.println("Net Size: " + gameObjects.get(i).getSize());
            }

        }

    }

    public void contractNet() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                if (gameObjects.get(i).getSize() - 50 >= 50) {
                    gameObjects.get(i).setSize(gameObjects.get(i).getSize() - 50);
                    System.out.print("Net contracted: ");
                } else {
                    System.out.print("Can't contract. ");
                }
                System.out.println(" Net Size: " + gameObjects.get(i).getSize());
            }
        }
    }

    public void scoop() {
        if (getSound() == true) {
            scoopSwoop.play();
        }

        for (int j = 0; j < gameObjects.size(); j++) {
            if (gameObjects.get(j) instanceof Net) {
                float netX1 = gameObjects.get(j).getLocation().getX() - (gameObjects.get(j).getSize() / 2);
                float netX2 = gameObjects.get(j).getLocation().getX() + (gameObjects.get(j).getSize() / 2);
                float netY1 = gameObjects.get(j).getLocation().getY() - (gameObjects.get(j).getSize() / 2);
                float netY2 = gameObjects.get(j).getLocation().getY() + (gameObjects.get(j).getSize() / 2);
                boolean animalScooped = false;
                for (int i = 0; i < gameObjects.size(); i++) {
                    if (gameObjects.get(i) instanceof Dog) {
                        Dog dogTemp = (Dog) gameObjects.get(i);
                        if (dogTemp.getLocation().getX() >= netX1 && dogTemp.getLocation().getX() <= netX2) {
                            if (dogTemp.getLocation().getY() >= netY1 && dogTemp.getLocation().getY() <= netY2) {
                                int dogPoints = 10 - dogTemp.getScratches();
                                gameScore = gameScore + dogPoints;
                                dogsScooped++;
                                dogsRemaining--;
                                gameObjects.remove(i);
                                System.out.println("DOG SCOOPED!");
                                animalScooped = true;
                                dataChanged();
                                if (dogsRemaining == 0) {
                                    GameOver dialog = new GameOver();
                                }
                            }
                        }
                    } else if (gameObjects.get(i) instanceof Cat) {
                        Cat catTemp = (Cat) gameObjects.get(i);
                        if (catTemp.getLocation().getX() >= netX1 && catTemp.getLocation().getX() <= netX2) {
                            if (catTemp.getLocation().getY() >= netY1 && catTemp.getLocation().getY() <= netY2) {
                                gameScore = gameScore - 10;
                                catsScooped++;
                                catsRemaining--;
                                gameObjects.remove(i);
                                System.out.println("CAT SCOOPED!");
                                animalScooped = true;
                                dataChanged();
                            }
                        }
                    }
                }
                if(!animalScooped){
                    System.out.println("Didn't scoop anything!");
                }
            }
        }
//        boolean scoopSuccess = false;
//        IIterator gameObjectItr = gameObjects.getIterator();
//        Object tempObj;
//        while (gameObjectItr.hasNext()) {
//            tempObj = gameObjectItr.getNext();
//            if (tempObj instanceof Net) {
//                Net net = (Net) tempObj;
//                float netX1 = net.getLocation().getX() - (net.getSize() / 2);
//                float netX2 = net.getLocation().getX() + (net.getSize() / 2);
//                float netY1 = net.getLocation().getY() - (net.getSize() / 2);
//                float netY2 = net.getLocation().getY() + (net.getSize() / 2);
//
//                gameObjectItr = gameObjects.getIterator();
//                while (gameObjectItr.hasNext()) {
//                    Object tempAnimal = gameObjectItr.getNext();
//                    if (tempAnimal instanceof Dog) {
//                        Dog dogTemp = (Dog) tempAnimal;
//                        if (dogTemp.getLocation().getX() >= netX1
//                                && dogTemp.getLocation().getX() <= netX2
//                                && dogTemp.getLocation().getY() >= netY1
//                                && dogTemp.getLocation().getY() <= netY2) {
//                            scoopSuccess = true;
//                            gameObjectItr.remove();
//                            int dogPoints = 10 - dogTemp.getScratches();
//                            gameScore = gameScore + dogPoints;
//                            dogsScooped++;
//                            dogsRemaining--;
//                            System.out.println("DOG SCOOPED!");
//                            dataChanged();
//                            if (dogsRemaining == 0) {
//                                GameOver dialog = new GameOver();
//                            }
//                        }
//                    } else if (tempAnimal instanceof Cat) {
//                        Cat catTemp = (Cat) tempAnimal;
//                        if (catTemp.getLocation().getX() >= netX1
//                                && catTemp.getLocation().getX() <= netX2
//                                && catTemp.getLocation().getY() >= netY1
//                                && catTemp.getLocation().getY() <= netY2) {
//                            scoopSuccess = true;
//                            gameObjectItr.remove();
//                            gameScore = gameScore - 10;
//                            catsScooped++;
//                            catsRemaining--;
//                            System.out.println("CAT SCOOPED!");
//                            dataChanged();
//                        }
//                    } else {
//                    }
//                }
//                if (scoopSuccess == false) {
//                    System.out.println("Didn't scoop anything!");
//                }
//
//            }
//        }
    }

    public class GameOver extends Form {
        public GameOver () {
            this.setTitle("Game Over");
            Boolean gameOver = Dialog.show("Game Over!", "Final Score: " + gameScore, "Exit", null);
            if (gameOver) {
                Display.getInstance().exitApplication();
            }
            this.show();
        }
    }
    public void moveNetRight() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                if (gameObjects.get(i).getLocation().getX() + 50 <= 1000) {
                    ((Net) gameObjects.get(i)).moveRight(50);
                    System.out.print("Net moved right. ");
                } else {
                    System.out.print("Can't move right. ");
                }
                System.out.println("Net Location: " + gameObjects.get(i).getLocation().getX() + "," + gameObjects.get(i).getLocation().getY());
            }
        }
    }

    public void moveNetLeft(){
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                if (gameObjects.get(i).getLocation().getX() - 50 >= 0) {
                    ((Net) gameObjects.get(i)).moveLeft(50);
                    System.out.print("Net moved left. ");
                } else {
                    System.out.print("Can't move left. ");
                }
                System.out.println("Net Location: " + gameObjects.get(i).getLocation().getX() + "," + gameObjects.get(i).getLocation().getY());
            }
        }
    }

    public void moveNetUp() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                if (gameObjects.get(i).getLocation().getY() + 50 <= 610) {
                    ((Net) gameObjects.get(i)).moveUp(50);
                    System.out.print("Net moved up. ");
                } else {
                    System.out.print("Can't move up. ");
                }
                System.out.println("Net Location: " + gameObjects.get(i).getLocation().getX() + "," + gameObjects.get(i).getLocation().getY());
            }
        }
    }

    public void moveNetDown() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                if (gameObjects.get(i).getLocation().getY() - 50 >= 0) {
                    ((Net) gameObjects.get(i)).moveDown(50);
                    System.out.print("Net moved down. ");
                } else {
                    System.out.print("Can't move down. ");
                }
                System.out.println("Net Location: " + gameObjects.get(i).getLocation().getX() + "," + gameObjects.get(i).getLocation().getY());
            }
        }
    }

    public void netTeleportToCat() {
        boolean foundCat;
        if (catsRemaining > 0) {
            foundCat = true;
        } else {
            foundCat = false;
        }
        while (foundCat == true) {
            int index = rand.nextInt(gameObjects.size());
            if (gameObjects.get(index) instanceof Cat) {
                foundCat = false;
                for (int i = 0; i < gameObjects.size(); i++) {
                    if (gameObjects.get(i) instanceof Net) {
                        ((Net) gameObjects.get(i)).jumpToCat((Cat) gameObjects.get(index));
                        System.out.print("Net moved to a cat. ");
                        System.out.println("Net Location: " + gameObjects.get(i).getLocation().getX() + "," + gameObjects.get(i).getLocation().getY());
                    }
                }
                System.out.println("Cat Location: " + gameObjects.get(index).getLocation().getX() + "," + gameObjects.get(index).getLocation().getY());
            }
        }
        if (foundCat == false) {
            System.out.println("There aren't any cats in the Game World.");
        }
    }

    public void netTeleportToDog(){
        boolean foundDog = false;
        while (foundDog == false) {
            int index = rand.nextInt(gameObjects.size());
            if (gameObjects.get(index) instanceof Dog) {
                foundDog = true;
                for (int i = 0; i < gameObjects.size(); i++) {
                    if (gameObjects.get(i) instanceof Net) {
                        ((Net) gameObjects.get(i)).jumpToDog((Dog) gameObjects.get(index));
                        System.out.print("Net moved to a dog. ");
                        System.out.println("Net Location: " + gameObjects.get(i).getLocation().getX() +  "," + gameObjects.get(i).getLocation().getY());
                    }
                }
                System.out.println("Dog Location: " + gameObjects.get(index).getLocation().getX() + "," + gameObjects.get(index).getLocation().getY());
            }
        }
    }


    public void catCollision() {
        IIterator gameElements = gameObjects.getIterator();
        int catCounter = 0;
        while (gameElements.hasNext()) {
            if (gameElements.getNext() instanceof  Cat) {
                catCounter++;
            }
        }
        if (catCounter >=2) {
            createCat();
            System.out.println("New cat added.");
            dataChanged();
        } else if (catCounter < 2) {
            System.out.println("There's less than two cats left in the Game World.");
        }
//        for (int i = 0; i < gameObjects.size(); i++) {
//            if (gameObjects.get(i) instanceof Cat) {
//                catCounter++;       // Keeps track to see if at least two cats exist
//            }
//        }
//
//        if (catCounter >= 2) {
//            createCat();
//            System.out.println("New cat added");
//        } else if (catCounter < 2){
//            System.out.println("There's less than two cats in the Game World.");
//        }
        
    }

    public void animalFight() {
        if (getSound() == true) {
            dogBark.play();
        }
    }

    public void dogHeal() {
        IIterator dogItr = gameObjects.getIterator();
        Object tempObj;

        while(dogItr.hasNext()) {
            tempObj = dogItr.getNext();
            if (tempObj instanceof Dog && ((Dog) tempObj).isSelected()) {
                Dog selectedDog = (Dog) tempObj;
                selectedDog.heal();

                System.out.println("Dog healed!");
            }
        }
    }

    public void gameClockTick(int elapsedTime, int mapWidth, int mapHeight) {
        gwWidth = mapWidth;
        gwHeight = mapHeight;
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Animal) {
                ((Animal) gameObjects.get(i)).move(elapsedTime, mapWidth, mapHeight);
                checkCollision();
            }
        }
        dataChanged();
    }

    private void checkCollision() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if(gameObjects.get(i)instanceof Animal) {
                Animal animalA = (Animal) gameObjects.get(i);
                for (int j = 0; j < gameObjects.size(); j++) {
                    if(i!=j && gameObjects.get(j)instanceof Animal) {
                        Animal animalB = (Animal) gameObjects.get(j);
                        if(animalA.collidesWith(animalB)){
                            animalA.handleCollision(animalB, this);
//                            System.out.println("dsa");
//                            if (animalA.handleCollision(animalB, this)) {
//                                createKitten();
//                            }
                        } else {
                        }

                    }
                }
            }
        }
    }

    public void createKitten() {
        if (catsRemaining < 30) {
            if (getSound() == true) {
                catMeow.play();
            }
            int size = rand.nextInt(30) + 20;
            // minX is size, maxX is gwWidth
            // minY is size, maxY is gwHeight
            float x = rand.nextInt((gwWidth - size / 2 - 4) - (size / 2 + 4)) + (size / 2 + 4);
            float y = rand.nextInt((gwHeight - size / 2 - 4) - (size / 2 + 4) * 2) + (size / 2 + 4);
            int direction = rand.nextInt(359);
            int color = ColorUtil.rgb(188,143,143);
            int speed = 3;
            boolean isKitten = true;
            gameObjects.add(new Cat(x, y, size, direction, color, speed, isKitten));
            catsRemaining++;
            System.out.println("Kitten created!");
            dataChanged();
        }
    }

    public int getGwHeight() {
        return gwHeight;
    }

    public void setGwHeight(int gwHeight) {
        this.gwHeight = gwHeight;
    }


    public int getGwWidth() {
        return gwWidth;
    }

    public void setGwWidth(int gwWidth) {
        this.gwWidth = gwWidth;
    }

    public void printValues() {
        System.out.println("Current score: " + gameScore);
        System.out.println("Number of dogs scooped: " + dogsScooped);
        System.out.println("Number of cats scooped: " + catsScooped);
        System.out.println("Number of cats remaining: " + catsRemaining);
        System.out.println("Number of dogs remaining: " + dogsRemaining);
    }

    public void printMap() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Net) {
                // Prints Net information
                System.out.println(gameObjects.get(i));
            }

            if (gameObjects.get(i) instanceof Dog) {
                // Prints Dog information
                System.out.println(gameObjects.get(i));

            }

            if (gameObjects.get(i) instanceof Cat) {
                // Prints Cat information
                System.out.println(gameObjects.get(i));
            }
        }
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void soundOn() {
        sound = true;
        dataChanged();
        bgMusic.play();
    }
    
    public void soundOff() {
        sound = false;
        dataChanged();
        bgMusic.pause();
    }

    // unused
//    public void soundToggle() {
//    	if (sound == false) {
//    		soundOn();
//            System.out.println("sound on");
//        } else if (sound == true) {
//    		soundOff();
//            System.out.println("sound off");
//        }
//    	dataChanged();
//    }

    public boolean getSoundCheckBox() {
        return soundCheckBox;
    }

    public void setSoundCheckBox(boolean soundCheckBox) {
        this.soundCheckBox = soundCheckBox;
    }

    public void toggleSoundCheckBox() {
        if (soundCheckBox == false) {
            soundCheckBox = true;
            System.out.println("Sound checked!");
        } else if(soundCheckBox == true) {
            soundCheckBox = false;
            System.out.println("Sound unchecked!");
        }
    }

    public boolean getSound() {
    	return sound;
    }

    public void setSound(boolean soundCheck) {
        this.sound = soundCheck;
    }
    
    public void dataChanged() {
    	setChanged();
    	notifyObservers();
    }

    public void quit() {
    	boolean userInput = Dialog.show("Confirmation", "Are you sure you want to quit?", "YES", "NO");
    	if(userInput){
    		System.exit(0);
    	}
    }

    public GameObjectCollection getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(GameObjectCollection gameObjects) {
        this.gameObjects = gameObjects;
    }

    public IIterator iterator() {
        IIterator iterator = gameObjects.getIterator();
        return iterator;
    }

}
