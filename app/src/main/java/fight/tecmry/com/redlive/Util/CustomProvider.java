package fight.tecmry.com.redlive.Util;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by Tecmry on 2017/8/23.
 */

public class CustomProvider implements LCChatProfileProvider{

    private static CustomProvider customProvider;
    private static List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
    public synchronized static CustomProvider getInstance()
    {
        if (customProvider==null){
            customProvider = new CustomProvider();
        }
        return customProvider;
    }
    private CustomProvider(){}

    private void addList()
    {

    }
    @Override
    public void fetchProfiles(List<String> userIdList, LCChatProfilesCallBack profilesCallBack) {

    }
}
