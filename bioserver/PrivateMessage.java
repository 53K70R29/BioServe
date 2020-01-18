/*
Server Emulator 1
*/

package bioserver;

import java.nio.ByteBuffer;

/**
 * Object for the private messaging system
 */
public class PrivateMessage {
    private byte[] senderhandle;
    private byte[] sendername;
    private byte[] recipient;
    private byte[] message;
    
    public PrivateMessage(byte[] senderhandle, byte[] sendername, byte[] recipient, byte[] message) {
        this.senderhandle = senderhandle;
        this.sendername = sendername;
        this.recipient = recipient;
        this.message = message;
    }
    
    public byte[] getRecipient() {
        return this.recipient;
    }
    
    public byte[] getSenderHandle() {
        return this.senderhandle;
    }
    
    public byte[] getMessage() {
        return this.message;
    }
    
    // create a packet for broadcast
    public byte[] getPacketData() {
        ByteBuffer z = ByteBuffer.wrap(new byte[200]);
        
        // handle and name of sender
        z.putShort((short) senderhandle.length);
        z.put(senderhandle);
        z.putShort((short) sendername.length);
        z.put(sendername);
        
        // message
        z.putShort((short) message.length);
        z.put(message);
        
        byte[] retval = new byte[z.position()];
        z.rewind();
        z.get(retval);
        return retval;
    }
}
