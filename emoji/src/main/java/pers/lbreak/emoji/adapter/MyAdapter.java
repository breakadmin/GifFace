package pers.lbreak.emoji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import pers.lbreak.emoji.Interface.FaceListener;
import pers.lbreak.emoji.R;
import pers.lbreak.emoji.model.Emoji;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Emoji> data;
    FaceListener listFaceListener;

    public void setFaceListener(FaceListener listFaceListener) {
        this.listFaceListener = listFaceListener;
    }

    public MyAdapter(Context context, List<Emoji> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_face, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Emoji emoji = data.get(position);


        Glide
                .with(context)
                .load(emoji.getRes())//显示gif
                .skipMemoryCache(false)
                .placeholder(R.drawable.preview)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                    .priority(Priority.HIGH)
                .into(holder.icon);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listFaceListener != null) {
                    listFaceListener.display(emoji, position);
                }
            }
        });


//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    class ViewHolder  extends RecyclerView.ViewHolder  {
        public View rootView;
        public ImageView icon;

        public ViewHolder(View rootView) {
            super(rootView);
            this.icon = (ImageView) rootView.findViewById(R.id.icon);
        }

    }
}