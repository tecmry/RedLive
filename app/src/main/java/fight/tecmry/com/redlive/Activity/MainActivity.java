    package fight.tecmry.com.redlive.Activity;

    import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
    import android.util.Log;

    import fight.tecmry.com.redlive.Fragment.HomePage_Fragment;
    import fight.tecmry.com.redlive.Fragment.State_Fragment;
    import fight.tecmry.com.redlive.Fragment.User_Fragment;
    import fight.tecmry.com.redlive.R;

    public class MainActivity extends AppCompatActivity {
private TabLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (TabLayout)findViewById(R.id.tab_layout2);


        setTab();
    }



    private void setTab(){
        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new HomePage_Fragment()).commit();
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("MainActivity",tab.getText().toString());
                switch (tab.getText().toString()){
                    case "首页":
                        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new HomePage_Fragment()).commit();
                        break;
                    case "圈子":
                        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new State_Fragment()).commit();
                        break;
                    case "我的":
                        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new User_Fragment()).commit();
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
}
