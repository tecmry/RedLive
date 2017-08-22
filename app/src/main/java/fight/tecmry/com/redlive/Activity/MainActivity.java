    package fight.tecmry.com.redlive.Activity;

    import android.Manifest;
    import android.content.pm.PackageManager;
    import android.os.Bundle;
    import android.support.design.widget.TabLayout;
    import android.support.v4.app.ActivityCompat;
    import android.support.v4.content.ContextCompat;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;

    import com.avos.avoscloud.AVObject;
    import com.avos.avoscloud.im.v2.AVIMClient;
    import com.avos.avoscloud.im.v2.AVIMException;
    import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

    import java.util.ArrayList;
    import java.util.List;

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

            getPermission();
            if (Constant.User.isLogin())
            {
              // startOpen();
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
                      //  Toast.makeText(MainActivity.this,"hahahha",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private void getPermission()
        {
            List<String> permissionList = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }/**
            if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.RECORD_AUDIO)
                    ==PackageManager.PERMISSION_GRANTED){
                permissionList.add(Manifest.permission.RECORD_AUDIO);
            }*/
            if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE
            )==PackageManager.PERMISSION_GRANTED){
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!permissionList.isEmpty())
            {
                String [] permission = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(MainActivity.this,permission,1);
            }
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
