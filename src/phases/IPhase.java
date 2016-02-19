package phases;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by MillerLJ on 2/18/2016.
 */
public interface IPhase {
    public void execute() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
