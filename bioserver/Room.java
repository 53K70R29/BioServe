/*
Server Emulator 1
*/

package bioserver;

/**
 * Object for rooms within areas
 */
public class Room {
    public static final byte STATUS_ACTIVE = 3;
    public static final byte STATUS_INACTIVE = 0;
    
    private int areanumber;
    private String name;
    private byte status;
    
    public Room(int area, String name, byte status) {
        this.name = name;
        this.status = status;
        this.areanumber = area;
    }
    
    public String getName() {
        return this.name;
    }
    
    public byte getStatus() {
        return this.status;
    }
    
    public int getAreaNumber() {
        return this.areanumber;
    }

}
