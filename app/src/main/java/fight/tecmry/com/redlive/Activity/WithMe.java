package fight.tecmry.com.redlive.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationsQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import fight.tecmry.com.redlive.Adapter.WithItemAdapter;
import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/24.
 */

public class WithMe extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private WithItemAdapter adapter;
    private Toolbar toolbar;
    private SwipeRefreshLayout refreshLayout;
    private List<AVIMConversation> mList = new ArrayList<AVIMConversation>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withme);
        init();
    }
    private void init()
    {
      recyclerView = (RecyclerView)findViewById(R.id.with_Rv);
        toolbar = (Toolbar)findViewById(R.id.with_toolbar);
        toolbar.setTitle("与我相关");
        with();
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.fresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                with();
                refreshLayout.setRefreshing(false);
            }
        });
    }
    private void with()
    {
        AVIMClient client = LCChatKit.getInstance().getClient();
        AVIMConversationsQuery query = client.getConversationsQuery();
        query.findInBackground(new AVIMConversationQueryCallback() {
            @Override
            public void done(List<AVIMConversation> list, AVIMException e) {
                if (e==null)
                {
                    Log.d("WithMe", String.valueOf(list.size()));
                    mList = list;
                    adapter = new WithItemAdapter(mList,getApplicationContext());
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //refreshLayout.setRefreshing(false);
                }else {
                    Toast.makeText(WithMe.this,"是不是没联网呢",Toast.LENGTH_SHORT).show();
                    Log.d("Witheme",e.toString());
                }

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}
