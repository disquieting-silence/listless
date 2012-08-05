package dsq.listless.screens.main;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.TextView;
import dsq.listless.R;
import dsq.listless.action.AddItemAction;
import dsq.listless.action.ManageScrollsAction;
import dsq.listless.db.app.AppDb;
import dsq.listless.db.app.ListlessDb;
import dsq.listless.db.general.DefaultSimpleDbAdapter;
import dsq.listless.db.general.SimpleDbAdapter;
import dsq.listless.db.item.DefaultItemMapping;
import dsq.listless.db.item.ItemTable;
import dsq.listless.db.scroll.ScrollTable;
import dsq.listless.db.world.WorldDbAdapter;
import dsq.listless.db.world.DefaultWorldDbAdapter;
import dsq.listless.db.world.World;
import dsq.listless.screens.general.*;
import dsq.listless.ui.DefaultPlainViewBinder;
import dsq.listless.ui.PlainViewBinder;
import dsq.listless.xml.SidebarIconLayout;
import dsq.thedroid.db.DbLifecycle;
import dsq.thedroid.db.DbUtils;
import dsq.thedroid.db.DefaultDbLifecycle;
import dsq.thedroid.ui.*;
import dsq.thedroid.util.DefaultStateExtractor;
import dsq.thedroid.util.StateExtractor;
import dsq.listless.action.Action;
import dsq.listless.action.NewScrollAction;

import java.io.Serializable;

// FIX 4/08/12 Split up.
public class Main extends ListActivity
{

    private final DbLifecycle lifecycle = new DefaultDbLifecycle();
    private SQLiteDatabase db;
    private SimpleDbAdapter iAdapter;
    private SimpleDbAdapter sAdapter;
    private final Lists lists = new DefaultLists();
    private final ListMapping mapping = new DefaultItemMapping();
    private final Menus menus = new DefaultMenus();
    
    private static final String CONTEXT_DIALOG = "CONTEXT_DIALOG";
    
    private TextDialog textDialog = new DefaultTextDialog(this);
    private StateExtractor extractor = new DefaultStateExtractor();
    
    private World world;
    private WorldDbAdapter wAdapter;
    private final String NEW_TITLE = "New";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_list);

        db = lifecycle.open(this, AppDb.value);
        iAdapter = new DefaultSimpleDbAdapter(db, ListlessDb.ITEM_TABLE);
        sAdapter = new DefaultSimpleDbAdapter(db, ListlessDb.SCROLL_TABLE);
        wAdapter = new DefaultWorldDbAdapter(db);

        registerForContextMenu(getListView());

        addButtonAction(R.id.manage_scrolls, new ManageScrollsAction());
        addButtonAction(R.id.add_item, new AddItemAction());

        final long scrollId = getScrollId(savedInstanceState);
        openScrollId(scrollId);
    }

    private void addButtonAction(final int buttonId, final Action action) {
        final SidebarIconLayout layout = (SidebarIconLayout)findViewById(buttonId);
        layout.setAction(this, action);
    }

    private long getScrollId(final Bundle savedInstanceState) {
        final Serializable id = extractor.extract(ItemTable.SCROLL_ID, savedInstanceState, getIntent());
        if (id == null) {
            final World w = wAdapter.fetch();
            return w.current;
        } else {
            return Long.parseLong(String.valueOf(id));
        }
    }
    
    private void openScrollId(long id) {
        final Cursor cursor = sAdapter.fetchById(id);
        final String title = DbUtils.getColumn(cursor, ScrollTable.TITLE);
        setScrollTitle(title);
        world = new World(id);
        wAdapter.update(world);
        refreshList();
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }

    private void refreshList() {
        final PlainViewBinder binder = new DefaultPlainViewBinder();
        final Cursor cursor = iAdapter.fetchByValue(ItemTable.SCROLL_ID, String.valueOf(world.current));
        lists.refresh(this, cursor, getListView(), new ComponentIndex(R.layout.main_list_row), mapping, binder);
        // FIX 28/04/12 Close the cursor here?
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id) {
            case Dialogs.ADD_ITEM_TITLE: {
                return textDialog.dialog("Item: ", "", new DialogListener() {
                    @Override
                    public void onSuccess(final String value) {
                        final ContentValues values = new ContentValues();
                        values.put(ItemTable.NAME, value);
                        values.put(ItemTable.SCROLL_ID, world.current);
                        iAdapter.create(values);
                        refreshList();
                    }
                });
            }
            case Dialogs.EDIT_ITEM_TITLE: {
                final Intent intent = getIntent();
                final long itemId = intent.getLongExtra(CONTEXT_DIALOG, -1);
                intent.removeExtra(CONTEXT_DIALOG);
                final Cursor cursor = iAdapter.fetchById(itemId);
                cursor.moveToFirst();
                final String name = DbUtils.getColumn(cursor, ItemTable.NAME);
                return textDialog.dialog("Item: ", name, new DialogListener() {
                    @Override
                    public void onSuccess(final String value) {
                        iAdapter.updateById(itemId, ItemTable.NAME, value);
                        refreshList();
                    }
                });
            }
        }
        return null;
    }

    private void setScrollTitle(final String value) {
        final TextView scrollTitle = (TextView)findViewById(R.id.scroll_title);
        scrollTitle.setText(value);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menus.context(this, menu, R.menu.main_context);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode == RESULT_OK && requestCode == Requests.MANAGE_SCROLLS) {
            openScrollId(data.getLongExtra(ItemTable.SCROLL_ID, -1));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_item: {
                showDialogForId(Dialogs.EDIT_ITEM_TITLE, info.id);
                return true;
            }
            case R.id.delete_item: {
                iAdapter.deleteById(info.id);
                refreshList();
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }
    
    private void showDialogForId(int dialog, long id) {
        Intent intent = new Intent();
        intent.putExtra(CONTEXT_DIALOG, id);
        this.setIntent(intent);
        removeDialog(dialog);
        showDialog(dialog);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putLong(ItemTable.SCROLL_ID, world.current);
    }
}
