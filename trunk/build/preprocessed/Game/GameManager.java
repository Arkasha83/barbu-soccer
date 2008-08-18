/*
 * GameManager.java
 *
 * Created on 19 octobre 2007, 10:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Game;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrateur
 */
public class GameManager implements Runnable {
    
    public static final int MODE_LOCAL = 0;
    public static final int MODE_SERVER = 1;
    public static final int MODE_CLIENT = 2;
    
    private Playfield pf;
    private GameMidLet server;
    private int multiplayerMode;
    
    
    /** Creates a new instance of GameManager */
    public GameManager(GameMidLet s)  {
        pf = new Playfield();
        server = s;
    }
    
 
    public Displayable getGameScreen()
    {
        return (Displayable)pf;
    }
    
    
    public void setMultiPlayerMode(int m)
    {
        pf.setMultiMode(m);
    }

    public void run() {
        
        pf.Restart();
        
        while (pf.isAlive) {
            
            pf.update();
            pf.repaint();
            
            try {
                Thread.currentThread().sleep(1000/24);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
	}
        pf.reset();
        server.endGame();
    }

    
    public byte[] getClientGameInfo()
    {
        byte[] b = new byte[4];
        b[0] = (byte)pf.getCurrentKey();
        return b;
    }
    
    public byte[] getServerGameInfo()
    {
        return pf.getServerInfo();
    }
    
    void setClientInput(byte[] str) {
        pf.setClientInput(str[0]);
    }
    
    void setServerInput(byte[] str) {
        pf.setServerInput(str);
    } 

    public void end() {
        pf.isAlive = false;
    }
}
