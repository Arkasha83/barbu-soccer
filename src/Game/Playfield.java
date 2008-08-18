/*
 * Playfield.java
 *
 * Created on 19 octobre 2007, 10:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Game;

import Tools.MySprite;
import Tools.Sound;
import Tools.Vector2D;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

/**
 *
 * @author Administrateur
 */
public class Playfield extends GameCanvas{
    
    public static final int STATE_NORMAL = 0;
    public static final int STATE_GOAL1  = 1;
    public static final int STATE_GOAL2  = 2;
    public static final int STATE_TIMEUP = 3;
    
    public static final int GOAL_TIME    = 50;
    
    private int multiPlayer;
    private int sGoalTime = 0;
    private int counter = 0;
    private int score1 = 0;
    private int score2 = 0;
    private int gameState;
    private int lastKey;
    private Ball ball;
    private BarbuSprite barbu1;
    private BarbuSprite barbu2;
    private Goal goal1;
    private Goal goal2;
    private Image goalImg;
    public boolean isAlive;

    private int clientInput = 0;
    
    
    /** Creates a new instance of Playfield */
    public Playfield() {
        super(false);
        setFullScreenMode(true);
        LoadSprites();
        //Restart();
        gameState = STATE_NORMAL;
    }
    
    public void setMultiMode(int m)
    {
        multiPlayer = m;
    }
    
    public void Restart()
    {
       gameState = STATE_NORMAL;
       isAlive = true;
       //ball.StopMvt();
       //barbu1.StopMvt();
       //barbu2.StopMvt();
       ball.setRefPixelPosition((getWidth())>>1,(getHeight()-ball.getHeight())>>1);
       barbu1.setRefPixelPosition((getWidth())>>1,getHeight()/4  - barbu1.getHeight()/2);
       barbu2.setRefPixelPosition((getWidth())>>1,getHeight()/4*3- barbu2.getHeight()/2);      
       
       Sound.PlayWav("/sounds/whistle.wav");
    }
    
    private void LoadSprites() {
       ball   = new Ball(LoadImage("/images/ball.PNG"), 15, getWidth(),getHeight());
       barbu1 = new BarbuSprite(LoadImage("/images/Walk.PNG"), 40, getWidth(),getHeight());
       barbu2 = new BarbuSprite(LoadImage("/images/Walk2.PNG"), 40, getWidth(),getHeight());
       goal1  = new Goal(getWidth()/2,0,Goal.TOP);
       goal2  = new Goal(getWidth()/2,getHeight(),Goal.BOTTOM);
       
       goalImg = LoadImage("/images/score.png");
    }
    
