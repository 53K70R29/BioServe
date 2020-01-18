/*
    Server Emulator 1
*/

package bioserver;

public class Area {
    // status of the area
    public static final byte STATUS_ACTIVE = 3;
    public static final byte STATUS_INACTIVE = 0;
    
    private int nr;
    private String name;
    private String description;
    private byte status;

    public Area(int number, String name, String description, byte status) {
        this.nr = number;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    
    public byte getStatus() {
        return status;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
}
