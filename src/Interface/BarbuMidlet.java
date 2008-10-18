/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Game.GameManager;
import Game.GameMidLet;
import Interface.ClientServer;
import Tools.Sound;
import javax.bluetooth.RemoteDevice;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;


/**
 * @author Administrateur
 */
public class BarbuMidlet extends GameMidLet implements CommandListener, Runnable {

// HINT - Uncomment for accessing new MIDlet Started/Resumed logic.
//    private boolean midletPaused = false;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private SplashScreen startScreen;
private List GameModes;
private Alert quitScreen;
private List HostGame;
private Alert Waiting;
private List gameList;
private Command exitCommand1;
private Command okCommand1;
private Command cancelCommand3;
private Command cancelCommand1;
private Font font1;
private Ticker Welcome;
private Image intro;
private Ticker ChooseGameMode;
//</editor-fold>//GEN-END:|fields|0|


// HINT - Uncomment for accessing new MIDlet Started/Resumed logic.
// NOTE - Be aware of resolving conflicts of the constructor.
//    /**
//     * The BarbuMidlet constructor.
//     */
//    public BarbuMidlet() {
//    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
/**
 * Initilizes the application.
 * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
 */
private void initialize () {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
}//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Started point.
 */
public void startMIDlet () {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable (null, getStartScreen ());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
}//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
 */
public void resumeMIDlet () {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
/**
 * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
 * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
 * @param nextDisplayable the Displayable to be set
 */
public void switchDisplayable (Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
Display display = getDisplay ();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
if (alert == null) {
display.setCurrent (nextDisplayable);
} else {
display.setCurrent (alert, nextDisplayable);
}//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
}//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
    // Insert global pre-action code here
if (displayable == GameModes) {//GEN-BEGIN:|7-commandAction|1|21-preAction
if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|1|21-preAction
 // write pre-action user code here
GameModesAction ();//GEN-LINE:|7-commandAction|2|21-postAction
 // write post-action user code here
} else if (command == exitCommand1) {//GEN-LINE:|7-commandAction|3|24-preAction
                // Insert pre-action code here
switchDisplayable (null, getQuitScreen ());//GEN-LINE:|7-commandAction|4|24-postAction
                // Insert post-action code here
}//GEN-BEGIN:|7-commandAction|5|33-preAction
} else if (displayable == HostGame) {
if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|33-preAction
 // write pre-action user code here
HostGameAction ();//GEN-LINE:|7-commandAction|6|33-postAction
 // write post-action user code here
} else if (command == cancelCommand3) {//GEN-LINE:|7-commandAction|7|35-preAction
                // Insert pre-action code here
switchDisplayable (null, getGameModes ());//GEN-LINE:|7-commandAction|8|35-postAction
                // Insert post-action code here
}//GEN-BEGIN:|7-commandAction|9|41-preAction
} else if (displayable == Waiting) {
if (command == cancelCommand1) {//GEN-END:|7-commandAction|9|41-preAction
                // Insert pre-action code here
                m_bRunThread=false;
                m_bIsServer=false;
                m_BlueObj = null;
switchDisplayable (null, getGameModes ());//GEN-LINE:|7-commandAction|10|41-postAction
                // Insert post-action code here
}//GEN-BEGIN:|7-commandAction|11|48-preAction
} else if (displayable == gameList) {
if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|11|48-preAction
 // write pre-action user code here
gameListAction ();//GEN-LINE:|7-commandAction|12|48-postAction
 // write post-action user code here
} else if (command == cancelCommand1) {//GEN-LINE:|7-commandAction|13|50-preAction
                // Insert pre-action code here
switchDisplayable (null, getGameModes ());//GEN-LINE:|7-commandAction|14|50-postAction
                // Insert post-action code here
}//GEN-BEGIN:|7-commandAction|15|27-preAction
} else if (displayable == quitScreen) {
if (command == okCommand1) {//GEN-END:|7-commandAction|15|27-preAction
                // Insert pre-action code here
exitMIDlet ();//GEN-LINE:|7-commandAction|16|27-postAction
                // Insert post-action code here
}//GEN-BEGIN:|7-commandAction|17|15-preAction
} else if (displayable == startScreen) {
if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|17|15-preAction
                // Insert pre-action code here
switchDisplayable (null, getGameModes ());//GEN-LINE:|7-commandAction|18|15-postAction
                // Insert post-action code here
}//GEN-BEGIN:|7-commandAction|19|7-postCommandAction
}//GEN-END:|7-commandAction|19|7-postCommandAction
    // Insert global post-action code here
}//GEN-BEGIN:|7-commandAction|20|
//</editor-fold>//GEN-END:|7-commandAction|20|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: startScreen ">//GEN-BEGIN:|13-getter|0|13-preInit
/**
 * Returns an initiliazed instance of startScreen component.
 * @return the initialized component instance
 */
