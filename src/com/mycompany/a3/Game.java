package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Commands.*;

/**
 * Created by Sean on 2/11/2016.
 */
public class Game extends Form implements Runnable {

    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;
    private Container scoreView;
    private Container bottomContainer, leftContainer, rightContainer;
    private Toolbar toolbar;
    private TextField myTF;
    private Button e, u, l, o, c, d, r, a, s, k, f, t, x, p, h;
    private UITimer timer;
    private int mapWidth, mapHeight, timerSpeed;
    private Boolean gameRunning;

    /** Constructs a new Game. */
    public Game() {
        gw = new GameWorld();
        mv = new MapView(gw);
        sv = new ScoreView(gw);
        gw.addObserver(mv);
        gw.addObserver(sv);


        timerSpeed = 30;
        timer = new UITimer(this);
        gameRunning = true;
        toggleGame();
        this.setLayout(new BorderLayout());

        // ScoreView
        scoreView = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        scoreView.addComponent(sv);
        this.addComponent(BorderLayout.NORTH, scoreView);


        // MapView
        this.addComponent(BorderLayout.CENTER, mv);


        // Top Bar
        toolbar = new Toolbar();
        this.setToolBar(toolbar);
        toolbar.setTitle("Dog Catcher Game");

        // Sound Check Box
        CheckBox soundCheckBox = new CheckBox("Sound");
        SoundCommand sound = new SoundCommand(gw);
        soundCheckBox.setCommand(sound);
        soundCheckBox.getAllStyles().setBgTransparency(255);
        soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        sound.putClientProperty("SideComponent", soundCheckBox);


        // Side Menu commands
        AboutCommand about = new AboutCommand();
        ExitCommand exit = new ExitCommand(gw);
        HelpMenuCommand helpMenu = new HelpMenuCommand();
        ScoopCommand scoop = new ScoopCommand(gw);


        // Adding to Top Bar
        toolbar.addCommandToSideMenu(about);
        toolbar.addCommandToSideMenu(exit);
        toolbar.addCommandToRightBar(helpMenu);
        toolbar.addCommandToSideMenu(scoop);
        toolbar.addComponentToSideMenu(soundCheckBox);

        // Exit command
        x = new Button("Exit");
        addKeyListener('x', exit);
        x.setCommand(exit);


        // Left side buttons
        leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        this.addComponent(BorderLayout.WEST, leftContainer);
        e = new Button("Expand");
        e.getUnselectedStyle().setBgTransparency(255);
        e.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        e.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        e.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        e.getDisabledStyle().setBgTransparency(255);
        e.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        u = new Button("Up");
        u.getUnselectedStyle().setBgTransparency(255);
        u.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        u.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        u.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        u.getDisabledStyle().setBgTransparency(255);
        u.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        l = new Button("Left");
        l.getUnselectedStyle().setBgTransparency(255);
        l.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        l.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        l.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        l.getDisabledStyle().setBgTransparency(255);
        l.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        o = new Button("Jump to a Dog");
        o.getUnselectedStyle().setBgTransparency(255);
        o.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        o.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        o.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        o.getDisabledStyle().setBgTransparency(255);
        o.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        // Left side commands
        ExpandCommand expand = new ExpandCommand(gw);
        addKeyListener('e', expand);
        e.setCommand(expand);

        UpCommand up = new UpCommand(gw);
        addKeyListener('u', up);
        u.setCommand(up);

        LeftCommand left = new LeftCommand(gw);
        addKeyListener('l', left);
        l.setCommand(left);

        JumpToDogCommand jumpToDog = new JumpToDogCommand(gw);
        addKeyListener('o', jumpToDog);
        o.setCommand(jumpToDog);

        leftContainer.addComponent(e);
        leftContainer.addComponent(u);
        leftContainer.addComponent(l);
        leftContainer.addComponent(o);

        // Right side buttons
        rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        this.addComponent(BorderLayout.EAST, rightContainer);
        c = new Button("Contract");
        c.getUnselectedStyle().setBgTransparency(255);
        c.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        c.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        c.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        c.getDisabledStyle().setBgTransparency(255);
        c.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        d = new Button("Down");
        d.getUnselectedStyle().setBgTransparency(255);
        d.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        d.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        d.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        d.getDisabledStyle().setBgTransparency(255);
        d.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        r = new Button("Right");
        r.getUnselectedStyle().setBgTransparency(255);
        r.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        r.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        r.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        r.getDisabledStyle().setBgTransparency(255);
        r.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        a = new Button("Jump to Cat");
        a.getUnselectedStyle().setBgTransparency(255);
        a.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        a.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        a.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        a.getDisabledStyle().setBgTransparency(255);
        a.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        s = new Button("Scoop!");
        s.getUnselectedStyle().setBgTransparency(255);
        s.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        s.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        s.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        s.getDisabledStyle().setBgTransparency(255);
        s.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        // Right side commands
        ContractCommand contract = new ContractCommand(gw);
        addKeyListener('c', contract);
        c.setCommand(contract);

        DownCommand down = new DownCommand(gw);
        addKeyListener('d', down);
        d.setCommand(down);

        RightCommand right = new RightCommand(gw);
        addKeyListener('r', right);
        r.setCommand(right);

        JumpToCatCommand jumpToCat = new JumpToCatCommand(gw);
        addKeyListener('a', jumpToCat);
        a.setCommand(jumpToCat);

        addKeyListener('s', scoop);
        s.setCommand(scoop);

        rightContainer.addComponent(c);
        rightContainer.addComponent(d);
        rightContainer.addComponent(r);
        rightContainer.addComponent(a);
        rightContainer.addComponent(s);


        // Bottom buttons
        bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        this.addComponent(BorderLayout.SOUTH, bottomContainer);
        bottomContainer.getStyle().setPadding(0,0,400,0);

        h = new Button("Heal");
        h.getUnselectedStyle().setBgTransparency(255);
        h.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        h.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        h.getDisabledStyle().setBgColor(ColorUtil.BLUE);
        h.getDisabledStyle().setBgTransparency(255);
        h.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        p = new Button("Pause");
        p.getUnselectedStyle().setBgTransparency(255);
        p.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        p.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        p.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
        p.getDisabledStyle().setBgTransparency(255);
        p.getDisabledStyle().setFgColor(ColorUtil.WHITE);

        // Bottom commands
        HealCommand heal = new HealCommand(gw);
        addKeyListener('h', heal);
        h.setCommand(heal);

        ToggleGameModeCommand pausePlay = new ToggleGameModeCommand(this);
        addKeyListener('p', pausePlay);
        p.setCommand(pausePlay);


        bottomContainer.addComponent(h);
        bottomContainer.addComponent(p);

        // Generate the Game
        this.show();
        mapWidth = mv.getWidth();
        mapHeight = mv.getHeight();
        gw.initLayout(mapWidth, mapHeight);
        disableHeal();

    }

