package fight.tecmry.com.redlive.Application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import cn.leancloud.chatkit.LCChatKit;

public class MyLeanCloud extends Application {
    private static final String APP_ID =  "mG3iiIfnY4daycqwRO44DY7m-gzGzoHsz";
    private static final String APP_KEY = "9J3aci9tjqMCFTutQ0eLfY08";
        @Override
        public void onCreate() {
            super.onCreate();
            // 初始化参数依次为 this, AppId, AppKey
            AVOSCloud.initialize(this, APP_ID,  APP_KEY);
            LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        }


    }
