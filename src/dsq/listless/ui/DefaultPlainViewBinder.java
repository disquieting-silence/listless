package dsq.listless.ui;

import android.database.Cursor;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

public class DefaultPlainViewBinder implements PlainViewBinder {
    @Override
    public boolean setViewValue(final View view, final Cursor cursor, final int i) {
//        final boolean isItem = view.getId() == R.id.item;
        return false;
    }

    private boolean strikethrough(final View view, final Cursor cursor, final int i) {
        final TextView item = (TextView)view;
        final int flags = item.getPaintFlags();
        item.setPaintFlags(flags | Paint.STRIKE_THRU_TEXT_FLAG);
        return false;
    }
}
