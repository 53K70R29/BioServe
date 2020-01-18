/*
Server Emulator 1
*/

package bioserver;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for initialisation
 */
public class ServerMain {
    // the server listens on this port
    public final static int LOBBYPORT = 8300;
    public final static int GAMEPORT = 8690;		// if you change this, search for gs_info
    
    // Entry point
    public static void main(String[] args) {

        System.out.println("------------------------------\n"+
                           "-     OUTBREAK BIOSERVER     -\n"+
                           "-           53K70R           -\n"+
                           "-                            -\n"+
                           "-         (c) 2020           -\n"+
                           "-                            -\n"+
                           "------------------------------\n");

        // setup the packethandler in his own thread
        PacketHandler packethandler = new PacketHandler();
        new Thread(packethandler).start();

        // create the server thread
        ServerThread server = new ServerThread(null, LOBBYPORT, packethandler);
        new Thread(server).start();
        
        // create a simple gameserver
        GameServerPacketHandler packethandler2 = new GameServerPacketHandler();
        new Thread(packethandler2).start();
        GameServerThread gsserver = new GameServerThread(null, GAMEPORT, packethandler2);
        new Thread(gsserver).start();
        
        // allow usage
        packethandler.setGameServerPacketHandler(packethandler2);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // last but not least
        // create a thread for the keepalivepings and cleanups
        new Thread(new HeartBeatThread(server, packethandler, gsserver, packethandler2)).start();
        
        Date date = new Date();
        System.out.println(date.toString()+" server started");      
    }
}