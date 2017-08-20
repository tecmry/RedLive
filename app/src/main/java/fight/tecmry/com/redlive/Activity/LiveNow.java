package fight.tecmry.com.redlive.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.util.Arrays;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livenow);
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
             sendSomeThing();
                getSomeThing();
            }
        }).start();
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

    private void sendSomeThing()
    {
        final AVIMClient client = AVIMClient.getInstance("Tom");
        client.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e==null)
                {
                    client.createConversation(Arrays.asList("Jerry"), "Tom&Jerry", null, new AVIMConversationCreatedCallback() {
                        @Override
                        public void done(AVIMConversation avimConversation, AVIMException e) {
                            if (e==null)
                            {
                                AVIMTextMessage msg = new AVIMTextMessage();
                                msg.setText("起床");
                                msg.setContent("起床");
                                avimConversation.sendMessage(msg, new AVIMConversationCallback() {
                                    @Override
                                    public void done(AVIMException e) {
                                        if (e==null)
                                        {
                                            Log.d("Tom&Jerry","发送成功");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
    public void getSomeThing() {
        AVIMClient jerry = AVIMClient.getInstance("Jerry");
        jerry.open(new AVIMClientCallback() {

            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    Log.d("Jerry", "登录成功");
                }
            }
        });
            class CustomMessageHandler extends AVIMMessageHandler {
            @Override
            public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
                // 新消息到来了。在这里增加你自己的处理代码。
                Log.d("Tom&Jerry", ((AVIMTextMessage) message).getText());
                Log.d("Tom&Jerry", message.getContent().toString());
            }
        }
        AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());
    }
}

