/*
Server Emulator 1
*/

package bioserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *  Organize the urls in lobby
 * 
 *  preliminary class, we need url to data mapping
 *  currently always the same page is delivered
 * 
 */
public class Information {
    
    // beware of the quotes, they need to be backslashed !
    String contents_s =
            "<HTML>"
            + "<HEAD>"
            + "<!--"
            + "	<GAME-STYLE>"
            + "		\"MOUSE=OFF\","
            + "		\"SCROLL=OFF\","
            + "		\"TITLE=OFF\","
            + "		\"BACK=ON:mmbb://BUTTON_NG\","
            + "		\"FORWARD=OFF\","
            + "		\"CANCEL=OFF\","
            + "		\"RELOAD=OFF\","
            + "		\"CHOICE_MV=OFF\","
            + "		\"X_SHOW=OFF\","
            + "		\"FRONT_LABEL=ON:6\","
            + "	</GAME-STYLE>"
            + "-->"
            + "<TITLE>database</TITLE><meta http-equiv=\"Content-Type\" content=\"text/html; charset=Shift_JIS\"></HEAD>"
            + ""
            + "<BODY bgcolor=\"#000033\" text=#FFFFFF>"
            + "<!-- Choices -->"
            + "<br>"
            + "<IMG SRC=\"\" width=0 height=0 USEMAP=#CENTER_MAP BORDER=0>"
            + "<MAP NAME=CENTER_MAP>"
            + "<!--CHG-IMG-BUTTON-2--><AREA SHAPE=RECT COORDS=\"164, 30,416, 60\" HREF=lbs://lbs/02/INFOR/INFOR00.HTM>"
            + "<!--CHG-IMG-BUTTON-2--><AREA SHAPE=RECT COORDS=\"164, 92,416,118\" HREF=lbs://lbs/02/RANKING.HTM>"
            + "<!--CHG-IMG-BUTTON-2--><AREA SHAPE=RECT COORDS=\"164,154,416,219\" HREF=afs://02/2>"
            + "<!--CHG-IMG-BUTTON-2--><AREA SHAPE=RECT COORDS=\"164,216,416,266\" HREF=afs://02/4>"
            + "</MAP> "
            + ""
            + ""
            + "<table width=584 cellspacing=30 cellpadding=0>"
            + "  <tr> "
            + "    <td align=center>&nbsp;</td>"
            + "    <td width=256 height=32 align=center background=afs://02/123.PNG>INFORMATION</td>"
            + "    <td align=center>&nbsp;</td>"
            + "  </tr>"
            + "  <tr> "
            + "    <td align=center>&nbsp;</td>"
            + "    <td width=256 height=32 align=center background=afs://02/123.PNG>RANKING</td>"
            + "    <td align=center>&nbsp;</td>"
            + "  </tr>"
            + "  <tr> "
            + "    <td align=center>&nbsp;</td>"
            + "    <td width=256 height=32 align=center background=afs://02/123.PNG>TERMS OF USE</td>"
            + "    <td align=center>&nbsp;</td>"
            + "  </tr>"
            + "  <tr> "
            + "    <td align=center>&nbsp;</td>"
            + "    <td width=256 height=32 align=center background=afs://02/123.PNG>REGISTER / CHANGE</td>"
            + "    <td align=center>&nbsp;</td>"
            + "  </tr>"
            + "</table>"
            + "</BODY>"
            + "</HTML>";
    
    public Information() {
        
    }
    
    // retrieve the desired URL
    public byte[] getData(String url) {
        byte[] content_b = null;
        url = ("htm/"+url).replace("..", "X");
        Logging.println("requested url: "+url);
        try {
            content_b = Files.readAllBytes(Paths.get(url));
        } catch (IOException ex) {
            Logging.println("Error reading file: "+url);
            content_b = contents_s.getBytes();
        }
        return content_b;
    }    
}
