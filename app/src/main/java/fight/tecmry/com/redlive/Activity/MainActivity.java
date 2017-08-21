    package fight.tecmry.com.redlive.Activity;

    import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
    import android.widget.Toast;

    import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import cn.leancloud.chatkit.LCChatKit;
import fight.tecmry.com.redlive.Fragment.HomePage_Fragment;
import fight.tecmry.com.redlive.Fragment.State_Fragment;
import fight.tecmry.com.redlive.Fragment.User_Fragment;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.Constant;

    public class MainActivity extends AppCompatActivity {
        private TabLayout tableLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tableLayout = (TabLayout) findViewById(R.id.tab_layout2);


            AVObject testObject = new AVObject("TestObject");
            setTab();

            if (Constant.User.isLogin())
            {
                startOpen();
            }
        }


        private void setTab() {
            getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment, new HomePage_Fragment()).commit();
            tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Log.d("MainActivity", tab.getText().toString());
                    switch (tab.getText().toString()) {
                        case "首页":
                            getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment, new HomePage_Fragment()).commit();
                            break;
                        case "圈子":
                            getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment, new State_Fragment()).commit();
                            break;
                        case "我的":
                            getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment, new User_Fragment()).commit();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

        private void startOpen() {
            LCChatKit.getInstance().open(Constant.User.avuser.getUsername(), new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this,"hahahha",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        protected void onResume() {
            super.onResume();
            if (Constant.User.isLogin())
            {
                startOpen();
            }
        }
    }
