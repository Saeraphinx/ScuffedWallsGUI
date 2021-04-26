import java.io.*;

public class ScuffedApplication {
    private String folderPath;
    private File SWexe;

    public ScuffedApplication(String path) {
        SWexe = new File(path);
        folderPath = SWexe.getParentFile().toString();
    }

    public boolean isValid(){
        if(SWexe.isFile() && SWexe.canExecute()) {
            return true;
        } else {
            return false;
        }
    }

    public String getFileName() {
        return SWexe.getName();
    }

    public void launch() throws IOException {
        // NOTE: Don't use this it's not what I want.
        Runtime.getRuntime().exec(folderPath, null, SWexe);
    }

}