public SplashScreen getStartScreen () {
if (startScreen == null) {//GEN-END:|13-getter|0|13-preInit
            // Insert pre-init code here
startScreen = new SplashScreen (getDisplay ());//GEN-BEGIN:|13-getter|1|13-postInit
startScreen.setTitle ("Barbu Soccer");
startScreen.setTicker (getWelcome ());
startScreen.setCommandListener (this);
startScreen.setImage (getIntro ());
startScreen.setTextFont (getFont1 ());//GEN-END:|13-getter|1|13-postInit
            // Insert post-init code here
}//GEN-BEGIN:|13-getter|2|
return startScreen;
}
//</editor-fold>//GEN-END:|13-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: GameModes ">//GEN-BEGIN:|19-getter|0|19-preInit
/**
 * Returns an initiliazed instance of GameModes component.
 * @return the initialized component instance
 */
public List getGameModes () {
if (GameModes == null) {//GEN-END:|19-getter|0|19-preInit
            // Insert pre-init code here
GameModes = new List ("Game Modes", Choice.IMPLICIT);//GEN-BEGIN:|19-getter|1|19-postInit
GameModes.append ("Host Game", null);
GameModes.append ("Join Game", null);
GameModes.setTicker (getChooseGameMode ());
GameModes.addCommand (getExitCommand1 ());
GameModes.setCommandListener (this);
GameModes.setSelectedFlags (new boolean[] { false, false });//GEN-END:|19-getter|1|19-postInit
            // Insert post-init code here
}//GEN-BEGIN:|19-getter|2|
return GameModes;
}
//</editor-fold>//GEN-END:|19-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: GameModesAction ">//GEN-BEGIN:|19-action|0|19-preAction
/**
 * Performs an action assigned to the selected list element in the GameModes component.
 */
public void GameModesAction () {//GEN-END:|19-action|0|19-preAction
 // enter pre-action user code here
switch (getGameModes ().getSelectedIndex ()) {//GEN-BEGIN:|19-action|1|31-preAction
case 0://GEN-END:|19-action|1|31-preAction
 // write pre-action user code here
switchDisplayable (null, getHostGame ());//GEN-LINE:|19-action|2|31-postAction
 // write post-action user code here
break;//GEN-BEGIN:|19-action|3|46-preAction
case 1://GEN-END:|19-action|3|46-preAction
 // write pre-action user code here
switchDisplayable (null, getGameList ());//GEN-LINE:|19-action|4|46-postAction
 // write post-action user code here
break;//GEN-BEGIN:|19-action|5|19-postAction
}//GEN-END:|19-action|5|19-postAction
 // enter post-action user code here
}//GEN-BEGIN:|19-action|6|
//</editor-fold>//GEN-END:|19-action|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: quitScreen ">//GEN-BEGIN:|26-getter|0|26-preInit
/**
 * Returns an initiliazed instance of quitScreen component.
 * @return the initialized component instance
 */
public Alert getQuitScreen () {
if (quitScreen == null) {//GEN-END:|26-getter|0|26-preInit
            // Insert pre-init code here
quitScreen = new Alert ("Quit Screen", "Barbu Dent Bleu Soccer by ArkaCorp. www.chris-ferrario.com", null, AlertType.INFO);//GEN-BEGIN:|26-getter|1|26-postInit
quitScreen.addCommand (getOkCommand1 ());
quitScreen.setCommandListener (this);
quitScreen.setTimeout (Alert.FOREVER);//GEN-END:|26-getter|1|26-postInit
            // Insert post-init code here
}//GEN-BEGIN:|26-getter|2|
return quitScreen;
}
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: HostGame ">//GEN-BEGIN:|32-getter|0|32-preInit
/**
 * Returns an initiliazed instance of HostGame component.
 * @return the initialized component instance
 */
