package dsq.listless.action;

import android.app.Activity;
import android.content.Intent;
import dsq.listless.screens.general.Requests;
import dsq.listless.screens.manage.Manage;

public class ManageScrollsAction implements Action {
    @Override
    public void run(final Activity activity) {
        final Intent intent = new Intent(activity, Manage.class);
        activity.startActivityForResult(intent, Requests.MANAGE_SCROLLS);
    }
}
