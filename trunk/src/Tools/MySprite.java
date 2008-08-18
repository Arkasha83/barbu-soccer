/*
 * MySprite.java
 *
 * Created on 3 novembre 2007, 20:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Tools;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrateur
 */
public abstract class MySprite extends Sprite {
    
    public static final int DIR_NO_DIR      = -1;
    public static final int DIR_UP          = 0;
    public static final int DIR_DOWN        = 1;
    public static final int DIR_LEFT        = 2;
    public static final int DIR_RIGHT       = 3;
    public static final int DIR_UPLEFT      = 4;
    public static final int DIR_UPRIGHT     = 5;
    public static final int DIR_DOWNLEFT    = 6;
    public static final int DIR_DOWNRIGHT   = 7;
    
    public static final int SPEED           = 20<<8;
    public static final int DIAGSPEED       = 30<<8;
    
    public int dir = DIR_NO_DIR;
    
    protected int limitH;
    protected int limitW;
    
    //physics
    public    Vector2D forces; //per?
    protected Vector2D distanceToMove;
    protected int massFixed; 
    

    
    protected MySprite(Image i, int mass)
    {
        super(i);
        defineReferencePixel(getWidth()>>1,getHeight()>>1);
        massFixed = mass << 8;
        forces = new Vector2D();
        distanceToMove = new Vector2D();
    }
    
    public void updateMoves()
    {
        int dx = 0, dy = 0;
        int nPX, nPY;
        switch (dir)
        {
            case DIR_UP         :    dy = -SPEED;     break;
            case DIR_DOWN       :    dy =  SPEED;     break;
            case DIR_LEFT       :    dx = -SPEED;     break;
            case DIR_RIGHT      :    dx =  SPEED;     break;
            case DIR_UPLEFT     :    
                dx = -DIAGSPEED;   
                dy = -DIAGSPEED;
                break;
            case DIR_UPRIGHT    :    
                dx =  DIAGSPEED;   
                dy = -DIAGSPEED;
                break;
            case DIR_DOWNLEFT   :    
                dx = -DIAGSPEED;   
                dy =  DIAGSPEED;
                break;
            case DIR_DOWNRIGHT  :    
                dx =  DIAGSPEED;   
                dy =  DIAGSPEED;
                break;
            default:
                //slowdown
                slowdown();
        }      
        

        //flip
        if(dx < 0)
            setTransform(TRANS_MIRROR);
        else if(dx > 0)
            setTransform(TRANS_NONE);
            
        forces.x += dx<<8;
        forces.y += dy<<8;        
        
        forces = manageMax(forces);
        
        distanceToMove.x += forces.x / massFixed;
        distanceToMove.y += forces.y / massFixed; 
        
        dx = distanceToMove.x>>8;
        dy = distanceToMove.y>>8;
        
        if(dx != 0)
        {     
            distanceToMove.x -= dx<<8;   
            nPX = getX() + dx;
            if(nPX < 0 || nPX > limitW - getWidth())
            {
                distanceToMove.x = -distanceToMove.x;
                forces.x = -forces.x;
                slowdown();
                dx = -dx;
            }
        }
        
        if(dy != 0)
        {
            distanceToMove.y -= dy<<8;
            nPY = getY() + dy;          
            if(nPY < 0 || nPY > limitH - getHeight())
            {
                distanceToMove.y = -distanceToMove.y;
                forces.y = -forces.y;
                slowdown();
                dy = -dy;
            }
        }
        
        customMove(dx, dy);
        
    } 
    
    public abstract void customMove(int x, int y);
    
    public abstract Vector2D manageMax(Vector2D forces);
    
    public abstract void slowdown();
    
    public void StopMvt()
    {
        forces.x = 0;
        forces.y = 0;
        distanceToMove.x = 0;
        distanceToMove.y = 0;
    }
}
