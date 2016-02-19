package phases;

import gui.UmlWrapper;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by MillerLJ on 2/18/2016.
 */
public class Print implements IPhase {

	private UmlWrapper wrapper;

	public Print(UmlWrapper wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public void execute() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		wrapper.generateGraph();
	}
}
