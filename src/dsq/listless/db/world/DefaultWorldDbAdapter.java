package dsq.listless.db.world;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dsq.thedroid.db.DbAccess;
import dsq.thedroid.db.DbUtils;
import dsq.thedroid.db.DefaultDbAccess;

public class DefaultWorldDbAdapter implements WorldDbAdapter {
    
    private final SQLiteDatabase db;
    private final DbAccess access = new DefaultDbAccess();

    public DefaultWorldDbAdapter(final SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    // FIX 28/04/12 Dupe with settings in ersatz.
    // FIX 28/04/12 Would be good to have option here.
    public World fetch() {
        final Cursor cursor = access.query(db, "SELECT * FROM " + WorldTable.TABLE_NAME, new String[0]);
        final int current = cursor.moveToFirst() ? readValue(cursor) : -1;
        final World r = new World(current);
        cursor.close();
        return r;
    }

    @Override
    public boolean update(final World world) {
       // FIX 28/04/12 Should have this on db access.
        db.execSQL(("DELETE FROM " + WorldTable.TABLE_NAME));
        final ContentValues values = new ContentValues();
        values.put(WorldTable.CURRENT, world.current);
        return db.insert(WorldTable.TABLE_NAME, null, values) > 0;
    }
    
    private int readValue(Cursor cursor) {
        return Integer.parseInt(DbUtils.getColumn(cursor, WorldTable.CURRENT));
    }
}
