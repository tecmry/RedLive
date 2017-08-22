package fight.tecmry.com.redlive.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.Constant;

/**
 * Created by Tecmry on 2017/8/18.
 */

public class SetLive extends AppCompatActivity implements View.OnClickListener
{
     private EditText Et_setLivename;
     private EditText Et_setLivetalk;
     private EditText Et_setFacepeople;

    private LinearLayout LoginFormView;
    private ProgressBar mProgressBar;

    private Toolbar mToolbar;
    //判断是否满足上传的条件
    private boolean isNext = true;
    private static final String AvDependent = "dependent";

    private AVObject LiveItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlive);
        init();
        CheckHaveLogin();
    }
    private void init()
    {
        Et_setFacepeople = (EditText)findViewById(R.id.Et_setterpeople);
        Et_setLivename = (EditText)findViewById(R.id.setLive_Etlivename);
        Et_setLivetalk = (EditText)findViewById(R.id.setLive_livetalk);
        Et_setFacepeople.setOnClickListener(this);
        Et_setLivetalk.setOnClickListener(this);
        Et_setLivename.setOnClickListener(this);

        LoginFormView = (LinearLayout)findViewById(R.id.setLive_main);
        mProgressBar = (ProgressBar)findViewById(R.id.login_progress);

        mToolbar = (Toolbar)findViewById(R.id.setLive_Toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Et_setterpeople:
                break;
            case R.id.setLive_livename:
                break;
            case R.id.setLive_livetalk:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.usertoolbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_yes:
                //将收集到的数据上传
                Push();
                break;
            default:
                return  super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Push()
    {
      final String Livename = Et_setLivename.getText().toString();
        final String LiveTalk = Et_setLivetalk.getText().toString();
        final String FacePeople = Et_setFacepeople.getText().toString();

        View FoucusView = null;
        /**
         * 下面做一些条件判断
         * */
        if (Livename.isEmpty())
        {
            isNext = false;
            FoucusView = Et_setLivename;
            Et_setLivename.setError("名字不能为空");
        }
        if(LiveTalk.isEmpty()){
            isNext = false;
            FoucusView = Et_setLivetalk;
            Et_setLivetalk.setError("不能为空");
        }
        if (FacePeople.isEmpty()){
            isNext = false;
            FoucusView = Et_setFacepeople;
            Et_setFacepeople.setError("不能为空");
        }
        if (isNext)
        {
            showProgress(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                     LiveItem = new AVObject("LiveItem");
                    AVUser avUser = AVUser.getCurrentUser();
                    LiveItem.put("name",Livename);
                    LiveItem.put("talk",LiveTalk);
                    LiveItem.put("facepeople",FacePeople);
                    LiveItem.put("Author",avUser.getUsername());
                    LiveItem.put(AvDependent,avUser);
                    LiveItem.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e==null)
                            {
                                setLive(Livename);
                                Toast.makeText(getApplicationContext(),"你的Live信息已经上传成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Log.d("SetLive",e.toString());
                            }
                        }
                    });
                }
            }).start();
        }
    }

    /**
     * 在创建Live的时候进行创建Conversation
     * */
    private void setLive(String name)
    {
        List<String> idList = new ArrayList<>();
        idList.add(Constant.User.avuser.getUsername());
        LCChatKit.getInstance().getClient().createConversation(
                idList,name, null, false, true, new AVIMConversationCreatedCallback() {
                    @Override
                    public void done(final AVIMConversation avimConversation, AVIMException e) {
                      //  final Intent intent = new Intent(SetLive.this, LCIMConversationActivity.class);
                        LiveItem.put("ConvresationId",avimConversation.getConversationId());
                        LiveItem.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                 /**
                                intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                                  startActivity(intent);
                            */if (e==null)
                            {
                                Toast.makeText(SetLive.this,"Success",Toast.LENGTH_SHORT).show();
                            }
                            }
                        });
                    }
                });
    }

    //判断是否登录
    private void CheckHaveLogin()
    {
        if (AVUser.getCurrentUser()==null)
        {
            isNext = false;
        }

    }
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            LoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            LoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    LoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            LoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
