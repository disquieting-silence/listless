package dsq.listless.db.scroll;

import dsq.listless.R;
import dsq.thedroid.ui.ComponentIndex;

public class DefaultScrollMapping implements ScrollMapping {
    @Override
    public String[] source() {
        return new String[] {
            ScrollTable.TITLE
        };
    }

    @Override
    public ComponentIndex[] dest() {
        return new ComponentIndex[] {
            new ComponentIndex(R.id.scroll)
        };
    }
}
