package dsq.listless.db.app;

import dsq.listless.db.item.ItemTable;
import dsq.listless.db.scroll.ScrollTable;
import dsq.listless.db.world.WorldTable;
import dsq.thedroid.db.Db;
import dsq.thedroid.db.Table;

public class ListlessDb implements Db {
    
    public static final Table SCROLL_TABLE = new ScrollTable();
    public static final Table ITEM_TABLE = new ItemTable();
    public static final Table WORLD_TABLE = new WorldTable();
    
    @Override
    public int version() {
        return 11;
    }

    @Override
    public String name() {
        return "LISTLESS";
    }

    @Override
    public Table[] tables() {
        return new Table[] {
            SCROLL_TABLE,
            ITEM_TABLE,
            WORLD_TABLE
        };
    }
}
