package dsq.listless.action;

import android.app.Activity;

public class CancelAction implements Action {
    @Override
    public void run(final Activity activity) {
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }
}
