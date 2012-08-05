package dsq.listless.screens.general;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.WindowManager;
import android.widget.EditText;

public class DefaultTextDialog implements TextDialog {
    
    private final Activity activity;

    public DefaultTextDialog(final Activity activity) {
        this.activity = activity;
    }

    @Override
    public AlertDialog dialog(final String message, final String initial, final DialogListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setCancelable(true);
        final EditText text = new EditText(activity);
        text.setText(initial);
        builder.setView(text);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int id) {
                listener.onSuccess(text.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int i) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return dialog;
    }
}
