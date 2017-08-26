package fight.tecmry.com.redlive.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/25.
 */

public class searchFriend extends AppCompatActivity
{
    private Toolbar toolbar;
    private TextView textView;
    private CardView cardView;
    private boolean isNext = true;
    private String fuck;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfriend);
        init();
    }
    private void init()
    {
        toolbar = (Toolbar)findViewById(R.id.searchfriend_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        textView = (TextView)findViewById(R.id.friend_username);
        cardView = (CardView)findViewById(R.id.friend_cardview);
        cardView.setVisibility(View.GONE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchFriend.this,Friend.class);
                intent.putExtra("Username",fuck);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("输入用户名");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                net(query);
                fuck = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
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
                   if (list.size()==0){
                       Toast.makeText(searchFriend.this,"查无此人",Toast.LENGTH_SHORT).show();
                   }else
                       {
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   cardView.setVisibility(View.VISIBLE);
                                   textView.setText(name);
                               }
                           });
                       }
                }else {
                    Toast.makeText(searchFriend.this,"要不就是没网络要不就是查无此人",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
