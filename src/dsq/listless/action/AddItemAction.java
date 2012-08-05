package dsq.listless.action;

import android.app.Activity;
import dsq.listless.screens.general.Dialogs;

public class AddItemAction implements Action {
    @Override
    public void run(final Activity activity) {
        activity.removeDialog(Dialogs.ADD_ITEM_TITLE);
        activity.showDialog(Dialogs.ADD_ITEM_TITLE);
    }
}
