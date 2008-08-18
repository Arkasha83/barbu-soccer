/*
 * GameMidLet.java
 *
 * Created on 3 mars 2008, 15:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Game;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author Administrateur
 */
public abstract class GameMidLet extends MIDlet {
    
    protected GameManager game;
    
    
    public GameMidLet()
    {
        game = new GameManager(this);
    }
    
    public void startGame(int type) {
    
        Display.getDisplay(this).setCurrent(game.getGameScreen());
        try {
            // Start the game in its own thread

            game.setMultiPlayerMode(type);
            Thread myThread = new Thread(game);
            myThread.start();
        } catch (Error e) {
            try {
                destroyApp(false);
            } catch (MIDletStateChangeException ex) {
                ex.printStackTrace();
            }
            notifyDestroyed();
        }
    }
    
    protected byte[] getClientInfo()
    {
        return game.getClientGameInfo();
    }
    
    protected byte[] getServerInfo()
    {
        return game.getServerGameInfo();
    }
    
    
    protected void pushClientInfo(byte[] str) {
        game.setClientInput(str);
    }
    
    protected void pushServerInfo(byte[] str) {
        game.setServerInput(str);
    }
    
    abstract protected void endGame();
}
