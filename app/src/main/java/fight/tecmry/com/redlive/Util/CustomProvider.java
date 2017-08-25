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

  static {
      userList.add(new LCChatKitUser("599d72661b69e6006a2ab50f",
              "abc","http://ac-8gYICsgi.clouddn.com/Cdd4Z6uxOjDAjwgH5id0Dayr6FlIZwJmnINhsZR7.jpg"));
  }
    @Override
    public void fetchProfiles(List<String> userIdList, LCChatProfilesCallBack profilesCallBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : userIdList) {
            for (LCChatKitUser user :userList) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        profilesCallBack.done(userList, null);
    }
    public List<LCChatKitUser> getAllUsers() {
        return userList;
    }
}
