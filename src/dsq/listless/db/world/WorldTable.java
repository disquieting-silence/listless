package dsq.listless.db.world;

import dsq.thedroid.db.Table;

public class WorldTable implements Table {

    public static final String TABLE_NAME = "WORLD";

    public static final String CURRENT = "current";

    public static final String[] ALL_COLUMNS = new String[] { CURRENT };

    @Override
    public String create() {
        return
            "CREATE TABLE " + TABLE_NAME + "(" +
                CURRENT + " integer not null" +
            ")";
    }

    @Override
    public String name() {
        return TABLE_NAME;
    }

    @Override
    public String drop() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Override
    public String[] load() {
        return new String[0];
    }

    @Override
    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}
