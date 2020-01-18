/*
Server Emulator 1
*/

package bioserver;

import java.nio.ByteBuffer;

/**
 *  handling of messages that were fragmented by tcp
 */
public class ServerStreamBuffer {
    // maximum of packetsize to exspect
    private static final int RECEIVESIZE = 8192;
    
    // we need a ByteBuffer
    public ByteBuffer buf = ByteBuffer.allocate(RECEIVESIZE);
    
    // pointer to actual message
    private int messptr;
    
    public ServerStreamBuffer () {
        this.messptr = 0;
    }
    
    // return a buffer with complete messages (lobby)
    public byte[] getCompleteMessages() {
        byte[] b = buf.array();
        int size = this.buf.position()-this.messptr;
        int total = 0;
        int plen;
        
        // enough data for a complete message?
        if(size >= Packet.HEADERSIZE) {
            // determine size of complete messages
            while(size>0) {
                // length of this mesage
                plen = (((int) b[messptr+total+4] << 8)&0xFF00) | ((int) b[messptr+total+5] &0xFF);
                size = size - plen - Packet.HEADERSIZE;
                // if it's a full message, add to total
                if(size >= 0) {
                    total = total + plen + Packet.HEADERSIZE;
                }
            }
        } else {
            // not enough data for a message
            return null;
        }
        
        // total holds the size of our new buffer
        byte[] retval = new byte[total];
        System.arraycopy(b, messptr, retval, 0, total);

        // size indicates fragmentation
        if(size == 0) {
            this.messptr = 0;
            buf.clear();
        } else {
            // let's continue next time
            this.messptr = this.messptr + total;
        }
        
        return retval;
    }
    
    // return a buffer with complete messages (gameserver)
    public byte[] getCompleteGameMessages() {
        byte[] b = buf.array();
        // is it a gameplay packet to check, or a lobby packet ?
        // for simplicity we assume that session packet is always complete!
        if((b[0]==(byte)0x82) && (b[1]==0x02)) {
            byte[] retval = new byte[buf.position()];
            buf.rewind();
            buf.get(retval);
            this.buf.clear();
            return retval;
        }
        
        int size = this.buf.position()-this.messptr;
        int total = 0;
        int plen;
        
        // enough data for a complete message? at least 2 bytes needed!
        if(size >= 1) {
            // determine size of complete messages
            while(size>0) {
                // length of this mesage
                plen = (byte) b[messptr+total] & 0x0FF;
                size = size - plen;
                // if it's a full message, add to total
                if(size >= 0) {
                    total = total + plen;
                }
            }
        } else {
            // not enough data for a message
            return null;
        }
        
        // total holds the size of our new buffer
        byte[] retval = new byte[total];
        System.arraycopy(b, messptr, retval, 0, total);

        // size indicates fragmentation
        if(size == 0) {
            this.messptr = 0;
            this.buf.clear();
        } else {
            // let's continue next time
            this.messptr = this.messptr + total;
        }
        
        return retval;
        
    }
    
}
