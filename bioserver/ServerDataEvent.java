/*
Server Emulator 1
*/

package bioserver;

import java.nio.channels.SocketChannel;

/**
 * Object for the packet server is sending
 */
class ServerDataEvent {
    public ServerThread server;
    public SocketChannel socket;
    public byte[] data;

    public ServerDataEvent(ServerThread server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }    
}
