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
import com.avos.avoscloud.SignUpCallback;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/16.
 */

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText UserName;
    private EditText PassWords;

    private ImageButton Cancel;

    private TextView HaveCount;
    private TextView GotoRegister;

    private View  LoginFormView;
    private ProgressBar  ProgressView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        init();
    }
    private void init()
    {
        UserName = (EditText)findViewById(R.id.register_countname);
        UserName.setOnClickListener(this);
        PassWords = (EditText)findViewById(R.id.register_passwords);
        PassWords.setOnClickListener(this);

        Cancel = (ImageButton)findViewById(R.id.renter_cancel);
        Cancel.setOnClickListener(this);

        ProgressView = (ProgressBar)findViewById(R.id.login_progress);
        LoginFormView = (LinearLayout)findViewById(R.id.LL);

        HaveCount = (TextView)findViewById(R.id.havecount);
        HaveCount.setOnClickListener(this);

        GotoRegister = (TextView)findViewById(R.id.register_Tv);
        GotoRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.register_Tv:
                attempt();
                break;
        }
    }
    private void attempt()
    {
            UserName.setError(null);
            PassWords.setError(null);
            final String username = UserName.getText().toString();
            final String passwords = PassWords.getText().toString();
            boolean cancel = false;
            View focusView = null;
        if(!TextUtils.isEmpty(passwords)&&!isPasswordValid(passwords)){
           PassWords.setError("密码大于四位");
            focusView = PassWords;
            cancel = true;
        }

        if(TextUtils.isEmpty(username)){
            UserName.setError("这个是必填项");
            focusView = UserName;
            cancel = true;
        }
        if(cancel)
        {
            focusView.requestFocus();
        }else {
            showProgress(true);
            AVUser user = new AVUser();
            user.setUsername(username);
            user.setPassword(passwords);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if(e == null){
                        Intent intent = new Intent(Register.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        showProgress(false);
                        Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private boolean isPasswordValid(String passwords){
        return passwords.length() > 6;
    }
    private void showProgress(final boolean show){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB_MR2){
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            LoginFormView.setVisibility(show?View.VISIBLE:View.GONE);
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
