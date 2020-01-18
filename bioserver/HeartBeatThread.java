/*
Server Emulator 1
*/

package bioserver;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Send a PING packet to all connected clients every 30 secs
 */
public class HeartBeatThread implements Runnable {
    PacketHandler packethandler;
    GameServerPacketHandler gspackethandler;
    ServerThread server;
    GameServerThread gsserver;    
    int counter, counter2;
    
    public HeartBeatThread(ServerThread server, PacketHandler packethandler, 
                           GameServerThread gsserver, GameServerPacketHandler packethandler2) {
        this.packethandler = packethandler;
        this.gspackethandler = packethandler2;
        this.server = server;
        this.gsserver = gsserver;
    }
    
    @Override
    public void run() {
        counter = 0;
        counter2 = 0;
        
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HeartBeatThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // loop forever
        while(true) {
            try {
                // servers send every 30 secs that he is alive, no answer needed
                packethandler.broadcastPing(server);
                
                // remove dead clients in gameserver
                gspackethandler.connCheck(gsserver);
                
                // also check if any slots have to be autostarted
                packethandler.checkAutoStart(server);

                // server asks for client every 60 secs
                // client will be deleted from list and disconnected if not answered
                if(counter == 1) {
                    packethandler.broadcastConnCheck(server);
                    counter = 0;
                } else {
                    counter++;
                }
                
                // clean up the rooms every 5 minutes
                if(counter2 == 9) {
                    packethandler.cleanGhostRooms(server);
                    counter2 = 0;
                } else {
                    counter2++;
                }

                // sleep for 30 secs
                Thread.sleep(30*1000);
            } catch (InterruptedException ex) {
                Logging.println("Heartbeat exception caught!");
            }
        }
    }
}
