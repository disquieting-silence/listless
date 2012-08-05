package dsq.listless.db.item;

import dsq.listless.R;
import dsq.thedroid.ui.ComponentIndex;

public class DefaultItemMapping implements ItemMapping {
    @Override
    public String[] source() {
        return new String[] {
            ItemTable.NAME
        };
    }

    @Override
    public ComponentIndex[] dest() {
        return new ComponentIndex[] {
            new ComponentIndex(R.id.item)
        };
    }
}
