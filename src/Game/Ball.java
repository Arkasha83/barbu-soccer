/*
 * Ball.java
 *
 * Created on 3 novembre 2007, 20:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Game;

import Tools.MySprite;
import Tools.Sound;
import Tools.Vector2D;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrateur
 */
public class Ball  extends  MySprite{
    
    
    
    /** Creates a new instance of Ball */
    public Ball(Image i, int mass, int lW, int lH) {
        super(i,mass);
        limitW = lW;
        limitH = lH;
    }

    void getKicked(Vector2D f) {
        if(f.x == 0 && f.y == 0)
        {
            forces.x = -forces.x;
            forces.y = -forces.y;
        }
        
        forces.x += f.x;
        forces.y += f.y;
    }

    public void slowdown() {
        forces.x *= 90;
        forces.x /= 100;
        forces.y *= 90;
        forces.y /= 100;
    }
    
    
    public void customMove(int x, int y) {
        move(x,y);
    }

    public Vector2D manageMax(Vector2D forces) {
        return forces;
    }
}
