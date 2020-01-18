/*
Server Emulator 1
*/

package bioserver;

import java.util.LinkedList;
import java.util.List;

/**
 * a class containing all rooms
 */
public class Rooms {
    private List rooms;
    private int numberOfAreas;
    private final int numberOfRooms = 10;

    public Rooms(int numberOfAreas) {
        rooms = new LinkedList();
        this.numberOfAreas = numberOfAreas;

        for(int i=1; i<=numberOfAreas; i++) {
            rooms.add(new Room(i, "R1", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R2", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R3", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R4", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R5", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R6", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R7", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R8", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "R9", Room.STATUS_ACTIVE));
            rooms.add(new Room(i, "RA", Room.STATUS_ACTIVE));
        }
    }
    
    public byte getStatus(int areanr, int roomnr) {
        Room r = (Room) rooms.get((areanr-1)*this.numberOfRooms + roomnr-1);
        return r.getStatus();
    }
    
    public String getName(int areanr, int roomnr) {
        Room r = (Room) rooms.get((areanr-1)*this.numberOfRooms + roomnr-1);
        return r.getName();
    }

    public int getRoomCount() {
        return this.numberOfRooms;
    }
}
