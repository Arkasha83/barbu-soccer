package Interface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
/*
 * ClientServer.java
 *
 * Created on December 4, 2006, 1:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Farhan Hameed Khan
 */
public class ClientServer  implements DiscoveryListener{
    
    /** Creates a new instance of ClientServer */
   UUID RFCOMM_UUID = new UUID(0x0003);
   private String m_ServerUrl = "btspp://localhost:" + RFCOMM_UUID + ";name=rfcommtest;authorize=true";
   private StreamConnection m_StrmConn = null;
   private LocalDevice m_LclDevice = null;	
   private InputStream m_Input=null;
   private OutputStream m_Output=null;
   private StreamConnectionNotifier  m_StrmNotf=null;
   public boolean running=false;
   public boolean m_bIsServer=false,m_bServerFound=false,m_bInitServer=false,m_bInitClient=false; 
   private static String m_strUrl;
   private final String SEVER_RESPONSE= "RUN_THE_GAME",CLIENT_RESPONSE="CLIENT_IS_READY";
   public DiscoveryAgent m_DscrAgent=null;
           
   public String connectionInfo;
   
    public ClientServer(boolean isServer) 
    {
        connectionInfo = "";
        running = true;
        m_bIsServer = isServer;
        
        if(m_bIsServer)
        {
            System.out.println("InitServer");
            InitServer();
        }
        else
        {
            System.out.println("InitClient");
            InitClient();
        }
    }
    
    private void InitServer()
    {
        
        m_strUrl= "btspp://localhost:" + RFCOMM_UUID + ";name=rfcommtest;authorize=true"; 

     // m_StrmConn = BTFACADE.waitForClient(SERVICE_NBR);
  
        try
        {
              m_LclDevice = LocalDevice.getLocalDevice();
            
              m_LclDevice.setDiscoverable(DiscoveryAgent.GIAC);
          
              m_StrmNotf = (StreamConnectionNotifier)Connector.open(m_strUrl);    
              
              m_StrmConn = m_StrmNotf.acceptAndOpen(); 
              
              m_bInitServer = true;
              
              m_Output = m_StrmConn.openOutputStream();
              m_Input = m_StrmConn.openInputStream();
        }
        catch (BluetoothStateException e)
        {        
            System.err.println( "BluetoothStateException: " + e.getMessage() );    
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println( "Exception: " + e.getMessage() );    
        }
                
    }
    
    private void InitClient()
    {
         SearchAvailDevices();
    }
    
    public boolean TestConnection()
    {
     
      if(m_Input == null || m_Output == null)
        return false;
      
      return true;
    }
    
    public void SearchAvailDevices()
    {
        try
        {
            //First get the local device and obtain the discovery agent. 
            m_LclDevice = LocalDevice.getLocalDevice();        
            m_DscrAgent=  m_LclDevice.getDiscoveryAgent();
            m_DscrAgent.startInquiry(DiscoveryAgent.GIAC,this);
        }
        catch (BluetoothStateException ex) 
        {
            System.out.println("Problem in searching the blue tooth devices");
            ex.printStackTrace();
        }
        
    }
    
    
   public void SendMessages(byte[] v_strData)
  {
       
      if(!TestConnection())
        return;
       
      if((m_bInitClient) || (m_bInitServer) )
      {
            try
            {
                m_Output.write(v_strData.length);
                m_Output.write(v_strData);   
            }
            catch (IOException ex) 
            {
                CloseAll();
                //ex.printStackTrace();
            }
                   
      } 
  }
   
   
   public byte[]  RecieveMessages()
   {
       byte[] data = null;   
       
       try 
       {       	      	
          if(!TestConnection())
            return null;
           
          int length = m_Input.read();       
          data= new byte[length];
          length = 0;
      
          while (length != data.length) 
          {     
                  int ch = m_Input.read(data, length, data.length - length);

                  if (ch == -1)
                  {
                    throw new IOException("Can't read data");
                  }
                  length += ch;
          }      

    
       }
       catch (IOException e) 
       {
             CloseAll();
           
             //System.err.println("IOException");
             //System.err.println(e);
       } 
       
    
       return data;
   }
   
   
   /*********************************************************************************************
   * below are the pure virtual  methods of discoverlistern
   *
   *
   *******************************************************************************************/
  
    
  public void inquiryCompleted(int discType)
  {
      System.out.println("Inquiry complete");
     //connectionInfo = "InquiryCompleted";
  }
  
  //called when service search gets complete
  public void serviceSearchCompleted(int transID, int respCode)
  {
      if(m_bServerFound)
      {
            try
            {   //lets the communication start by setting the url and send client reponse
                m_StrmConn = (StreamConnection) Connector.open(m_strUrl);
               
                m_Output = m_StrmConn.openOutputStream(); 
                m_Input = m_StrmConn.openInputStream();
                 
                m_Output.write(CLIENT_RESPONSE.length());
                m_Output.write(CLIENT_RESPONSE.getBytes());
                
                connectionInfo = "serviceSearchCompleted URL="+m_strUrl;
                //System.out.println("serviceSearchCompleted");
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }   
      }
  }

  void CloseAll()
  {
      //System.out.println("Closing");
        try 
        {
            if(m_Output!=null)
                m_Output.close();
            
            if( m_Input!=null)
                 m_Input.close();
            
            m_Input = null;
            m_Output = null;
            m_StrmConn = null;
            running = false;
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
  }

  
  
  //called when service found during service search
  public void servicesDiscovered(int transID, ServiceRecord[] records)
  {
    
    for (int i = 0; i < records.length; i++)
    {
        m_strUrl = records[i].getConnectionURL(ServiceRecord.AUTHENTICATE_ENCRYPT, false); 
         
        
        System.out.println("URL:"+m_strUrl);
        if(m_strUrl.startsWith("btspp")) //we have found our service protocol 
        {
            //connectionInfo = "servicesDiscovered URL="+m_strUrl;
            m_bServerFound = true;
            m_bInitClient = true;
            
           break;
        }
       
    } 

    
  }

  //Called when device is found during inquiry 
  public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod)
  {
  
      if(m_DscrAgent == null || btDevice == null)
          return;
      
     try 
     {
      // Get Device Info
      System.out.println("Device Discovered");
      System.out.println("Major Device Class: " + cod.getMajorDeviceClass() + " Minor Device Class: " + cod.getMinorDeviceClass());
      System.out.println("Bluetooth Address: " + btDevice.getBluetoothAddress());
      System.out.println("Bluetooth Friendly Name: " + btDevice.getFriendlyName(true));     
      
      // Search for Services
      UUID uuidSet[] = new UUID[1];     
      uuidSet[0] =  RFCOMM_UUID;      
      int searchID = m_DscrAgent.searchServices(null,uuidSet,btDevice,this);      
    }
    catch (Exception e) 
    {
      System.out.println("Device Discovered Error: " + e);	
    }
     
       
  }

}
