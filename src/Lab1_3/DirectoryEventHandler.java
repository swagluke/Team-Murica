package Lab1_3;

import java.io.IOException;

/**
 * Created by lukezhang on 12/5/15.
 */
public interface DirectoryEventHandler
{
    public Process changeprocess(String filename, String eventName) throws IOException;
}
