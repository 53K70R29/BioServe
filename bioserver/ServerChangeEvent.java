/*
Server Emulator 1
*/
package bioserver;

import java.nio.channels.SocketChannel;

/**
 * Object for the request queue
 */
public class ServerChangeEvent {
    public static final int REGISTER = 1;
    public static final int CHANGEOPS = 2;
    public static final int FORCECLOSE = 3;
    
    public SocketChannel socket;
    public int type;
    public int ops;

    public ServerChangeEvent(SocketChannel socket, int type, int ops) {
        this.socket = socket;
        this.type = type;
        this.ops = ops;
    }
} 