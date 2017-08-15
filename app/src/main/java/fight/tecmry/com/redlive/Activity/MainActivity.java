    package fight.tecmry.com.redlive.Activity;

    import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
    import android.util.Log;

    import fight.tecmry.com.redlive.Fragment.homepage_fragment;
    import fight.tecmry.com.redlive.Fragment.state_fragment;
    import fight.tecmry.com.redlive.Fragment.user_fragment;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new homepage_fragment()).commit();
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("MainActivity",tab.getText().toString());
                switch (tab.getText().toString()){
                    case "首页":
                        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new homepage_fragment()).commit();
                        break;
                    case "圈子":
                        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new state_fragment()).commit();
                        break;
                    case "我的":
                        getSupportFragmentManager().beginTransaction().replace(R.id.add_Fragment,new user_fragment()).commit();
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
