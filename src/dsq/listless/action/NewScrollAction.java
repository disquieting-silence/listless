package dsq.listless.action;

import android.app.Activity;
import dsq.listless.screens.general.Dialogs;

public class NewScrollAction implements Action {
    @Override
    public void run(final Activity activity) {
        activity.showDialog(Dialogs.ADD_SCROLL_TITLE);
    }
}
