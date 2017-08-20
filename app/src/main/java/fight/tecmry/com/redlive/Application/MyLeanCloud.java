package fight.tecmry.com.redlive.Application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloud extends Application {

        @Override
        public void onCreate() {
            super.onCreate();

            // 初始化参数依次为 this, AppId, AppKey
            AVOSCloud.initialize(this, "8gYICsgijm604TfN1LQG9Vb3-gzGzoHsz", "MVLOl54w9TLQgwlCkfh8MAVo");

        }


    }
