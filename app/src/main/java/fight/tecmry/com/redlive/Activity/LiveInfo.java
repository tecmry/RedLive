package fight.tecmry.com.redlive.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/21.
 */

public class LiveInfo extends AppCompatActivity implements View.OnClickListener{
    private TextView livename;
    private TextView livetalk;
    private TextView AuthoeName;

    private String author;
    private Button button;
    private String ConversationID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liveinfo);
        init();
    }
    private void init()
    {
        livename = (TextView)findViewById(R.id.Tv_Tiitle);
        livetalk = (TextView)findViewById(R.id.Tv_livetalk);
        AuthoeName = (TextView)findViewById(R.id.authorname);
        button = (Button)findViewById(R.id.Bt_addLive);
        button.setOnClickListener(this);

        Intent intent = getIntent();
        author = intent.getStringExtra("Author");
        String Title = intent.getStringExtra("Title");
        ConversationID = intent.getStringExtra("ConversationId");
        livename.setText(Title);
        AuthoeName.setText(author);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Bt_addLive:
                /**
                Intent intent = new Intent(getApplicationContext(), LCIMConversationActivity.class);
                System.out.println(author);
                intent.putExtra(LCIMConstants.PEER_ID, author);
                startActivity(intent);*/
                join();
                break;
        }
    }
    private void join(){
        LCChatKit.getInstance().getClient().open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e==null)
                {
                    final AVIMConversation conversation = avimClient.getConversation(ConversationID);
                    conversation.join(new AVIMConversationCallback() {
                        @Override
                        public void done(AVIMException e) {
                            if (e==null)
                            {
                                Intent intent = new Intent(LiveInfo.this, LCIMConversationActivity.class);
                                intent.putExtra(LCIMConstants.CONVERSATION_ID, conversation.getConversationId());
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }
}

