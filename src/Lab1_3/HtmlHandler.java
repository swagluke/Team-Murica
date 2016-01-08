package Lab1_3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukezhang on 12/5/15.
 */
public class HtmlHandler implements DirectoryEventHandler
{
    private String command = "explorer";
    List<String> supportfilentype = new ArrayList<String>();

    public HtmlHandler() {
        supportfilentype.add("html");
        supportfilentype.add("htm");
    }

    @Override
    public Process changeprocess(String fileName, String eventName) throws IOException
    {
        // We are only interested in the new files that get dropped into the launcher folder
        if(!eventName.equals("ENTRY_CREATE"))
            return null;

        String filetype = fileName.split("\\.(?=[^\\.]+$)")[1];
        if(!supportfilentype.contains(filetype))
            return null;

        System.out.format("Launching %s ...%n", command);
        ProcessBuilder processBuilder = new ProcessBuilder(command, fileName);

        // Start and add the process to the processes list
        return processBuilder.start();
    }
}

