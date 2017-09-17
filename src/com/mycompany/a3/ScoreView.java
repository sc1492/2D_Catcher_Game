package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by seanpc on 3/3/16.
 */
public class ScoreView extends Container implements Observer {
	private int gameScore, catsScooped, dogsScooped, catsRemaining, dogsRemaining;
	private Label gScore, cScooped, dScooped, cRemaining, dRemaining, sBoolean;
	private Container scoreView;
	private boolean sound;
	
//	public ScoreView(Observable obs) {
//		obs.addObserver(this);
//
//	}
//
	public ScoreView(Observable obs) {
		obs.addObserver(this);
		gameScore = 0;
		catsScooped = 0;
		dogsScooped = 0;
		catsRemaining = 2;
		dogsRemaining = 3;
		sound = false;
		this.scoreView = new Container(new BoxLayout(BoxLayout.X_AXIS));
		gScore = new Label("Game Score: " + gameScore);
		gScore.getAllStyles().setFgColor(ColorUtil.BLUE);
		gScore.getAllStyles().setPadding(0,0,0,8);
		
		cScooped = new Label("Cats scooped: " + catsScooped);
		cScooped.getAllStyles().setFgColor(ColorUtil.BLUE);
		cScooped.getAllStyles().setPadding(0,0,0,8);

		dScooped = new Label("Dogs scooped: " + dogsScooped);
		dScooped.getAllStyles().setFgColor(ColorUtil.BLUE);
		dScooped.getAllStyles().setPadding(0,0,0,8);

		cRemaining = new Label("Cats remaining: " + catsRemaining);
		cRemaining.getAllStyles().setFgColor(ColorUtil.BLUE);
		cRemaining.getAllStyles().setPadding(0,0,0,8);

		dRemaining = new Label("Dogs remaining: " + dogsRemaining);
		dRemaining.getAllStyles().setFgColor(ColorUtil.BLUE);
		dRemaining.getAllStyles().setPadding(0,0,0,8);
		
		
		sBoolean = new Label("Sound: off");
		sBoolean.getAllStyles().setFgColor(ColorUtil.BLUE);
		sBoolean.getAllStyles().setPadding(0,0,0,8);
		
		this.addComponent(gScore);
        this.addComponent(cScooped);
        this.addComponent(dScooped);
        this.addComponent(cRemaining);
        this.addComponent(dRemaining);
        this.addComponent(sBoolean);
	}
	
	@Override
    public void update (Observable o, Object arg) {
    	if (o instanceof GameWorld) {
    		GameWorld gWorld = (GameWorld)o;
    		gameScore = gWorld.getGameScore();
    		catsScooped = gWorld.getCatsScooped();
    		dogsScooped = gWorld.getDogsScooped();
    		catsRemaining = gWorld.getCatsRemaining();
    		dogsRemaining = gWorld.getDogsRemaining();
    		sound = gWorld.getSound();

    		gScore.setText("Game Score: " + gameScore);
    		cScooped.setText("Cats scooped: " + catsScooped);
    		dScooped.setText("Dogs scooped: " + dogsScooped);
    		cRemaining.setText("Cats remaining: " + catsRemaining);
    		dRemaining.setText("Dogs remaining: " + dogsRemaining);
    		if (sound == true) {
    			sBoolean.setText("Sound: on");
    		} else if (sound == false) {
    			sBoolean.setText("Sound: off");
    		}
    	}
    }
	
	
}
