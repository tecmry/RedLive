package fight.tecmry.com.redlive.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/25.
 */

public class SelectPopUpWindow extends PopupWindow
{
    private View mainview;
   public SelectPopUpWindow(Activity activity)
   {
       super(activity);
       Log.d("SelectPopUpWindow","我执行了");
       LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       mainview = inflater.inflate(R.layout.popitem,null);


       this.setContentView(mainview);
       //设置宽度
       this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

       this.setFocusable(true);

       ColorDrawable drawable = new ColorDrawable(0xb0000000);
       this.setBackgroundDrawable(drawable);

      mainview.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              int height = mainview.findViewById(R.id.pop_parent).getHeight();
              int y =(int)event.getY();
              if (event.getAction()==MotionEvent.ACTION_UP)
              {
                  if (y<height)
                  {
                      dismiss();
                  }
              }
            return true;
          }
      });
   }
}