    public static Image LoadImage(String path)
    {
        Image img = null;
        try {
            img = Image.createImage(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return img;
    }

    protected void keyPressed(int keyCode) {
        if(keyCode == KEY_STAR)
            isAlive = false;
        lastKey = keyCode;
    }

    protected void keyReleased(int keyCode) {
        if(lastKey == keyCode)
            lastKey = 0;
    }
    

    
    private int getDirFromAI(int x, int y)
    {
        int keys = 0;
        if(ball.getRefPixelY()>y)
            keys |= DOWN_PRESSED;
        else if(ball.getRefPixelY()<y)
            keys |= UP_PRESSED;
        if(ball.getRefPixelX()>x)
            keys |= RIGHT_PRESSED;
        else if(ball.getRefPixelX()<x)
            keys |= LEFT_PRESSED;
        
        return getDirFromKeys(keys);
    }

    
    private int getDirFromKeys(int keys)
    {
        int dir = 0;
        
        switch (keys)
        {
            case DOWN_PRESSED  : 
            case KEY_NUM8      :
                dir = BarbuSprite.DIR_DOWN;
                break;
            case UP_PRESSED    : 
            case KEY_NUM2      :
                dir = BarbuSprite.DIR_UP;
                break;
            case RIGHT_PRESSED :
            case KEY_NUM6      : 
                dir = BarbuSprite.DIR_RIGHT;
                break;
            case LEFT_PRESSED  :
            case KEY_NUM4      : 
                dir = BarbuSprite.DIR_LEFT;
                break;
            case LEFT_PRESSED|UP_PRESSED  :
            case KEY_NUM1      : 
                dir = BarbuSprite.DIR_UPLEFT;
                break;
            case RIGHT_PRESSED|UP_PRESSED  :
            case KEY_NUM3      : 
                dir = BarbuSprite.DIR_UPRIGHT;
                break;
            case DOWN_PRESSED|LEFT_PRESSED  :
            case KEY_NUM7      : 
                dir = BarbuSprite.DIR_DOWNLEFT;
                break;
            case DOWN_PRESSED|RIGHT_PRESSED  :
            case KEY_NUM9      : 
                dir = BarbuSprite.DIR_DOWNRIGHT;
                break;
            default:
                 dir = BarbuSprite.DIR_NO_DIR;
            
        }  
        return dir;
    }
    
    public int getCurrentKey()
    {
        
        int keyState = getKeyStates();
        
        if(keyState == 0)
            keyState = lastKey;
        
        return keyState;
    }
    
    public void update()
    {
        if(multiPlayer == GameManager.MODE_CLIENT)
            return;
        
        if(gameState == STATE_NORMAL)
            counter++;

        if(counter/60 > 44)
            gameState = STATE_TIMEUP;
        
        int d  = getDirFromKeys(getCurrentKey()); 
        int d2 = getDirFromKeys(clientInput); 
        
        if(multiPlayer == GameManager.MODE_LOCAL)
        {
            barbu1.dir = d;
            barbu2.dir = getDirFromAI(barbu2.getRefPixelX(),barbu2.getRefPixelY());
        }
        else if(multiPlayer == GameManager.MODE_SERVER) 
        {
            barbu1.dir = d;
            barbu2.dir = d2;
        }
        
        if(gameState == STATE_NORMAL)
        {
            if(ball.collidesWith(goal1, false))
            {
               gameState = STATE_GOAL1;
                score1++;
                sGoalTime = GOAL_TIME; 
                Sound.PlayWav(Sound.GOAL);   
            }
            if(ball.collidesWith(goal2, false))
            {
                gameState = STATE_GOAL2;
                score2++;
                sGoalTime = GOAL_TIME;
                Sound.PlayWav(Sound.GOAL);
            }
            
            if(ball.collidesWith(barbu2, false))
            {
                ball.getKicked(barbu2.forces.divide(2));
                Sound.PlayWav(Sound.GRUNT_2);
            }
            if(ball.collidesWith(barbu1, false))
            {
                ball.getKicked(barbu1.forces.divide(2));
                Sound.PlayWav(Sound.GRUNT_1);
            }

            barbu2.updateMoves();
            barbu1.updateMoves();
            ball.updateMoves();
        }
        else if(gameState == STATE_GOAL1 || gameState == STATE_GOAL2)
        {
            sGoalTime--;
            if(sGoalTime<0)
                Restart();
        }
    }
    

    public void paint(Graphics g) {
        DrawField(g);
        goal1.customPaint(g);
        
        if(ball != null)
            ball.paint(g);
        if(barbu1 != null)
            barbu1.paint(g);
        if(barbu2 != null)
            barbu2.paint(g);
        
        goal2.customPaint(g);
        
        DrawHUD(g);
    }
    
    private void DrawHUD(Graphics g) {
        int m,s;
        
        String msg = "";
        m = counter/60;
        s = counter%60;
        g.setColor(0,0,0);
        if(m<10)  msg += "0";
        msg+=m+" : ";
        if(s<10)  msg += "0";
        msg+=s;
        
        g.drawString( msg,10,10,0);
        g.drawString( (score2)+" - "+(score1),10,25,0);
        
        if(gameState == STATE_GOAL1 || gameState == STATE_GOAL2)
        {
            g.drawImage(goalImg,(getWidth()-goalImg.getWidth())>>1,(getHeight()-goalImg.getHeight())>>1,0);
        }   
        else if(gameState == STATE_TIMEUP)
        {
            String text;
            g.setColor(255,255,255);
            g.fillRect(getWidth()/2 - 50,getWidth()/2 - 40,100,80);
            g.setColor(255,0,0);
            if(score1 == score2)
                text = "Barbu Draw!";
            else if(score1 < score2)
                text = "Barbu Brun Wins!";
            else
                text = "Barbu Blond Wins!";
            
            
            g.drawString( text,getWidth()/2 - 40,getWidth()/2 -30,0);
            text = "* to quit";
            g.drawString( text,getWidth()/2 - 40,getWidth()/2 +10,0);
        }
    }
    
    private void DrawField(Graphics g) {
        int x,y,w,h,tmp,tmpx,tmpy;
        
        x = 0;
        y = 0;
        w = getWidth();
        h = getHeight();
        
        //drawfield
        g.setColor(0,255,0);
        g.fillRect(x,y,w,h);
        
        g.setColor(255,255,255);
        //drawlines
        tmpx = 2;
        tmpy = 10;
        x+=tmpx;   y+=tmpy;   w-=tmpx*2;   h-=tmpy*2;
        g.drawRect(x,y,w,h);
        tmp = 1;
        x+=tmp;   y+=tmp;   w-=tmp*2;   h-=tmp*2;
        g.drawRect(x,y,w,h);
        //midline
        y = getHeight() >> 1;   h = 1;
        g.drawRect(x,y,w,h);
        //circle
        tmp = getWidth() >> 3;
        x = (getWidth() >> 1) - tmp;
        y = (getHeight() >> 1) - tmp;
        w = tmp*2;
        h = tmp*2;
        g.drawRoundRect(x,y,w,h,w,h);   
    }    

    void setClientInput(int i) {
        clientInput = i;
    }
    
    void setServerInput(byte[] b)
    {
        int x,y;
        
        if(b == null)
            return;
        score1    = b[0];
        score2    = b[1];
        counter   = b[2]*60;
        counter  += b[3];
        gameState = b[20];
        
        x = ((b[4]&0xff)<<8)+(b[5]&0xff);
        y = ((b[6]&0xff)<<8)+(b[7]&0xff);
        ball.setPosition(x,y);
        x = ((b[8]&0xff)<<8)+(b[9]&0xff);
        y = ((b[10]&0xff)<<8)+(b[11]&0xff);
        simulateUpdate(barbu1,x,y,b[16],b[18]);
        x = ((b[12]&0xff)<<8)+(b[13]&0xff);
        y = ((b[14]&0xff)<<8)+(b[15]&0xff);
        simulateUpdate(barbu2,x,y,b[17],b[19]);
    }
    
    private void simulateUpdate(BarbuSprite b, int x, int y, byte dir, byte frame)
    {
        int dx = x-b.getX();
        int dy = y-b.getY();
           
        //flip
        if(dir == 2 || dir == 4 || dir == 6)
            b.setTransform(b.TRANS_MIRROR);
        else if(dir == 3 || dir == 5 || dir == 7)
            b.setTransform(b.TRANS_NONE);
        
        b.setFrame(frame);
        
        b.setPosition(x,y);
    }
    
    
    public byte[] getServerInfo()
    {
        byte[] b = new byte[21];
        b[0]  = (byte)score1;
        b[1]  = (byte)score2;
        b[2]  = (byte)(counter/60);
        b[3]  = (byte)(counter%60);

        b[4]  = ((byte)(ball.getX()>>8));
        b[5]  = ((byte)(ball.getX()));
        b[6]  = (byte)(ball.getY()>>8);
        b[7]  = (byte)(ball.getY());
        b[8]  = (byte)(barbu1.getX()>>8);
        b[9]  = (byte)(barbu1.getX());
        b[10] = (byte)(barbu1.getY()>>8);
        b[11] = (byte)(barbu1.getY());
        b[12] = (byte)(barbu2.getX()>>8);
        b[13] = (byte)(barbu2.getX());
        b[14] = (byte)(barbu2.getY()>>8);
        b[15] = (byte)(barbu2.getY());
        b[16] = (byte)barbu1.dir;
        b[17] = (byte)barbu2.dir;
        b[18] = (byte)barbu1.getFrame();
        b[19] = (byte)barbu2.getFrame();
        b[20] = (byte)gameState;
              
        return b;
    }

    void reset() {
        counter = 0;
        gameState = STATE_NORMAL;
        ball.StopMvt();
        barbu1.StopMvt();
        barbu2.StopMvt();
        ball.setRefPixelPosition((getWidth())>>1,(getHeight()-ball.getHeight())>>1);
        barbu1.setRefPixelPosition((getWidth())>>1,getHeight()/4  - barbu1.getHeight()/2);
        barbu2.setRefPixelPosition((getWidth())>>1,getHeight()/4*3- barbu2.getHeight()/2); 
    }
    
}
