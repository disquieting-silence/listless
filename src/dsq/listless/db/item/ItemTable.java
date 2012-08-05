package dsq.listless.db.item;

import android.database.DatabaseUtils;
import dsq.listless.db.scroll.ScrollTable;
import dsq.thedroid.db.Table;

public class ItemTable implements Table {
    
    public static final String TABLE_NAME = "ITEM";
    public static final String ID = "_id";
    public static final String SCROLL_ID = "scroll_id";
    public static final String NAME = "name";
    
    private static final String[] ALL_COLUMNS = new String[] { ID, SCROLL_ID, NAME};

    @Override
    public String name() {
        return TABLE_NAME;
    }

    @Override
    public String create() {
        return 
            "CREATE TABLE " + TABLE_NAME + "(" + 
                ID + " integer primary key autoincrement, " +
                SCROLL_ID + " integer, " +
                NAME + " text not null, " +
                "FOREIGN KEY (" + SCROLL_ID + ") REFERENCES " + ScrollTable.TABLE_NAME + "(" + ScrollTable.ID + ") ON DELETE CASCADE" +
            ")";
    }

    @Override
    public String drop() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
    
    private String sql(int scrollId, String name) {
        return "INSERT INTO " + TABLE_NAME + "(" + SCROLL_ID + ", " + NAME + ") VALUES(" + String.valueOf(scrollId) + ", " +
            DatabaseUtils.sqlEscapeString(name) + ");";
    }

    @Override
    // FIX 28/04/12 Really need to stop SQL injection ...
    public String[] load() {
        return new String[] {
            sql(1, "Frozen Peas"),
            sql(0, "Vitamin E Cream"),
            sql(1, "Pizzas"),
            sql(1, "Measuring Cup"),
            sql(1, "Vegetable Oil"),
            sql(1, "Golden Syrup"),
            sql(1, "Bandaids"),
            sql(1, "Walnuts"),
            sql(1, "Icing Sugar"),
            sql(1, "Plates")
        };
    }

    @Override
    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}