/*
 * FixedInt.java
 *
 * Created on 11 novembre 2007, 11:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Tools;

/**
 *
 * @author Administrateur
 */
public class FixedInt {
    
    public int var;
    
    /** Creates a new instance of FixedInt */
    public FixedInt(int _var) {
        var = _var << 8;
    }
    
    public int toInt()
    {
        return var >> 8;
    }
    
}
