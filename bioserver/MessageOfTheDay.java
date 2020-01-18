/*
Server Emulator 1
*/

package bioserver;

import java.nio.ByteBuffer;

/**
 * Object for Message of the day
 */
public class MessageOfTheDay {
    private byte number;
    private String message;
    
    public MessageOfTheDay(int number, String message) {
        this.message = message;
        this.number = (byte) number;
    }
    
    public byte[] getPacket() {
        byte[] retval = new byte[3 + message.length()];
        retval[0] = number;
        if(message.length() == 0) retval[0] = 0;
        retval[1] = (byte)(message.length() >> 8);
        retval[2] = (byte)(message.length() & 0xFF);
        System.arraycopy(message.getBytes(), 0, retval, 3, message.length());
        return(retval);
    }
}