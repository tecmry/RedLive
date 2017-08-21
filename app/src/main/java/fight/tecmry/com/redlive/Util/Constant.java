package fight.tecmry.com.redlive.Util;

import com.avos.avoscloud.AVUser;

/**
 * Created by Tecmry on 2017/8/18.
 */

public class Constant {
    //文件路径管理类
    public static class FilePath {
        //存放录音类
        //根目录不写/sdcard/没办法读取

        public static final String ROOT_PATH = "/sdcard/";
        public static final String RECORD_DIR = "record/";
        public static final String RECORD_PATH = ROOT_PATH + RECORD_DIR;

        //存放用户图片类
        public static final String HEAD_DIR = "userheader/";
        public static final String HEAD_PATH = ROOT_PATH + RECORD_DIR;
        public static final String USER_NAME = AVUser.getCurrentUser().getUsername();

    }
    public static class User{
        public static final AVUser avuser = AVUser.getCurrentUser();

        /**
         * 判断是否进行登录
         * */
        public static boolean isLogin()
        {
            return AVUser.getCurrentUser() != null;
        }
    }
}