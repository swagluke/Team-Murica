package Lab1_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukezhang on 12/5/15.
 */
public class BackReadHandler implements DirectoryEventHandler
{
    List<String> supportfilentype = new ArrayList<String>();

    public BackReadHandler() {
        supportfilentype.add("txt");
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
        System.out.println("Reading file backwards begins now.");
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                for(int i = line.length()-1;i>=0;i--)
                {
                    sb.append(line.charAt(i));
                }
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.println(everything);
        } finally {
            br.close();
        }
        // Start and add the process to the processes list
        return null;
    }
}
