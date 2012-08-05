package dsq.listless.xml;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import dsq.listless.R;
import dsq.listless.action.Action;

public class SidebarIconLayout extends LinearLayout {
    
    private final ImageButton button;

    public SidebarIconLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SidebarIconLayout);
        final String value = attributes.getString(R.styleable.SidebarIconLayout_icon);
        
        button = new ImageButton(context);

        final int drawable = context.getResources().getIdentifier(value, "drawable", context.getPackageName());
        button.setBackgroundResource(drawable);

        final LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        
        addView(button);
    }
    
    /** Really wish this was an interface .... */
    public void setAction(final Activity activity, final Action action) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                action.run(activity);
            }
        });
    }
}
