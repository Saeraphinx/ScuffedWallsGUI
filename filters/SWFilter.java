package filters;

import java.io.File;
import javax.swing.filechooser.*;
 
public class SWFilter extends FileFilter {
 
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
 
        String extension = UtilsSW.getExtension(f);
        if (extension != null) {
            if (extension.equals(UtilsSW.sw)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
 
    //The description of this filter
    public String getDescription() {
        return ".sw (Scuffed Walls)";
    }
}

class UtilsSW {
    public final static String sw = "sw";
 
    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}

