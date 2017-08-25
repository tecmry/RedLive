package fight.tecmry.com.redlive.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import fight.tecmry.com.redlive.Bean.CountData;
import fight.tecmry.com.redlive.Bean.ImageData;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.CountInterface;
import fight.tecmry.com.redlive.Util.ImageInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tecmry on 2017/8/25.
 */

public class SeachCount extends AppCompatActivity
{
    private Toolbar toolbar;

    private ImageView imageView;

    private TextView baiduTextview;
    private TextView mobileTextView;
    private TextView thirdTextView;
    private TextView keywordsTextView;
    private TextView timeTextView;

    private Handler handler;
    public static final String BASE_URL="http://www.bing.com";
    private String addUrl = "http://s.cn.bing.net";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serachcount);
        init();
    }
    private void init()
    {
        toolbar = (Toolbar)findViewById(R.id.search_toolbar);
        toolbar.setTitle("热点");
        setSupportActionBar(toolbar);
        imageView = (ImageView)findViewById(R.id.bannner);

        baiduTextview = (TextView)findViewById(R.id.tv_suggestion);
        mobileTextView = (TextView)findViewById(R.id.tv_suggestion_mobile);
        thirdTextView = (TextView)findViewById(R.id.tv_suggestion_360);
        keywordsTextView = (TextView)findViewById(R.id.tv_countname);
        timeTextView = (TextView)findViewById(R.id.tv_temp);
        NetUtil();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 1:
                        Glide.with(getApplicationContext()).load(msg.obj).into(imageView);
                        break;
                }
            }
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("输入关键词");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchNet(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }
    private void SearchNet(final String count)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.91cha.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CountInterface countInterface = retrofit.create(CountInterface.class);
                Call<CountData> call=countInterface.getCount("b92b9f8775e545c3a5d573c7dc156558",count);
                call.enqueue(new Callback<CountData>() {
                    @Override
                    public void onResponse(Call<CountData> call, Response<CountData> response) {
                        CountData data = response.body();
                        List<CountData.DataBean> list = data.getData();
                        CountData.DataBean bean = list.get(0);
                         final String keywords = bean.getKeyword();
                        final int baidu = bean.getAllindex();
                        final int baidumobile = bean.getMobileindex();
                        final int third = bean.getSo360index();
                        System.out.println("Search" + keywords);

                        Date date = new Date();
                        DateFormat df = new SimpleDateFormat("MM-dd hh:mm:ss");
                        final String time = df.format(date);
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               keywordsTextView.setText(keywords);
                               baiduTextview.setText(String.valueOf(baidu));
                               mobileTextView.setText(String.valueOf(baidumobile));
                               thirdTextView.setText(String.valueOf(third));
                               timeTextView.setText(time);
                                }
                       });
                    }

                    @Override
                    public void onFailure(Call<CountData> call, Throwable t) {
                        Toast.makeText(SeachCount.this,"网络出了点小问题",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
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
                        Message message = Message.obtain();
                        message.obj = url;
                        message.what = 1;
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
}
