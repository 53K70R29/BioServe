/*
Server Emulator 1
*/

package bioserver;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * information about the clients
 */
public class Client {
    private SocketChannel socket;
    private String userid;
    private String session;
    private HNPair hnpair;      // chosen handle / nickname
    private byte[] characterstats;  // 0xd0 in len
    private short character;
    private short costume;

    private int area;           // special case 51 = post-game lobby
    private int room;
    private int slot;
    public int gamenumber;
    private byte player;        // number of this player (1-4)
    
    public boolean connalive;   // set back every 60 secs or be disconnected ;-)

    private byte host;          // this is the host of a gameslot

    public Client(SocketChannel socket, String userid, String session) {
        this.socket = socket;
        this.userid = userid;
        this.session = session;
        this.area = 0;      // in no area (area selection screen)
        this.room = 0;      // in no room
        this.slot = 0;      // in no slot
        this.host = 0;
        this.connalive = true;  // begin with active connection
    }
    
    public SocketChannel getSocket() {
        return socket;
    }
    
    public String getUserID() {
        return userid;
    }

    public HNPair getHNPair() {
        return hnpair;
    }
    
    public void setHNPair(HNPair hnpair) {
        this.hnpair = hnpair;
    }
    
    public void setCharacterStats(byte[] charstats) {
        this.characterstats = charstats;
        this.character = (short) (charstats[0xc8] & 0x00ff);
        // npc start with MAC = 9
        this.character = (short) (this.character + (short) (8 * charstats[0xca] & 0x00ff));
        this.costume = (short) (charstats[0xcc] & 0x00ff);
    }
    
    public void setArea(int number) {
        this.area = number;
    }
    
    public int getArea() {
        return this.area;
    }
    
    public void setRoom(int number) {
        this.room = number;
    }
    
    public int getRoom() {
        return this.room;
    }

    public void setSlot(int number) {
        this.slot = number;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public byte[] getCharacterStats() {
        return this.characterstats;
    }

    public byte getHostFlag() {
        return this.host;
    }
    
    public void setHostFlag(byte number) {
        this.host = number;
    }

    public void setPlayerNum(byte number) {
        this.player = number;
    }

    public byte getPlayerNum() {
        return this.player;
    }
    
    public byte[] getPreGameStat(byte playernum) {
        ByteBuffer z = ByteBuffer.wrap(new byte[300]);
        
        z.put(playernum);
        z.put((byte) 1);
        z.put(this.getHNPair().getHNPair());
        z.putShort((short) this.characterstats.length);
        z.put(this.characterstats);
        z.put((byte) 0);
        z.put((byte) 0);
        z.put((byte) 6);
        
        byte[] retval = new byte[z.position()];
        z.rewind();
        z.get(retval);
        return retval;
    }
    
   public byte[] getCharacterStat() {
        ByteBuffer z = ByteBuffer.wrap(new byte[300]);
        
        z.put(this.getHNPair().getHNPair());
        z.putShort((short) this.characterstats.length);
        z.put(this.characterstats);
        
        byte[] retval = new byte[z.position()];
        z.rewind();
        z.get(retval);
        return retval;
    }

   public short getCharacter() {
       return this.character;
   }

   public short getCostume() {
       return this.costume;
   }
}
