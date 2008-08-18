/*
 * Sound.java
 *
 * Created on 4 mars 2008, 17:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Tools;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author Administrateur
 */
public class Sound {
    
    public static String GRUNT_1 = "/sounds/grunt.wav";
    public static String GRUNT_2 = "/sounds/grunt2.wav";
    public static String BS = "/sounds/bs.wav";
    public static String WHISTLE = "/sounds/whistle.wav";
    public static String GOAL = "/sounds/goal.wav";
    
    private static Player grunt;
    private static Player grunt2;
    private static Player bs;
    private static Player whistle;
    private static Player goal;
    
    private static Sound mSound;
    public static Sound getSingleton()
    {
        if(mSound == null)
            mSound = new Sound();
        
        return mSound;
    }
    
    /** Creates a new instance of Sound */
    private Sound() {
        
        try {
           grunt = Manager.createPlayer(this.getClass().getResourceAsStream(GRUNT_1), "audio/x-wav");
           grunt.realize();
           grunt2 = Manager.createPlayer(this.getClass().getResourceAsStream(GRUNT_2), "audio/x-wav");
           grunt2.realize();
           bs = Manager.createPlayer(this.getClass().getResourceAsStream(BS), "audio/x-wav");
           bs.realize();
           whistle = Manager.createPlayer(this.getClass().getResourceAsStream(WHISTLE), "audio/x-wav");
           whistle.realize();
           goal = Manager.createPlayer(this.getClass().getResourceAsStream(GOAL), "audio/x-wav");
           goal.realize();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void PlayWav(String path)
    {
        try {
            if(path.equals(GRUNT_1))
                getSingleton().grunt.start();
            else if(path.equals(GRUNT_2))
                getSingleton().grunt2.start();
            else if(path.equals(BS))
                getSingleton().bs.start();
            else if(path.equals(WHISTLE))
                getSingleton().whistle.start();
            else if(path.equals(GOAL))
                getSingleton().goal.start();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    
}
