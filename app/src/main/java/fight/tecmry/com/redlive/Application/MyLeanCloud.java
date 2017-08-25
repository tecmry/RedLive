package fight.tecmry.com.redlive.Application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import cn.leancloud.chatkit.LCChatKit;
import fight.tecmry.com.redlive.Util.CustomProvider;

public class MyLeanCloud extends Application {
    private static final String APP_ID =  "8gYICsgijm604TfN1LQG9Vb3-gzGzoHsz";
    private static final String APP_KEY = "MVLOl54w9TLQgwlCkfh8MAVo";
        @Override
        public void onCreate() {
            super.onCreate();
            // 初始化参数依次为 this, AppId, AppKey
            AVOSCloud.initialize(this, APP_ID,  APP_KEY);
            LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
            LCChatKit.getInstance().setProfileProvider(CustomProvider.getInstance());
        }


    }
