package fight.tecmry.com.redlive.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.View.AudioRecorderButton;

/**
 * Created by Tecmry on 2017/8/15.
 */

public class HomePage_Fragment extends Fragment {
    private final static  String TAG = "homepage_fragment";
    private AudioRecorderButton button;
    private int setRedio = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_place,container,false);
        if (Build.VERSION.SDK_INT>=23){
            int checkpermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO);
            if (checkpermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.RECORD_AUDIO},setRedio);
            }
        }
        return view;
    }


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
