package fight.tecmry.com.redlive.Util;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tecmry on 2017/8/19.
 */

public class IndexItem {
    /**
     * 想办法不让自己写重复代码（雾）
     * 用来返回一个类的所有元素
     * */
    private  List<AVObject> mList = new ArrayList<AVObject>();

    public  List<AVObject> searchItem(final String index)
    {
        new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        AVQuery<AVObject> query = new AVQuery<AVObject>(index);
                        mList = query.find();
                    } catch (AVException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        Log.d("IndexItem", String.valueOf(mList.size()));
           return mList;
    }

    /**
     *进行附属关系查询 一对多
     * @Param index:父类对象 include：包括在父类中的类 Id：父类对象的ID
     * */
    private  List<AVObject> searchItem(final String index,final String include,final String Id)
    {
        AVObject up = AVObject.createWithoutData(index,Id);
        AVQuery<AVObject> avQuery = new AVQuery<>(include);

        avQuery.whereEqualTo("dependent", up);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                mList = list;
            }
        });
        return mList;
    }
}
