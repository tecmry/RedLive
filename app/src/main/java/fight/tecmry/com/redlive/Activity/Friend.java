package fight.tecmry.com.redlive.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.Constant;
import fight.tecmry.com.redlive.Util.GlideCircleTransform;

/**
 * Created by Tecmry on 2017/8/25.
 */

public class Friend extends AppCompatActivity {

    private ImageView Iv_gotoTalk;
    private ImageView Iv_user;
    private TextView Tv_email;
    private TextView Tv_username;
    private TextView Tv_hobby;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frienditem);
        init();
    }
    private void init()
    {
        Tv_hobby = (TextView)findViewById(R.id.textView11);
        Tv_username = (TextView)findViewById(R.id.username);
        Tv_email = (TextView)findViewById(R.id.textView5);
        Iv_user = (ImageView)findViewById(R.id.Iv_userimage);
        Iv_gotoTalk = (ImageView)findViewById(R.id.users_talk);
        intent = getIntent();
        String nm = intent.getStringExtra("Username");
        net(nm);
        Iv_gotoTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            create();
            }
        });
    }
    private void create()
    {
        List<String> idList = new ArrayList<>();
        idList.add(Constant.User.avuser.getUsername());
        LCChatKit.getInstance().getClient().createConversation(
                idList, Constant.User.avuser.getUsername(), null, false, true, new AVIMConversationCreatedCallback() {
                    @Override
                    public void done(AVIMConversation avimConversation, AVIMException e) {
                        if (e==null)
                        {
                            Intent intent = new Intent(Friend.this, LCIMConversationActivity.class);
                            intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                            startActivity(intent);
                            finish();
                        }else
                            {
                                System.out.println("Friend" + e.toString());
                                Toast.makeText(Friend.this,e.toString(),Toast.LENGTH_SHORT).show();
                            }
                    }
                }
        );
    }
    private void net(final String name)
        {
            AVQuery<AVUser> userQuery = new AVQuery<>("_User");
            userQuery.whereEqualTo("username",name);
            userQuery.findInBackground(new FindCallback<AVUser>() {
                @Override
                public void done(List<AVUser> list, AVException e) {
                    if (e==null)
                    {
                       try {
                           AVUser avUser = list.get(0);
                           final String username = avUser.getUsername();
                           final String url = (String) avUser.get("imageUrl");
                           System.out.println("imageUrl" + url);
                           final String email = avUser.getEmail();
                           final String hobby = (String) avUser.get("hobby");
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   Tv_username.setText(username);
                                   Tv_email.setText(email);
                                   Tv_hobby.setText(hobby);
                                   if (!url.isEmpty()) {
                                       Glide.with(Friend.this).load(url).
                                               transform(new GlideCircleTransform(getApplicationContext()))
                                               .into(Iv_user);
                                   }
                               }
                           });
                       }catch (IndexOutOfBoundsException se){
                           se.printStackTrace();
                       }
                    }
                }
            });
        }

}
