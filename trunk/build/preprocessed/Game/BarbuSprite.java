/*
 * BarbuSprite.java
 *
 * Created on 19 octobre 2007, 17:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Game;

import Tools.MySprite;
import Tools.Vector2D;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrateur
 */
public class BarbuSprite extends MySprite {
    
    
   // private static final int MAX_SPEED = ;
    
    /**
     * Creates a new instance of BarbuSprite
     */
    public BarbuSprite(Image i, int mass, int lW, int lH) {
        super(i, mass);
        setImage(i, getWidth()/2, getHeight());
        defineReferencePixel(getWidth()>>1,getHeight()>>1);
        limitW = lW;
        limitH = lH;
    }
    
  
    public void slowdown() {
        forces.x /= 2;
        forces.y /= 2;
    }

    public void customMove(int x, int y) {
        
        
        if(x==0&&y==0)
            setFrame(1);
        else
            setFrame(0);
        
        move(x,y);
    }

    public Vector2D manageMax(Vector2D forces) {
        
        //manage max
        if(forces.x / massFixed > 1024) forces.x = 1024 * massFixed;
        if(forces.y / massFixed > 1024) forces.y = 1024 * massFixed;
        if(forces.x / massFixed < -1024) forces.x = -1024 * massFixed;
        if(forces.y / massFixed < -1024) forces.y = -1024 * massFixed;
        
        return forces;
    }
}
