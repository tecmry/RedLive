package fight.tecmry.com.redlive.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetDataCallback;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import fight.tecmry.com.redlive.Activity.Enter;
import fight.tecmry.com.redlive.Activity.SeachCount;
import fight.tecmry.com.redlive.Activity.UserEditor;
import fight.tecmry.com.redlive.Activity.WithMe;
import fight.tecmry.com.redlive.Activity.searchFriend;
import fight.tecmry.com.redlive.Bean.EverydaySentenceData;
import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.Constant;
import fight.tecmry.com.redlive.Util.EverydayInterface;
import fight.tecmry.com.redlive.Util.GlideCircleTransform;
import fight.tecmry.com.redlive.View.SelectPopUpWindow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class User_Fragment extends Fragment implements View.OnClickListener{
    private final static String TAG = "user_fragment";


    private ImageView userimage;

    private TextView username;
    private TextView with;
    private TextView  everydaysentence;
    private TextView OutEnter;
    private TextView getBack;
    private TextView hot;
    private ImageView Im_back;
    private AVUser avUser;

    private Handler handler;
    private static String BASE_URL= "http://open.iciba.com";
    private String sentenceUrl;
    private String sentence;
    private String text;
    private Toolbar toolbar;
    private ImageButton imageButton;

    private SelectPopUpWindow popUpWindow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_news,container,false);

        init(view);
        return view;
    }

    private void init(View view)
    {
        imageButton = (ImageButton)view.findViewById(R.id.Ib_search);
        imageButton.setOnClickListener(this);
        toolbar = (Toolbar)view.findViewById(R.id.usernews_toolbar);
        userimage = (ImageView)view.findViewById(R.id.usernews_userimage);
        userimage.setOnClickListener(this);
        if (Constant.User.isLogin()) {
            loadImage(userimage);
        }
        username = (TextView)view.findViewById(R.id.usernews_username);

        OutEnter = (TextView)view.findViewById(R.id.out_enter);
        OutEnter.setOnClickListener(this);
        with = (TextView)view.findViewById(R.id.textView8);
        everydaysentence = (TextView)view.findViewById(R.id.everydaycount);
        everydaysentence.setOnClickListener(this);
        with.setOnClickListener(this);

        getBack = (TextView)view.findViewById(R.id.textView2);
        getBack.setOnClickListener(this);

        hot = (TextView)view.findViewById(R.id.textView9);
        hot.setOnClickListener(this);
        Im_back = (ImageView)view.findViewById(R.id.imageView4);
        Im_back.setOnClickListener(this);
        avUser = AVUser.getCurrentUser();

      if (Constant.User.isLogin()) {
          username.setText(avUser.getUsername());
      }
    LoadSentence();
      handler = new Handler()
      {
          @Override
          public void handleMessage(Message msg) {
              super.handleMessage(msg);
              switch (msg.what)
              {
                  case 0:
                      everydaysentence.setText((CharSequence) msg.obj);
                      break;
              }
          }
      };
    }

    private void loadImage(final ImageView imageView)
    {
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/"+Constant.User.avuser.getUsername()+"/" + "head.jpg");
        if (bitmap!=null)
        {
           imageView.setImageBitmap(bitmap);
        }else {new Thread(new Runnable() {
            @Override
            public void run() {
                AVFile avFile = new AVFile(Constant.User.avuser.getUsername() + ".jpg",
                        (String) Constant.User.avuser.get("headImage"),new HashMap<String,Object>());
                avFile.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        if (bytes!=null) {
                            Glide.with(getContext()).load(bytes).
                                    transform(new GlideCircleTransform(getContext())).into(imageView);
                        }
                    }
                });
            }
        }).start();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.usernews_userimage:
                if (Constant.User.isLogin()){
                    Intent intent = new Intent(getContext(), UserEditor.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getContext(), Enter.class);
                    startActivity(intent);
                }
                break;
            case R.id.out_enter:
               if (AVUser.getCurrentUser() == null)
               {
                   Toast.makeText(getContext(),"登都没登还退！！",Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getContext(),"您已退出",Toast.LENGTH_SHORT).show();
                   AVUser.getCurrentUser().logOut();
                   username.setText("");
               }
               break;
            case R.id.textView8:
                if (Constant.User.isLogin()){
                startActivity(new Intent(getContext(), WithMe.class));
                }else {
                    startActivity(new Intent(getContext(),Enter.class));
                }
                break;
            case R.id.everydaycount:

                Toast.makeText(getContext(),"前往下载英文版录音",Toast.LENGTH_SHORT).show();
                if (!sentenceUrl.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(sentenceUrl);
                    intent.setData(uri);
                    startActivity(Intent.createChooser(intent, "请选择浏览器"));
                }
                break;
            case R.id.textView2:
                showPop();
                break;
            case R.id.imageView4:
                showPop();
                break;
            case R.id.textView9:
                startActivity(new Intent(getContext(), SeachCount.class));
                break;
            case R.id.Ib_search:
                if (Constant.User.isLogin()) {
                    startActivity(new Intent(getContext(), searchFriend.class));
                }else {
                    Toast.makeText(getContext(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),Enter.class));
                }
                    break;
        }

    }
    private void showPop()
    {
        popUpWindow = new SelectPopUpWindow(getActivity());
        popUpWindow.showAtLocation(getActivity().findViewById(R.id.main_parent),
                Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }
    private void LoadSentence()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit =new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EverydayInterface Einterface = retrofit.create(EverydayInterface.class);
                Call<EverydaySentenceData> call = Einterface.getSentence();
                call.enqueue(new Callback<EverydaySentenceData>() {
                    @Override
                    public void onResponse(Call<EverydaySentenceData> call, Response<EverydaySentenceData> response) {
                        EverydaySentenceData data = response.body();
                        sentence = data.getNote();
                        sentenceUrl = data.getTts();
                        Message message = new Message();
                        message.obj = sentence;
                        message.what = 0;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Call<EverydaySentenceData> call, Throwable t) {
                        Toast.makeText(getContext(),"每日一句加载出了问题",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constant.User.isLogin()) {
            loadImage(userimage);
        }
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
