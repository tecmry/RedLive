package fight.tecmry.com.redlive.Application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import cn.leancloud.chatkit.LCChatKit;

public class MyLeanCloud extends Application {
    private static final String APP_ID =  "8gYICsgijm604TfN1LQG9Vb3-gzGzoHsz";
    private static final String APP_KEY = "MVLOl54w9TLQgwlCkfh8MAVo";
        @Override
        public void onCreate() {
            super.onCreate();
            // 初始化参数依次为 this, AppId, AppKey
            AVOSCloud.initialize(this, "8gYICsgijm604TfN1LQG9Vb3-gzGzoHsz", "MVLOl54w9TLQgwlCkfh8MAVo");
            LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        }


    }
