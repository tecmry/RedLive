package fight.tecmry.com.redlive.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.avos.avoscloud.AVObject;

import java.util.ArrayList;
import java.util.List;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/15.
 */

public class State_Fragment extends Fragment implements View.OnClickListener{
    private final static  String TAG = "state_fragment";

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;

    private List<AVObject> list = new ArrayList<AVObject>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_place,container,false);
        init(view);

        return view;
    }

    private void init(View view)
    {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.Rv);
        mProgressBar = (ProgressBar)view.findViewById(R.id.second_progress);
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

    @Override
    public void onClick(View view) {

    }
}
