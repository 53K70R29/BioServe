/*
Server Emulator 1
*/

package bioserver;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Handle / Nickname pair
 */
public class HNPair {
    private byte[] handle;
    private byte[] nickname;
    
    // constructor for given handle and nickname Byte
    public HNPair(byte[] handle, byte[] nickname) {
        this.handle = handle;
        this.nickname = nickname;
    }
    
    // constructor for given handle and nickname array
    public HNPair(String handle, String nickname) {
        this.handle = handle.getBytes();
        try {
            this.nickname = nickname.getBytes("SJIS");
        } catch (UnsupportedEncodingException ex) {
            this.nickname = "sjis".getBytes();
        }
    }

    // create a random handle
    public void createHandle(Database db) {
        byte[] d = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").getBytes();
        boolean flag = false;
        while(!flag) {
            for(int i=0; i<6; i++) {
                Double dd = 36*Math.random();
                this.handle[i] = d[dd.intValue()];
            }
            flag = db.checkHandle(handle.toString());
        }
    }
    
    public byte[] getHandle() {
        return handle;
    }
    
    public byte[] getNickname() {
        return nickname;
    }
    
    // create an array with the HNpair
    public byte[] getHNPair() {
        byte[] hnpair = new byte[handle.length + nickname.length + 4];
        
        hnpair[0] = 0;
        hnpair[1] = 6;
        System.arraycopy(handle, 0, hnpair, 2, 6);
        hnpair[8] = 0;
        hnpair[9] = (byte) nickname.length;
        System.arraycopy(nickname, 0, hnpair, 10, nickname.length);
        
        return hnpair;
    }
    
}
