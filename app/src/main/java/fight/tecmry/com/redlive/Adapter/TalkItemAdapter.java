package fight.tecmry.com.redlive.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fight.tecmry.com.redlive.Bean.TalkData;
import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/18.
 */

public class TalkItemAdapter extends RecyclerView.Adapter<TalkItemAdapter.ViewHolder> {

    private List<TalkData> list = new ArrayList<TalkData>();

    @Override
    public TalkItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.livenow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TalkItemAdapter.ViewHolder holder, int position)
    {
            TalkData talkData = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout right_layout;
        LinearLayout left_layout;
        TextView left_Msg;
        TextView right_Msg;
        LinearLayout time_layout;
        TextView time_tv;
       ImageView Iv_left;
        ImageView Iv_right;

        public ViewHolder(View itemView) {
            super(itemView);
            right_layout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            left_layout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            right_Msg = (TextView) itemView.findViewById(R.id.right_Msg);
            left_Msg = (TextView) itemView.findViewById(R.id.left_Msg);
            time_layout = (LinearLayout) itemView.findViewById(R.id.ll_time);
            time_tv = (TextView) itemView.findViewById(R.id.tv_time);
            Iv_left = (ImageView)itemView.findViewById(R.id.CV_Left);
            Iv_right = (ImageView)itemView.findViewById(R.id.CV_Right);
        }
    }

}
