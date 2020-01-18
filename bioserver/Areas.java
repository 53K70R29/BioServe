/*
Server Emulator 1
*/

package bioserver;

import java.util.LinkedList;
import java.util.List;

/**
 * All areas
 */
public class Areas {
    private List areas;
    
    // setup our areas
    public Areas() {
        areas = new LinkedList();
        areas.add(new Area(1, "East Town", "<BODY><SIZE=3>standard rules<END>", Area.STATUS_ACTIVE));
        areas.add(new Area(2, "West Town", "<BODY><SIZE=3>individual games<END>",Area.STATUS_ACTIVE));
    }
    
    // how many areas do we have ?
    public int getAreaCount() {
        return areas.size();
    }
    
    public String getName(int areanumber) {
        Area area = (Area) areas.get(areanumber-1);
        return(area.getName());
    }
    
    public String getDescription(int areanumber) {
        Area area = (Area) areas.get(areanumber-1);
        return(area.getDescription());
    }
    
    public byte getStatus(int areanumber) {
        Area area = (Area) areas.get(areanumber-1);
        return(area.getStatus());
    }
}