    public UITimer getTimer() {
        return timer;
    }

    public void setTimer(UITimer timer) {
        this.timer = timer;
    }

    public void toggleGame() {
        if (gameRunning == true) {
            timer.schedule(timerSpeed, true, this);
        }
        if (gameRunning == false) {
            timer.cancel();
        }
    }

    public void gamePlay() {
        enableCommands();
        disableHeal();
        gameRunning = true;
        toggleGame();
        IIterator unselectItr = gw.getGameObjects().getIterator();
        Object tempObj;

        while(unselectItr.hasNext()) {
            tempObj = unselectItr.getNext();
            if (tempObj instanceof Dog && ((Dog) tempObj).isSelected()) {
                Dog selectedDog = (Dog) tempObj;
                selectedDog.setSelected(false);
            }
        }

        if (gw.isGamePaused() == true) {    // if game is in a pause state
            gw.setGamePaused(false);
            System.out.println("Game resume");
            if (gw.getSound() == false) {
                if (gw.getSoundCheckBox() == true) {    // check if checkbox is checked
                    gw.setSound(true);                  // if it is, turn on sound
                    gw.soundOn();
                }
            }
        }
    }

    public void gamePause() {
        enableHeal();
        disableCommands();
        gameRunning = false;
        toggleGame();
        if (gw.isGamePaused() == false) {   // if game is in a resume state
            gw.setGamePaused(true);         // set game to a pause state
            System.out.println("Game paused");
            gw.setSound(false);             // turn off sound
            gw.soundOff();
        }
    }

    public void enableCommands() {
        e.setEnabled(true);
        u.setEnabled(true);
        l.setEnabled(true);
        o.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        r.setEnabled(true);
        a.setEnabled(true);
        s.setEnabled(true);
    }

    public void disableCommands() {
        e.setEnabled(false);
        u.setEnabled(false);
        l.setEnabled(false);
        o.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        r.setEnabled(false);
        a.setEnabled(false);
        s.setEnabled(false);
    }

    public void disableHeal() {
        h.setEnabled(false);
    }

    public void enableHeal() {
        h.setEnabled(true);
    }

    public Button getP() {
        return p;
    }

    public void setP(Button p) {
        this.p = p;
    }

    public Boolean getGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(Boolean timerState) {
        this.gameRunning = timerState;
    }

    public void run() {
        gw.gameClockTick(timerSpeed, mv.getWidth(), mv.getHeight());
        repaint();
    }



}
