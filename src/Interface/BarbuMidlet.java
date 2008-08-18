package Interface;
/*
 * BlueToothExp.java
 *
 * Created on December 8, 2006, 2:08 AM
 */

import Game.GameManager;
import Game.GameMidLet;
import Interface.ClientServer;
import Tools.Sound;
import javax.bluetooth.RemoteDevice;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author Farhan Hameed Khan
 */
public class BarbuMidlet extends GameMidLet implements CommandListener, Runnable {   
    
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
                if(m_BlueObj.connectionInfo != "")
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
        getDisplay().setCurrent(get_GameModes());           
    }

    
    private org.netbeans.microedition.lcdui.SplashScreen startScreen;//GEN-BEGIN:MVDFields
    private Ticker Welcome;
    private Image intro;
    private Font font1;
    private List GameModes;
    private Ticker ChooseGameMode;
    private List HostGame;
    private Alert Waiting;
    private Command cancelCommand1;
    private Command cancelCommand3;
    private Command exitCommand1;
    private Alert quitScreen;
    private Command okCommand1;
    private List gameList;//GEN-END:MVDFields
    
//GEN-LINE:MVDMethods

    /** Called by the system to indicate that a command has been invoked on a particular displayable.//GEN-BEGIN:MVDCABegin
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:MVDCABegin
    // Insert global pre-action code here
        if (displayable == startScreen) {//GEN-BEGIN:MVDCABody
            if (command == startScreen.DISMISS_COMMAND) {//GEN-END:MVDCABody
                // Insert pre-action code here
                getDisplay().setCurrent(get_GameModes());//GEN-LINE:MVDCAAction3
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase3
        } else if (displayable == GameModes) {
            if (command == GameModes.SELECT_COMMAND) {
                switch (get_GameModes().getSelectedIndex()) {
                    case 0://GEN-END:MVDCACase3
                        // Insert pre-action code here
                        getDisplay().setCurrent(get_HostGame());//GEN-LINE:MVDCAAction11
                        // Insert post-action code here
                        break;//GEN-BEGIN:MVDCACase11
                    case 1://GEN-END:MVDCACase11
                        // Insert pre-action code here
                        if(m_bRunThread==false)
                        {
                            Thread BTThread = new Thread(this);
                            BTThread.start();
                            m_bRunThread=true;
                            m_bIsServer=false;
                        }
                        getDisplay().setCurrent(get_gameList());//GEN-LINE:MVDCAAction13
                        // Insert post-action code here
                        break;//GEN-BEGIN:MVDCACase13
                }
            } else if (command == exitCommand1) {//GEN-END:MVDCACase13
                // Insert pre-action code here
                getDisplay().setCurrent(get_quitScreen());//GEN-LINE:MVDCAAction42
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase42
        } else if (displayable == HostGame) {
            if (command == HostGame.SELECT_COMMAND) {
                switch (get_HostGame().getSelectedIndex()) {
                    case 0://GEN-END:MVDCACase42
                        
                        startGame(GameManager.MODE_LOCAL);
                        // Do nothing//GEN-LINE:MVDCAAction27
                        // Insert post-action code here
                        break;//GEN-BEGIN:MVDCACase27
                    case 1://GEN-END:MVDCACase27
                        // Insert pre-action code here
                        if(m_bRunThread==false)
                        {                            
                            Thread BTThread = new Thread(this);
                            BTThread.start();
                            m_bRunThread=true;
                            m_bIsServer=true;
                        }
                        getDisplay().setCurrent(get_Waiting(), get_HostGame());//GEN-LINE:MVDCAAction29
                        // Insert post-action code here
                        break;//GEN-BEGIN:MVDCACase29
                }
            } else if (command == cancelCommand3) {//GEN-END:MVDCACase29
                // Insert pre-action code here
                getDisplay().setCurrent(get_GameModes());//GEN-LINE:MVDCAAction40
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase40
        } else if (displayable == Waiting) {
            if (command == cancelCommand1) {//GEN-END:MVDCACase40
                // Insert pre-action code here
                m_bRunThread=false;
                m_bIsServer=false;
                m_BlueObj = null;
                getDisplay().setCurrent(get_GameModes());//GEN-LINE:MVDCAAction36
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase36
        } else if (displayable == quitScreen) {
            if (command == okCommand1) {//GEN-END:MVDCACase36
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction45
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase45
        } else if (displayable == gameList) {
            if (command == cancelCommand1) {//GEN-END:MVDCACase45
                // Insert pre-action code here
                getDisplay().setCurrent(get_GameModes());//GEN-LINE:MVDCAAction53
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase53
        }//GEN-END:MVDCACase53
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method initializes UI of the application.//GEN-BEGIN:MVDInitBegin
     */
    private void initialize() {//GEN-END:MVDInitBegin
        // Insert pre-init code here
        getDisplay().setCurrent(get_startScreen());//GEN-LINE:MVDInitInit
        // Insert post-init code here
    }//GEN-LINE:MVDInitEnd
    
    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {//GEN-FIRST:MVDGetDisplay
        return Display.getDisplay(this);
    }//GEN-LAST:MVDGetDisplay
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {//GEN-FIRST:MVDExitMidlet
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }//GEN-LAST:MVDExitMidlet

    /** This method returns instance for startScreen component and should be called instead of accessing startScreen field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for startScreen component
     */
    public org.netbeans.microedition.lcdui.SplashScreen get_startScreen() {
        if (startScreen == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            startScreen = new org.netbeans.microedition.lcdui.SplashScreen(getDisplay());//GEN-BEGIN:MVDGetInit2
            startScreen.setCommandListener(this);
            startScreen.setTitle("Barbu Soccer");
            startScreen.setTicker(get_Welcome());
            startScreen.setImage(get_intro());
            startScreen.setTextFont(get_font1());//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return startScreen;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for Welcome component and should be called instead of accessing Welcome field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for Welcome component
     */
    public Ticker get_Welcome() {
        if (Welcome == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            Welcome = new Ticker("Welcome new barbu!");//GEN-LINE:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return Welcome;
    }//GEN-END:MVDGetEnd4

    /** This method returns instance for intro component and should be called instead of accessing intro field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for intro component
     */
    public Image get_intro() {
        if (intro == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit5
                intro = Image.createImage("/images/leaf.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit5
            // Insert post-init code here
            Sound.PlayWav("/sounds/bs.wav");
        }//GEN-BEGIN:MVDGetEnd5
        return intro;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for font1 component and should be called instead of accessing font1 field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for font1 component
     */
    public Font get_font1() {
        if (font1 == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            font1 = Font.getFont(Font.FACE_SYSTEM, 0x3, Font.SIZE_SMALL);//GEN-LINE:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return font1;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for GameModes component and should be called instead of accessing GameModes field directly.//GEN-BEGIN:MVDGetBegin7
     * @return Instance for GameModes component
     */
    public List get_GameModes() {
        if (GameModes == null) {//GEN-END:MVDGetBegin7
            // Insert pre-init code here
            GameModes = new List("Game Modes", Choice.IMPLICIT, new String[] {//GEN-BEGIN:MVDGetInit7
                "Host Game",
                "Join Game"
            }, new Image[] {
                null,
                null
            });
            GameModes.addCommand(get_exitCommand1());
            GameModes.setCommandListener(this);
            GameModes.setTicker(get_ChooseGameMode());
            GameModes.setSelectedFlags(new boolean[] {
                false,
                false
            });//GEN-END:MVDGetInit7
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd7
        return GameModes;
    }//GEN-END:MVDGetEnd7

    /** This method returns instance for ChooseGameMode component and should be called instead of accessing ChooseGameMode field directly.//GEN-BEGIN:MVDGetBegin9
     * @return Instance for ChooseGameMode component
     */
    public Ticker get_ChooseGameMode() {
        if (ChooseGameMode == null) {//GEN-END:MVDGetBegin9
            // Insert pre-init code here
            ChooseGameMode = new Ticker("Host to start a 1 or 2 player game");//GEN-LINE:MVDGetInit9
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd9
        return ChooseGameMode;
    }//GEN-END:MVDGetEnd9
  
    /** This method returns instance for HostGame component and should be called instead of accessing HostGame field directly.//GEN-BEGIN:MVDGetBegin24
     * @return Instance for HostGame component
     */
    public List get_HostGame() {
        if (HostGame == null) {//GEN-END:MVDGetBegin24
            // Insert pre-init code here
            HostGame = new List("Host Game", Choice.IMPLICIT, new String[] {//GEN-BEGIN:MVDGetInit24
                "1 player(local)",
                "2 playeres(bluetooth)"
            }, new Image[] {
                null,
                null
            });
            HostGame.addCommand(get_cancelCommand3());
            HostGame.setCommandListener(this);
            HostGame.setSelectedFlags(new boolean[] {
                false,
                false
            });//GEN-END:MVDGetInit24
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd24
        return HostGame;
    }//GEN-END:MVDGetEnd24

    /** This method returns instance for Waiting component and should be called instead of accessing Waiting field directly.//GEN-BEGIN:MVDGetBegin34
     * @return Instance for Waiting component
     */
    public Alert get_Waiting() {
        if (Waiting == null) {//GEN-END:MVDGetBegin34
            // Insert pre-init code here
            Waiting = new Alert(null, "Trying to connect...", null, null);//GEN-BEGIN:MVDGetInit34
            Waiting.addCommand(get_cancelCommand1());
            Waiting.setCommandListener(this);
            Waiting.setTimeout(-2);//GEN-END:MVDGetInit34
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd34
        return Waiting;
    }//GEN-END:MVDGetEnd34

    /** This method returns instance for cancelCommand1 component and should be called instead of accessing cancelCommand1 field directly.//GEN-BEGIN:MVDGetBegin35
     * @return Instance for cancelCommand1 component
     */
    public Command get_cancelCommand1() {
        if (cancelCommand1 == null) {//GEN-END:MVDGetBegin35
            // Insert pre-init code here
            cancelCommand1 = new Command("Cancel", Command.CANCEL, 1);//GEN-LINE:MVDGetInit35
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd35
        return cancelCommand1;
    }//GEN-END:MVDGetEnd35

    /** This method returns instance for cancelCommand3 component and should be called instead of accessing cancelCommand3 field directly.//GEN-BEGIN:MVDGetBegin39
     * @return Instance for cancelCommand3 component
     */
    public Command get_cancelCommand3() {
        if (cancelCommand3 == null) {//GEN-END:MVDGetBegin39
            // Insert pre-init code here
            cancelCommand3 = new Command("Cancel", Command.CANCEL, 1);//GEN-LINE:MVDGetInit39
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd39
        return cancelCommand3;
    }//GEN-END:MVDGetEnd39

    /** This method returns instance for exitCommand1 component and should be called instead of accessing exitCommand1 field directly.//GEN-BEGIN:MVDGetBegin41
     * @return Instance for exitCommand1 component
     */
    public Command get_exitCommand1() {
        if (exitCommand1 == null) {//GEN-END:MVDGetBegin41
            // Insert pre-init code here
            exitCommand1 = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit41
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd41
        return exitCommand1;
    }//GEN-END:MVDGetEnd41

    /** This method returns instance for quitScreen component and should be called instead of accessing quitScreen field directly.//GEN-BEGIN:MVDGetBegin43
     * @return Instance for quitScreen component
     */
    public Alert get_quitScreen() {
        if (quitScreen == null) {//GEN-END:MVDGetBegin43
            // Insert pre-init code here
            quitScreen = new Alert("Quit Screen", "Barbu Dent Bleu Soccer by ArkaCorp. www.chris-ferrario.com", null, AlertType.INFO);//GEN-BEGIN:MVDGetInit43
            quitScreen.addCommand(get_okCommand1());
            quitScreen.setCommandListener(this);
            quitScreen.setTimeout(-2);//GEN-END:MVDGetInit43
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd43
        return quitScreen;
    }//GEN-END:MVDGetEnd43

    /** This method returns instance for okCommand1 component and should be called instead of accessing okCommand1 field directly.//GEN-BEGIN:MVDGetBegin44
     * @return Instance for okCommand1 component
     */
    public Command get_okCommand1() {
        if (okCommand1 == null) {//GEN-END:MVDGetBegin44
            // Insert pre-init code here
            okCommand1 = new Command("Ok", Command.OK, 1);//GEN-LINE:MVDGetInit44
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd44
        return okCommand1;
    }//GEN-END:MVDGetEnd44

    /** This method returns instance for gameList component and should be called instead of accessing gameList field directly.//GEN-BEGIN:MVDGetBegin51
     * @return Instance for gameList component
     */
    public List get_gameList() {
        if (gameList == null) {//GEN-END:MVDGetBegin51
            // Insert pre-init code here
            gameList = new List("Available Devices", Choice.IMPLICIT, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit51
            gameList.addCommand(get_cancelCommand1());
            gameList.setCommandListener(this);
            gameList.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit51
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd51
        return gameList;
    }//GEN-END:MVDGetEnd51
    
    
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
    
}
