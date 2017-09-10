package fight.tecmry.com.redlive.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

import fight.tecmry.com.redlive.Bean.ImageData;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.ImageInterface;
import fight.tecmry.com.redlive.Util.NetWorkUntils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tecmry on 2017/8/23.
 */

public class StartActivity extends AppCompatActivity {

    /***
     * 在网速较差的情况下没法进行图片加载
     * */
    private ImageView imageView;
    private TextView textView;
    public static final String BASE_URL="http://www.bing.com";
    private String addUrl = "http://s.cn.bing.net";
    private Handler handler;
    private static final int imageurl = 1;
    private static final int right = 2;
    private static Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        chexk();
        mContext = getApplicationContext();
        imageView = (ImageView)findViewById(R.id.start_image);
        textView = (TextView)findViewById(R.id.start_TextView);
        if (NetWorkUntils.isNetWorkConnected(mContext))
        {
            NetUtil();
        }else
        {
            Toast.makeText(StartActivity.this,"无网络链接",Toast.LENGTH_SHORT).show();
        }
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case imageurl:
                        Glide.with(StartActivity.this).load(msg.obj).into(imageView);
                        break;
                    case right:
                        textView.setText((CharSequence) msg.obj);
                        break;
                }
            }
        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);
    }
    private void NetUtil()
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               Retrofit retrofit =new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();
               ImageInterface imageInterface = retrofit.create(ImageInterface.class);
               Call<ImageData> call = imageInterface.getUrl("js","1","7");
               call.enqueue(new Callback<ImageData>() {
                   @Override
                   public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                       ImageData imageData = response.body();
                       Random random = new Random();
                       int c = random.nextInt(7);
                       String url =addUrl+imageData.getImages().get(c).getUrl();
                       String copyright = imageData.getImages().get(c).getCopyright();
                       Log.d("StartActivity",copyright);
                       Toast.makeText(StartActivity.this,copyright,Toast.LENGTH_SHORT).show();
                       Message message = Message.obtain();
                       message.obj = url;
                       message.what = imageurl;
                       Message message1 = Message.obtain();
                       message1.obj = copyright;
                       message1.what = right;
                       handler.sendMessage(message1);
                       handler.sendMessage(message);
                   }

                   @Override
                   public void onFailure(Call<ImageData> call, Throwable t) {
                       Log.d("StartActivity",t.toString());
                   }
               });
           }
       }).start();
    }
    private void chexk(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else {
            View decoreview=getWindow().getDecorView();
            decoreview.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        }
    }
}
