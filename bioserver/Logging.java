/*
Server Emulator 1
*/

package bioserver;

import java.util.Date;

/*
 * a simple class to print the hexdump of a byte buffer
 * 
 */
public class Logging {
    // how many bytes should be shown in one line ?
    private static final int WIDTH = 16;

    public static void printBuffer(byte[] buffer, int length) {
        int index = 0;

        while(length>0) {
            // print a full line or rest of the buffer
            int j = Math.min(length, WIDTH);
            for(int i=0; i<j; i++) System.out.printf("%02x ", buffer[index+i]);
            for(int i=0; i<(WIDTH+1-j); i++) System.out.printf("   ");
            for(int i=0; i<j; i++) {
                char c = (char) buffer[index+i];
                System.out.printf("%c", Character.isLetterOrDigit(c)||Character.isSpaceChar(c) ? c:'.');
            }
            System.out.printf("\n");

            length -= WIDTH;
            index  += WIDTH;
        }
    }
	
    // print the complete buffer
    public static void printBuffer(byte[] buffer) {
            printBuffer(buffer, buffer.length);
    }
    
    // print a message to console
    public static void println(String msg) {
        Date date = new Date();
        System.out.println(date.toString()+" "+msg);
    }
}