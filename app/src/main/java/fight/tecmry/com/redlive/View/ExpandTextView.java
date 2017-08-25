package fight.tecmry.com.redlive.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class ExpandTextView extends LinearLayout {
    public ExpandTextView(Context context) {
        super(context);
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attributeSet)
    {
        setOrientation(VERTICAL);

    }
}
