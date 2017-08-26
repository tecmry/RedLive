package fight.tecmry.com.redlive.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import fight.tecmry.com.redlive.R;
import fight.tecmry.com.redlive.Util.Constant;
import fight.tecmry.com.redlive.Util.GlideCircleTransform;


public class UserEditor extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;

    private ImageView user_image;

    private EditText Et_email;
    private EditText Et_Work;
    private EditText Et_motto;
    private EditText Et_City;
    private CheckBox Cb_man;
    private CheckBox Cb_woman;

    private String TAG = "UserEditor";
    private static String path = "/sdcard/"+Constant.User.avuser.getUsername()+"/";
    private static final String filename= "head.jpg";
    private static String image_filename;

    private static final int take_photo = 1;
    private static final int choose_photo = 2;
    private static final int core_photo = 3;

    private int addFile = 0;
    private int checkHeadTimes = 0;
    private Bitmap mBitmap;
    private String name = Constant.User.avuser.getUsername()+".jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usereditor);

        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.usereditor_toolbar);
        toolbar.setTitle(AVUser.getCurrentUser().getUsername());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserEditor.this,"取消修改",Toast.LENGTH_SHORT).show();
                finish();
        }
        });


        user_image = (ImageView)findViewById(R.id.user_image);
        user_image.setOnClickListener(this);
        loadImage(user_image);

        Et_email = (EditText)findViewById(R.id.Et_email);
        Et_email.setText(AVUser.getCurrentUser().getEmail());
        Et_motto = (EditText)findViewById(R.id.Et_motto);
        Et_City = (EditText)findViewById(R.id.Et_City);
        if (AVUser.getCurrentUser().get("email")!=null)
        {
            Et_email.setText((CharSequence) Constant.User.avuser.get("email"));
        }
        if ((CharSequence) AVUser.getCurrentUser().get("motto")!=null) {
            Et_motto.setText((CharSequence) AVUser.getCurrentUser().get("motto"));
        }
        Et_Work = (EditText)findViewById(R.id.Et_work);
        if ((CharSequence) AVUser.getCurrentUser().get("work")!=null) {
            Et_Work.setText((CharSequence) AVUser.getCurrentUser().get("work"));
        }
        if ((CharSequence) AVUser.getCurrentUser().get("city")!=null) {
            Et_City.setText((CharSequence) AVUser.getCurrentUser().get("city"));
        }
        boolean isChecked = false;
        Cb_man = (CheckBox)findViewById(R.id.Cb_man);
        Cb_man.setOnClickListener(this);
        Cb_woman = (CheckBox)findViewById(R.id.Cb_woman);
        Cb_woman.setOnClickListener(this);
        if (AVUser.getCurrentUser().get("sex")=="男")
        {
            Cb_man.setChecked(true);
        }else if (AVUser.getCurrentUser().get("sex")=="女"){
            Cb_woman.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.usertoolbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_yes:
                change();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.user_image:
                showDialog();
                break;
            case R.id.Cb_man:
                if (!Cb_woman.isChecked()&&!Cb_man.isChecked())
                {
                    Cb_man.setChecked(true);
                }
                if (Cb_woman.isChecked())
                {
                    Cb_man.setChecked(true);
                    Cb_woman.setChecked(false);
                }
                break;
            case R.id.Cb_woman:
                if (!Cb_woman.isChecked()&&!Cb_man.isChecked())
                {
                    Cb_woman.setChecked(true);
                }
                if (Cb_man.isChecked())
                {
                    Cb_woman.setChecked(true);
                    Cb_man.setChecked(false);
                }

                break;
        }
    }


    /**
    * 实现进行更换用户信息
    * */
    private void change()
    {
        try {

            final String email = Et_email.getText().toString();
            final String work = Et_Work.getText().toString();
            final String mooto = Et_motto.getText().toString();
            final String city = Et_City.getText().toString();
            boolean isMan = Cb_man.isChecked();
            boolean isWomen = Cb_woman.isChecked();
            String sex = null;
            if (isMan || isWomen) {
                if (isMan) {
                    sex = "男";
                } else if (isWomen) {
                    sex = "女";
                }
            }
            final String sexx = sex;
            //上传信息 不进行空判断
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                Toast.makeText(UserEditor.this, "已经执行上传了", Toast.LENGTH_SHORT).show();
                                Constant.User.avuser.put("email", email);
                                Constant.User.avuser.put("hobby", work);
                                Constant.User.avuser.put("motto", mooto);
                                Constant.User.avuser.put("city", city);
                                System.out.println(sexx);
                                Constant.User.avuser.put("sex", sexx);
                                Constant.User.avuser.saveInBackground();
                            } else {
                                Toast.makeText(UserEditor.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
            int  i =0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("name" + name);
                    try {
                        final AVFile file = AVFile.withAbsoluteLocalPath(Constant.User.avuser.getUsername()+".jpg",path + "head.jpg");
                        file.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e==null){
                                    Toast.makeText(UserEditor.this,"Photo is Push",Toast.LENGTH_SHORT).show();
                                    AVUser.getCurrentUser().put("imageUrl",file.getUrl());
                                    AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(AVException e) {
                                            if (e==null)
                                            {
                                                Log.d(TAG,"OK");
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    } catch (FileNotFoundException ed) {
                        ed.printStackTrace();
                    }
                }
            }).start();
            Toast.makeText(UserEditor.this, "修改成功", Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e){
            Log.e("UserEditor",e.toString());
        }
    }
    /**
     *
     * */
    private boolean checkNull(String str)
    {
        if (str!=null)
        {
            return true;
        }else {
            return false;
        }
    }
    private void loadImage(final ImageView imageView)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(path + "head.jpg");
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
                            Glide.with(UserEditor.this).load(bytes).
                                    transform(new GlideCircleTransform(UserEditor.this)).into(imageView);
                        }
                    }
                });
            }
        }).start();
        }
    }
    /**
     * 实现更换头像
     * */
    public void showDialog(){
        Dialog dialog =new AlertDialog.Builder(UserEditor.this)
                .setTitle("更换头像")
                .setMessage("选择一种方式更换头像")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPhoto();
                    }
                }) .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPhoto();
                    }
                }).create();
        dialog.show();
    }
    public void setPhoto() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (isSdCardExiting()) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, getUrl());
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                startActivityForResult(intent, take_photo);
            } else {
                Toast.makeText(UserEditor.this, "可能没有插入SD卡", Toast.LENGTH_LONG).show();
            }
        }else {
            new AlertDialog.Builder(getApplicationContext())
                    .setMessage("申请相机权限")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(UserEditor.this,
                                    new String[]{Manifest.permission.CAMERA}, take_photo);
                        }
                    }).show();
        }
    }
    public void getPhoto()
    {
        Intent intent = new Intent();
        // 获取本地相册方法一
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //获取本地相册方法二
        //        intent.setAction(Intent.ACTION_PICK);
        //        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        //                "image/*");
        startActivityForResult(intent, core_photo);
    }
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, choose_photo);
    }
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            saveBitmap(photo);
            /**
             * 这样不能获取到CircleView实例；
             * */
           user_image.setImageBitmap(photo);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(UserEditor.this, "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case take_photo:
                if (isSdCardExiting()) {
                    File filetemp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    photoClip(Uri.fromFile(filetemp));
                }else {
                    Toast.makeText(UserEditor.this,"没找到SD卡，无法储存照片",Toast.LENGTH_LONG).show();
                }
                break;
            case core_photo:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case choose_photo:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean isSdCardExiting(){
        final String state= Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }
    private Uri getUrl(){
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),filename));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == take_photo) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(UserEditor.this,"已申请权限",Toast.LENGTH_LONG).show();
            } else {
                /**
                 * 用户勾选了不在询问
                 * 提示用户手动打开权限
                 */
                if (!ActivityCompat.shouldShowRequestPermissionRationale(UserEditor.this, Manifest.permission.CAMERA)) {
                    Toast.makeText(UserEditor.this, "相机权限已经禁止", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private void saveBitmap(Bitmap bitmap){
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED))
        { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;

            File file_left = new File(path);
            file_left.mkdirs();// 创建文件夹
            image_filename = path + "head.jpg";// 图片名字

        try
        {
            System.out.println(image_filename + "image");
            b = new FileOutputStream(image_filename);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流

                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
