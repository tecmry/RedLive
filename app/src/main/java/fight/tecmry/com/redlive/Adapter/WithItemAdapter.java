package fight.tecmry.com.redlive.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import fight.tecmry.com.redlive.Bean.WithMeData;
import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/24.
 */

public class WithItemAdapter extends RecyclerView.Adapter<WithItemAdapter.ViewHolder> {
    private List<AVIMConversation> list = new ArrayList<AVIMConversation>();
    private Context mContext;
    private String ConversationId;

    public WithItemAdapter(List<AVIMConversation> list, Context context)
    {
        this.list = list;
        this.mContext = context;
    }
    public interface OnItemClickListner{
        void OnItemClickListner(View view,int position);
    }

    private ShowItemAdapter.OnItemClickListner listner;
    public void setItemClickListner(ShowItemAdapter.OnItemClickListner listner){
        this.listner=listner;
    }
    @Override
    public WithItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.withmeitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WithItemAdapter.ViewHolder holder, int position)
    {
         Check(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView talkername;
        private TextView talktime;
        private TextView lastTalk;
        private ImageView userimage;
        private RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            talkername = (TextView)itemView.findViewById(R.id.with_username);
            talktime = (TextView)itemView.findViewById(R.id.withme_Time);
            lastTalk = (TextView)itemView.findViewById(R.id.withme_lasttalk);
            userimage = (ImageView)itemView.findViewById(R.id.userimage);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.with_Rl);

        }
    }
    private void Check(WithItemAdapter.ViewHolder holder,int position)
    {
        try{
            AVIMConversation conversation = list.get(position);
            ConversationId = conversation.getConversationId();
            //获取Conversation的名字
            String conversationname = conversation.getName();
            Log.d("WithMe","Conversationname"+conversationname);
            AVIMMessage lasttalkmessage = conversation.getLastMessage();
            //获取消息发送时间
            Date  date = conversation.getLastMessageAt();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = new Gson();
            WithMeData data = gson.fromJson(lasttalkmessage.getContent(),WithMeData.class);
            String Content = data.get_lctext();
            final int members = conversation.getMembers().size();
            String creator  =conversation.getCreator();


            if (members>2)
            {
                holder.talkername.setText(conversationname);
                holder.lastTalk.setText(Content);
                holder.talktime.setText(df.format(date));
            }else if (members <=2)
            {
                holder.talkername.setText(creator);
                holder.lastTalk.setText(Content);
                holder.talktime.setText(df.format(date));
            }
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,LCIMConversationActivity.class);
                    intent.putExtra(LCIMConstants.CONVERSATION_ID, ConversationId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        } catch (NullPointerException ee){
            ee.printStackTrace();
        }
    }
}
