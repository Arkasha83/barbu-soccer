/*
 * Vector2D.java
 *
 * Created on 11 novembre 2007, 19:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Tools;

/**
 *
 * @author Administrateur
 */
public class Vector2D {
    
    public int x;
    public int y;
    
    /** Creates a new instance of Vector2D */
    public Vector2D() {
        x = y = 0;
    }
    
    
    public Vector2D divide(int f)
    {
        x /= f;
        y /= f;
        
        return this;
    }
    
}
