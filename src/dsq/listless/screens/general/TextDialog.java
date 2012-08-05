package dsq.listless.screens.general;

import android.app.AlertDialog;

public interface TextDialog {
    AlertDialog dialog(final String message, String initial, final DialogListener listener);
}
