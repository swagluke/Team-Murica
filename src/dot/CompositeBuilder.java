package dot;

import records.IClassRecord;

import java.util.HashMap;

/**
 * Created by MillerLJ on 2/5/2016.
 */
public class CompositeBuilder extends APatternBuilder {
    public CompositeBuilder(IBuilder b) {
        super(b);
    }

    @Override
    public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap) {
        return false;
    }

    @Override
    public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap) {

    }
}