public List getHostGame () {
if (HostGame == null) {//GEN-END:|32-getter|0|32-preInit
            // Insert pre-init code here
HostGame = new List ("Host Game", Choice.IMPLICIT);//GEN-BEGIN:|32-getter|1|32-postInit
HostGame.append ("1 player(local)", null);
HostGame.append ("2 playeres(bluetooth)", null);
HostGame.addCommand (getCancelCommand3 ());
HostGame.setCommandListener (this);
HostGame.setSelectedFlags (new boolean[] { false, false });//GEN-END:|32-getter|1|32-postInit
            // Insert post-init code here
}//GEN-BEGIN:|32-getter|2|
return HostGame;
}
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: HostGameAction ">//GEN-BEGIN:|32-action|0|32-preAction
/**
 * Performs an action assigned to the selected list element in the HostGame component.
 */
public void HostGameAction () {//GEN-END:|32-action|0|32-preAction
 // enter pre-action user code here
switch (getHostGame ().getSelectedIndex ()) {//GEN-BEGIN:|32-action|1|38-preAction
case 0://GEN-END:|32-action|1|38-preAction
 // write pre-action user code here
//GEN-LINE:|32-action|2|38-postAction
 // write post-action user code here
break;//GEN-BEGIN:|32-action|3|39-preAction
case 1://GEN-END:|32-action|3|39-preAction
 // write pre-action user code here
switchDisplayable (getWaiting (), getHostGame ());//GEN-LINE:|32-action|4|39-postAction
 // write post-action user code here
break;//GEN-BEGIN:|32-action|5|32-postAction
}//GEN-END:|32-action|5|32-postAction
 // enter post-action user code here
}//GEN-BEGIN:|32-action|6|
//</editor-fold>//GEN-END:|32-action|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Waiting ">//GEN-BEGIN:|40-getter|0|40-preInit
/**
 * Returns an initiliazed instance of Waiting component.
 * @return the initialized component instance
 */
public Alert getWaiting () {
if (Waiting == null) {//GEN-END:|40-getter|0|40-preInit
            // Insert pre-init code here
Waiting = new Alert ("alert", "Trying to connect...", null, null);//GEN-BEGIN:|40-getter|1|40-postInit
Waiting.addCommand (getCancelCommand1 ());
Waiting.setCommandListener (this);
Waiting.setTimeout (Alert.FOREVER);//GEN-END:|40-getter|1|40-postInit
            // Insert post-init code here
}//GEN-BEGIN:|40-getter|2|
return Waiting;
}
//</editor-fold>//GEN-END:|40-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: gameList ">//GEN-BEGIN:|47-getter|0|47-preInit
/**
 * Returns an initiliazed instance of gameList component.
 * @return the initialized component instance
 */
public List getGameList () {
if (gameList == null) {//GEN-END:|47-getter|0|47-preInit
            // Insert pre-init code here
gameList = new List ("Available Devices", Choice.IMPLICIT);//GEN-BEGIN:|47-getter|1|47-postInit
gameList.addCommand (getCancelCommand1 ());
gameList.setCommandListener (this);//GEN-END:|47-getter|1|47-postInit
            // Insert post-init code here
}//GEN-BEGIN:|47-getter|2|
return gameList;
}
//</editor-fold>//GEN-END:|47-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: gameListAction ">//GEN-BEGIN:|47-action|0|47-preAction
/**
 * Performs an action assigned to the selected list element in the gameList component.
 */
public void gameListAction () {//GEN-END:|47-action|0|47-preAction
 // enter pre-action user code here
//GEN-LINE:|47-action|1|47-postAction
 // enter post-action user code here
}//GEN-BEGIN:|47-action|2|
//</editor-fold>//GEN-END:|47-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|25-getter|0|25-preInit
/**
 * Returns an initiliazed instance of exitCommand1 component.
 * @return the initialized component instance
 */
