package fight.tecmry.com.redlive.Util;

import java.util.List;

import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by Tecmry on 2017/8/23.
 */

public class CustomProvider implements LCChatProfileProvider{

    private static CustomProvider customProvider;
    public synchronized static CustomProvider getInstance()
    {
        if (customProvider==null){
            customProvider = new CustomProvider();
        }
        return customProvider;
    }

    @Override
    public void fetchProfiles(List<String> userIdList, LCChatProfilesCallBack profilesCallBack) {

    }
}
