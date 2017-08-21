package fight.tecmry.com.redlive.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.util.ArrayList;
import java.util.List;

import fight.tecmry.com.redlive.Adapter.TalkItemAdapter;
import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/17.
 */

public class LiveNow extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView Rv;
    private EditText mEditText;
    private ImageButton Left_Button;
    private ImageButton Right_Button;

    private TalkItemAdapter itemAdapter;

    private AVIMConversation conversation;

    private List<String> list = new ArrayList<String>();
    private AVUser avUser;
    private AVUser alittleuser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        avUser = AVUser.getCurrentUser();
        AVQuery<AVUser> userQuery = new AVQuery<>("_User");
        userQuery.getInBackground("5999b35e61ff4b0058ee9bfb", new GetCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    alittleuser = avUser;
                }
            });

        init();

    }
    private void init()
    {
        Rv = (RecyclerView)findViewById(R.id.Live_Rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Rv.setLayoutManager(layoutManager);
        mEditText = (EditText)findViewById(R.id.editText);

        Left_Button = (ImageButton) findViewById(R.id.imageButton);
        Right_Button = (ImageButton)findViewById(R.id.imageButton2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imageButton2:
                break;
        }
    }

    private void openClient(String name)
    {
        AVIMClient client = AVIMClient.getInstance(name);
        client.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                sendTextMessage(avimClient);
            }
        });
    }
    private void sendTextMessage(AVIMClient client)
    {
        client.createConversation(list, "LLL", null, new AVIMConversationCreatedCallback() {
            @Override
            public void done(AVIMConversation avimConversation, AVIMException e) {
                AVIMTextMessage message = new AVIMTextMessage();
                message.setText(String.valueOf(mEditText.getText()));
                avimConversation.sendMessage(message, new AVIMConversationCallback() {
                    @Override
                    public void done(AVIMException e) {
                        if (e==null)
                        {
                            Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

 }