public Command getExitCommand1 () {
if (exitCommand1 == null) {//GEN-END:|25-getter|0|25-preInit
            // Insert pre-init code here
exitCommand1 = new Command ("Exit", Command.EXIT, 1);//GEN-LINE:|25-getter|1|25-postInit
            // Insert post-init code here
}//GEN-BEGIN:|25-getter|2|
return exitCommand1;
}
//</editor-fold>//GEN-END:|25-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|28-getter|0|28-preInit
/**
 * Returns an initiliazed instance of okCommand1 component.
 * @return the initialized component instance
 */
public Command getOkCommand1 () {
if (okCommand1 == null) {//GEN-END:|28-getter|0|28-preInit
            // Insert pre-init code here
okCommand1 = new Command ("Ok", Command.OK, 1);//GEN-LINE:|28-getter|1|28-postInit
            // Insert post-init code here
}//GEN-BEGIN:|28-getter|2|
return okCommand1;
}
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand3 ">//GEN-BEGIN:|36-getter|0|36-preInit
/**
 * Returns an initiliazed instance of cancelCommand3 component.
 * @return the initialized component instance
 */
public Command getCancelCommand3 () {
if (cancelCommand3 == null) {//GEN-END:|36-getter|0|36-preInit
            // Insert pre-init code here
cancelCommand3 = new Command ("Cancel", Command.CANCEL, 1);//GEN-LINE:|36-getter|1|36-postInit
            // Insert post-init code here
}//GEN-BEGIN:|36-getter|2|
return cancelCommand3;
}
//</editor-fold>//GEN-END:|36-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand1 ">//GEN-BEGIN:|42-getter|0|42-preInit
/**
 * Returns an initiliazed instance of cancelCommand1 component.
 * @return the initialized component instance
 */
public Command getCancelCommand1 () {
if (cancelCommand1 == null) {//GEN-END:|42-getter|0|42-preInit
            // Insert pre-init code here
cancelCommand1 = new Command ("Cancel", Command.CANCEL, 1);//GEN-LINE:|42-getter|1|42-postInit
            // Insert post-init code here
}//GEN-BEGIN:|42-getter|2|
return cancelCommand1;
}
//</editor-fold>//GEN-END:|42-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Welcome ">//GEN-BEGIN:|16-getter|0|16-preInit
/**
 * Returns an initiliazed instance of Welcome component.
 * @return the initialized component instance
 */
public Ticker getWelcome () {
if (Welcome == null) {//GEN-END:|16-getter|0|16-preInit
            // Insert pre-init code here
Welcome = new Ticker ("Welcome new barbu!");//GEN-LINE:|16-getter|1|16-postInit
            // Insert post-init code here
}//GEN-BEGIN:|16-getter|2|
return Welcome;
}
//</editor-fold>//GEN-END:|16-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: font1 ">//GEN-BEGIN:|17-getter|0|17-preInit
/**
 * Returns an initiliazed instance of font1 component.
 * @return the initialized component instance
 */
public Font getFont1 () {
if (font1 == null) {//GEN-END:|17-getter|0|17-preInit
            // Insert pre-init code here
font1 = Font.getFont (Font.FONT_STATIC_TEXT);//GEN-LINE:|17-getter|1|17-postInit
            // Insert post-init code here
}//GEN-BEGIN:|17-getter|2|
return font1;
}
//</editor-fold>//GEN-END:|17-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: intro ">//GEN-BEGIN:|18-getter|0|18-preInit
/**
 * Returns an initiliazed instance of intro component.
 * @return the initialized component instance
 */
public Image getIntro () {
if (intro == null) {//GEN-END:|18-getter|0|18-preInit
            // Insert pre-init code here
try {//GEN-BEGIN:|18-getter|1|18-@java.io.IOException
intro = Image.createImage ("/images/leaf.png");
} catch (java.io.IOException e) {//GEN-END:|18-getter|1|18-@java.io.IOException
e.printStackTrace ();
}//GEN-LINE:|18-getter|2|18-postInit
            // Insert post-init code here
            Sound.PlayWav("/sounds/bs.wav");
}//GEN-BEGIN:|18-getter|3|
return intro;
}
//</editor-fold>//GEN-END:|18-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ChooseGameMode ">//GEN-BEGIN:|23-getter|0|23-preInit
/**
 * Returns an initiliazed instance of ChooseGameMode component.
 * @return the initialized component instance
 */
