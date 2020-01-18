/*
Server Emulator 1
*/

package bioserver;

/**
 * special class for multiple HNPairs
 */
public class HNPairs {
    // byte number of ids (max 3)
    // int length id (6)
    // string id
    // int length 
    // string handle (16)
    // word end marker

    // for simplicity we create a maxmimum buffer for 3 handles
    private byte[] hnpairs = new byte[85];
    private int count;
    private int length;
    
    public HNPairs() {
        count = 0;
        length = 1;
    }
    
    public byte[] getArray() {
        hnpairs[0] = (byte) count;  // set count

        byte[] retval = new byte[length];
        System.arraycopy(this.hnpairs, 0, retval, 0, length);
        return retval;
    }
    
    // add a pair to buffer
    public void add(HNPair hnpair) {
        byte[] hn = hnpair.getHNPair();
        
        System.arraycopy(hn, 0, hnpairs, length, hn.length);
        length = length+hn.length;
        hnpairs[length++] = 0;
        hnpairs[length++] = 0;  // add end marker

        count++;
    }
    
}