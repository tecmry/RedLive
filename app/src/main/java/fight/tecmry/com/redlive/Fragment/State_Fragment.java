package fight.tecmry.com.redlive.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import java.util.ArrayList;
import java.util.List;

import fight.tecmry.com.redlive.Activity.Enter;
import fight.tecmry.com.redlive.Activity.LiveNow;
import fight.tecmry.com.redlive.Adapter.ShowItemAdapter;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.Constant;

/**
 * Created by Tecmry on 2017/8/15.
 */

public class State_Fragment extends Fragment implements View.OnClickListener{
    private final static  String TAG = "state_fragment";

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private TextView creativeLive;
    private ShowItemAdapter itemAdapter;
    private List<AVObject> list = new ArrayList<AVObject>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_place,container,false);
        findList();
        init(view);
        return view;
    }

    private void init(View view)
    {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.Rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //对List进行非空判断
        if (list.size()!=0) {
            itemAdapter = new ShowItemAdapter(list,getContext());
            mRecyclerView.setAdapter(itemAdapter);
            mRecyclerView.setLayoutManager(layoutManager);
        }

        mProgressBar = (ProgressBar)view.findViewById(R.id.second_progress);

        creativeLive = (TextView)view.findViewById(R.id.creative_Live);
        creativeLive.setOnClickListener(this);
    }

    private void findList()
    {
      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  AVQuery<AVObject> query = new AVQuery<AVObject>("LiveItem");
                  list = query.find();
              } catch (AVException e) {
                  e.printStackTrace();
              }
          }
      }).start();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.creative_Live:
                if (Constant.User.isLogin())
                {
                    startActivity(new Intent(getContext(), LiveNow.class));
                }else {
                    Toast.makeText(getContext(),"未登录用户请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), Enter.class));
                }
                break;
        }
    }

    private void initData(View view)
    {}
    /**
     * 生命周期
     * */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

}
