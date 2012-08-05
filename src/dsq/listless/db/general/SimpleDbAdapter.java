package dsq.listless.db.general;

import android.content.ContentValues;
import android.database.Cursor;
import dsq.thedroid.db.DbAdapter;

public interface SimpleDbAdapter extends DbAdapter {
    long create(ContentValues values);
    boolean updateById(long id, String field, String value);
    boolean updateById(long id, ContentValues values);
    Cursor fetchByValue(String field, String value);
}
