package fight.tecmry.com.redlive.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import cn.leancloud.chatkit.LCChatKit;
import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/16.
 */

public class Enter extends AppCompatActivity implements View.OnClickListener{
    private EditText UserName;
    private EditText PassWords;

    private View  LoginFormView;
    private ProgressBar  ProgressView;

    private ImageButton Cancel;

    private TextView GoToRegister;
    private TextView GoToEnter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        init();
    }

    private void init()
    {
        UserName = (EditText)findViewById(R.id.enter_countname);
        UserName.setOnClickListener(this);
        PassWords = (EditText)findViewById(R.id.enter_passwords);
       PassWords.setOnClickListener(this);

        GoToEnter = (TextView)findViewById(R.id.enter_Tv);
        GoToEnter.setOnClickListener(this);
        GoToRegister = (TextView)findViewById(R.id.enter_gotoregister);
        GoToRegister.setOnClickListener(this);
        LoginFormView = (LinearLayout)findViewById(R.id.login_form);
        ProgressView = (ProgressBar)findViewById(R.id.login_progress);
        Cancel = (ImageButton)findViewById(R.id.enter_cancel);
        Cancel.setOnClickListener(this);
    }

    private void attemptLogin()
    {
        final String username = UserName.getText().toString();
        final String userpasswords = PassWords.getText().toString();

        View focusview = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(userpasswords)&&!isPasswordValid(userpasswords))
        {
            PassWords.setError("密码要大于6位");
            focusview = PassWords;
            cancel = true;
        }
        if(TextUtils.isEmpty(username))
        {
            UserName.setError("用户名不能为空");
            focusview = UserName;
            cancel = true;
        }
        if (cancel)
        {
            focusview.requestFocus();
        }else
            {
                showProgress(true);
                AVUser.logInInBackground(username, userpasswords, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if(e == null){
                            LCChatKit.getInstance().open(username, new AVIMClientCallback() {
                                @Override
                                public void done(AVIMClient avimClient, AVIMException e) {
                                    if (e==null)
                                    {
                                        Intent intent = new Intent(Enter.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        showProgress(false);
                                        Toast.makeText(Enter.this,e.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            showProgress(false);
                            Toast.makeText(Enter.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
    }

    @Override
    public void onClick(View view) {
     switch (view.getId())
     {
         case R.id.enter_gotoregister:
             Intent intent =  new Intent(Enter.this,Register.class);
             startActivity(intent);
             finish();
             break;
         case R.id.enter_Tv:
             attemptLogin();
             break;
     }
    }
    private boolean isPasswordValid(String password){
        return password.length() >= 6;
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

            ProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            ProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            ProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            LoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
