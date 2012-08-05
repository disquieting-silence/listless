package dsq.listless.db.general;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import dsq.thedroid.db.DbAccess;
import dsq.thedroid.db.DefaultDbAccess;
import dsq.thedroid.db.Table;

public class DefaultSimpleDbAdapter implements SimpleDbAdapter {

    private final SQLiteDatabase db;
    private final Table table;
    private DbAccess access = new DefaultDbAccess();
    
    public DefaultSimpleDbAdapter(final SQLiteDatabase db, final Table table) {
        this.db = db;
        this.table = table;        
    }

    @Override
    public boolean deleteById(final long id) {
        return access.deleteById(db, table.name(), id);
    }

    @Override
    public Cursor fetchAll() {
        return access.fetchAll(db, table.name(), table.allColumns());
    }

    @Override
    public Cursor fetchById(final long id) throws SQLException {
        return access.fetchById(db, table.name(), table.allColumns(), id);
    }

    @Override
    public long create(ContentValues values) {
        return access.create(db, table.name(), values);
    }

    @Override
    public boolean updateById(final long id, final String field, final String value) {
        final ContentValues values = new ContentValues();
        values.put(field, value);
        return updateById(id, values);
    }

    @Override
    public boolean updateById(final long id, final ContentValues values) {
        return access.update(db, table.name(), id, values);
    }

    @Override
    public Cursor fetchByValue(final String field, final String value) {
        return access.fetch(db, table.name(), table.allColumns(), field + "=?", new String[] { value });
    }
}