public Ticker getChooseGameMode () {
if (ChooseGameMode == null) {//GEN-END:|23-getter|0|23-preInit
            // Insert pre-init code here
ChooseGameMode = new Ticker ("Host to start a 1 or 2 player game");//GEN-LINE:|23-getter|1|23-postInit
            // Insert post-init code here
}//GEN-BEGIN:|23-getter|2|
return ChooseGameMode;
}
//</editor-fold>//GEN-END:|23-getter|2|
    

















  















    
    /**
     * This method should return an instance of the display.
     */

    /**
     * Returns a display instance.
     * @return the display instance.
     */
public Display getDisplay () {

        return Display.getDisplay(this);
        // return Display.getDisplay (this);
}

    
    /**
     * This method should exit the midlet.
     */

    /**
     * Exits MIDlet.
     */
public void exitMIDlet() {

        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
        // switchDisplayable (null, null);
        // destroyApp(true);
        // notifyDestroyed();
}

   
    
    /** Creates a new instance of BlueToothExp */
    public BarbuMidlet() {
        initialize();
    }
                   
    private ClientServer m_BlueObj; 
    private boolean m_bRunThread=false;
    private boolean m_bIsServer=false;
    private boolean m_gameStarted=false;
    
    public void run() 
    {
        while(m_bRunThread)
        {
            try
            {
                if(m_BlueObj==null)
                {
                    m_BlueObj=new ClientServer(m_bIsServer);
                }
                if(!m_BlueObj.connectionInfo.equals(""))
                    Waiting.setString(m_BlueObj.connectionInfo);
                
                if(m_BlueObj.m_DscrAgent !=  null)
                {
                    RemoteDevice[] devices = m_BlueObj.m_DscrAgent.retrieveDevices(0);
                    
                    boolean exists;
                    String s;
                    if(devices != null)
                    {
                        for(int i=0;i<devices.length;i++)
                        {
                            s = devices[i].getFriendlyName(true);
                            exists = false;
                            for(int j=0;j<gameList.size();j++)
                            {
                                if(gameList.getString(j).equals(s))
                                    exists = true;
                            }
                            
                            if(!exists)
                            try
                            {
                                gameList.append(s,null);
                            }
                            catch(Exception ex)
                            {

                            }
                        }
                    }
                }
               
                byte[] str = m_BlueObj.RecieveMessages();
                
                if(str!=null)
                {
                    //System.out.println(str);
                    if(!m_gameStarted)
                    {
                        m_gameStarted = true;
                        startGame((m_bIsServer)?GameManager.MODE_SERVER:GameManager.MODE_CLIENT);
                    }
                    else
                    {
                        if(m_bIsServer)
                            pushClientInfo(str);
                        else
                            pushServerInfo(str);
                    }
                }

                m_BlueObj.SendMessages((m_bIsServer)?getServerInfo():getClientInfo());
                
                Thread.sleep(10);
                m_bRunThread = m_BlueObj.running;
            }
            catch(Exception ex)
            {
                System.out.println("EXC:"+ex.getMessage());
                System.out.println("EXC:"+ex.getClass());
            }

         }
        exitMIDlet();
    }//end while

    protected void endGame() {
        getDisplay().setCurrent(getGameModes());           
    }

    

    
    
    public void startApp() {
    }
    
    public void pauseApp() {
        
    }
    
    public void destroyApp(boolean unconditional) {
        
        game.end();
        m_bRunThread=false;
        if(m_BlueObj!=null)
            m_BlueObj.CloseAll();
    }
    


// HINT - Uncomment for accessing new MIDlet Started/Resumed logic.
// NOTE - Be aware of resolving conflicts of following methods.
//    /**
//     * Called when MIDlet is started.
//     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
//     */
//    public void startApp() {
//        if (midletPaused) {
//            resumeMIDlet ();
//        } else {
//            initialize ();
//            startMIDlet ();
//        }
//        midletPaused = false;
//    }
//
//    /**
//     * Called when MIDlet is paused.
//     */
//    public void pauseApp() {
//        midletPaused = true;
//    }
//
//    /**
//     * Called to signal the MIDlet to terminate.
//     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
//     */
//    public void destroyApp(boolean unconditional) {
//    }

}
