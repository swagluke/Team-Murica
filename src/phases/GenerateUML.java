package phases;

import gui.UmlWrapper;

/**
 * Created by MillerLJ on 2/18/2016.
 */
public class GenerateUML implements IPhase {
    UmlWrapper wrapper;

    public GenerateUML(UmlWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void execute() {
        wrapper.generateUML();
    }
}
