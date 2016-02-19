package phases;

import gui.UmlWrapper;

/**
 * Created by MillerLJ on 2/18/2016.
 */
public class PatternDetection implements IPhase {
    private UmlWrapper wrapper;

    public PatternDetection(UmlWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void execute() {
        wrapper.findPatterns();
    }
}
