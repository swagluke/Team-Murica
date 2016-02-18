package phases;

import gui.UmlWrapper;

/**
 * Created by MillerLJ on 2/18/2016.
 */
public class Load implements IPhase {
    UmlWrapper wrapper;
    public Load(UmlWrapper wrapper) {
        this.wrapper=wrapper;
    }

    @Override
    public void execute() {

    }
}
