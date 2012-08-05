package dsq.listless.db.scroll;

import android.database.DatabaseUtils;
import dsq.thedroid.db.Table;

public class ScrollTable implements Table {
    
    public static final String TABLE_NAME = "SCROLL";
    public static final String ID = "_id";
    public static final String TITLE = "title";
    
    private static final String[] ALL_COLUMNS = new String[] { ID, TITLE };

    @Override
    public String name() {
        return TABLE_NAME;
    }

    @Override
    public String create() {
        return 
            "CREATE TABLE " + TABLE_NAME + "(" + 
                ID + " integer primary key autoincrement, " +
                TITLE + " text not null" +                
            ")";
    }

    @Override
    public String drop() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
    
    private String sql(String title) {
        return "INSERT INTO " + TABLE_NAME + "(" + TITLE + ") VALUES(" + DatabaseUtils.sqlEscapeString(title) + ");";
    }

    @Override
    // FIX 28/04/12 Really need to stop SQL injection ...
    public String[] load() {
        return new String[] {
            sql("Shopping List"),
            sql("DVDs")
        };
    }

    @Override
    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}