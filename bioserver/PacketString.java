/*
Server Emulator 1
*/

package bioserver;

import java.nio.ByteBuffer;

/**
 *  object for representation of strings in packetdata
 */
public class PacketString {
    private byte[] buffer;
    
    // construct it from a string
    public PacketString(String string) {
        this.buffer = string.getBytes();
    }
    
    // convert to data used in packets
    public byte[] getData() {
        ByteBuffer zwi = ByteBuffer.wrap(new byte[buffer.length + 2]);
        zwi.putShort((short) buffer.length);
        zwi.put(buffer);
        return zwi.array();
    }
}
