/*
Server Emulator 1
*/

package bioserver;

import java.nio.channels.SocketChannel;

/**
 * Object for the packet server is sending
 */
class GameServerDataEvent {
    public GameServerThread server;
    public SocketChannel socket;
    public byte[] data;

    public GameServerDataEvent(GameServerThread server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }    
}
