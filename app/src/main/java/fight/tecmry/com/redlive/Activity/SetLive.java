package fight.tecmry.com.redlive.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/18.
 */

public class SetLive extends AppCompatActivity implements View.OnClickListener
{
     private EditText Et_setLivename;
     private EditText Et_setLivetalk;
     private EditText Et_setFacepeople;

    //判断是否满足上传的条件
    private boolean isNext = true;
    private static final String AvDependent = "dependent";
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AVObject LiveItem = new AVObject("LiveItem");
                    AVUser avUser = AVUser.getCurrentUser();
                    LiveItem.put("name",Livename);
                    LiveItem.put("talk",LiveTalk);
                    LiveItem.put("facepeople",FacePeople);
                    LiveItem.put(AvDependent,avUser);
                    LiveItem.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e==null)
                            {
                                Toast.makeText(getApplicationContext(),"你的Live信息已经上传成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Log.d("SetLive",e.toString());
                            }
                        }
                    });
                }
            }).start();
        }
    }

    private void CheckHaveLogin()
    {
        if (AVUser.getCurrentUser()==null)
        {
            isNext = false;
        }

    }
}
