package fight.tecmry.com.redlive.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Tecmry on 2017/8/29.
 */

public class NetWorkUntils {
    /**
     * 用于判断网络连接的工具类
     * */
    public static boolean isNetWorkConnected(Context context)
    {
        if (context!=null)
        {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null)
            {
                return info.isAvailable();
            }

        }
        return false;
    }
}
