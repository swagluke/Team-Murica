package Lab1_3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukezhang on 12/5/15.
 */
public class ModifyHandler implements DirectoryEventHandler
{

    @Override
    public Process changeprocess(String fileName, String eventName) throws IOException
    {
        // We are only interested in the new files that get dropped into the launcher folder
        if(!eventName.equals("ENTRY_CREATE"))
            return null;

        System.out.format(fileName + " has been modified");
        return null;
    }
}
