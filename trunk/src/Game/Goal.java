/*
 * Goal.java
 *
 * Created on 3 mars 2008, 12:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrateur
 */
public class Goal extends Sprite{
    
    public static final int TOP    = 0;
    public static final int BOTTOM = 1;
    
    /** Creates a new instance of Goal */
    public Goal(int x, int y, int anchor) {
        super(Playfield.LoadImage("/images/Goal.PNG"));
        defineCollisionRectangle(getWidth()/4,getHeight()/4,getWidth(),getHeight()/2);
        
        
        if(anchor == BOTTOM) y -= getHeight();
        x -= getWidth();
        setPosition(x,y);
    }
    
    public void customPaint(Graphics g)
    {
        paint(g);
        setTransform(TRANS_MIRROR);
        move(getWidth()*2-1,0);
        paint(g);
        setTransform(TRANS_NONE);
        move(1-getWidth()*2,0);
    }
    
}
