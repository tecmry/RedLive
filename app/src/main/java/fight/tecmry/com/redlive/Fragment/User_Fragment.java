package fight.tecmry.com.redlive.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;

import fight.tecmry.com.redlive.Activity.Enter;
import fight.tecmry.com.redlive.Activity.UserEditor;
import fight.tecmry.com.redlive.R;


public class User_Fragment extends Fragment implements View.OnClickListener{
    private final static String TAG = "user_fragment";

    private boolean isEnter = false;

    private ImageView userimage;
    private TextView username;
    private AVUser avUser;

    private TextView OutEnter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_news,container,false);

        init(view);
        return view;
    }

    private void init(View view)
    {
        userimage = (ImageView)view.findViewById(R.id.usernews_userimage);
        userimage.setOnClickListener(this);

        username = (TextView)view.findViewById(R.id.usernews_username);

        OutEnter = (TextView)view.findViewById(R.id.out_enter);
        OutEnter.setOnClickListener(this);

        avUser = AVUser.getCurrentUser();
        if (avUser==null)
        {
                isEnter = false;
        }else if (avUser!=null)
            {
                isEnter = true;
             //捕获下空指针异常
                username.setText(avUser.getUsername());

            }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.usernews_userimage:
                if (isEnter){
                    Intent intent = new Intent(getContext(), UserEditor.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getContext(), Enter.class);
                    startActivity(intent);
                }
                break;
            case R.id.out_enter:
               if (AVUser.getCurrentUser() == null)
               {
                   Toast.makeText(getContext(),"登都没登还退！！",Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getContext(),"您已退出",Toast.LENGTH_SHORT).show();
                   AVUser.getCurrentUser().logOut();
                   username.setText("");
               }
               break;
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


}
