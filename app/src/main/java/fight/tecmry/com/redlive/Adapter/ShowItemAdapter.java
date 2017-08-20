package fight.tecmry.com.redlive.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;

import java.util.ArrayList;
import java.util.List;

import fight.tecmry.com.redlive.R;

/**
 * Created by Tecmry on 2017/8/19.
 */

public class ShowItemAdapter extends RecyclerView.Adapter<ShowItemAdapter.ViewHolder>
{
    private List<AVObject> list = new ArrayList<AVObject>();
    private Context context;
    public ShowItemAdapter(List<AVObject> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    public interface OnItemClickListner{
        void OnItemClickListner(View view,int position);
    }

    private OnItemClickListner listner;
    public void setItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }
    @Override
    public ShowItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liveitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowItemAdapter.ViewHolder holder, int position)
    {
        holder.Title.setText((CharSequence) list.get(position).get("name"));
        holder.Author.setText((CharSequence) list.get(position).get("Author"));

        //设置给头像

        holder.like.setText((CharSequence) list.get(position).get("Like"));
        holder.like.setText((CharSequence)list.get(position).get("View"));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        TextView Author;
        RatingBar ratingBar;
        ImageView imageView;
        TextView  like;
        ImageButton imageButton;
        TextView viewPeople;
        ImageView type;
        public ViewHolder(View itemView) {
            super(itemView);
            Title = (TextView)itemView.findViewById(R.id.liveitem_title);
            Author = (TextView)itemView.findViewById(R.id.liveitem_author);
            ratingBar = (RatingBar)itemView.findViewById(R.id.liveitem_ratingbar);
            imageView = (ImageView)itemView.findViewById(R.id.LiveImage);
            like = (TextView)itemView.findViewById(R.id.textView3);
            imageButton = (ImageButton)itemView.findViewById(R.id.like);
            viewPeople = (TextView)itemView.findViewById(R.id.liveitem_peopleview);
            type = (ImageView)itemView.findViewById(R.id.imageView5);
        }
    }
}
