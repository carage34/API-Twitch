package com.example.recyclerview.controller;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclerview.model.obj.Streamer;

import com.example.recyclerview.R;
import com.example.recyclerview.view.MainActivity;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Streamer> values;
    private Context context;
    private StreamController sc;
    private View.OnClickListener mClickListener;
    private OnItemClickListener listener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder



    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView txtHeader;
        private TextView txtSecond;
        private ImageView img;
        private View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            img = (ImageView)v.findViewById(R.id.icon);
            txtSecond = (TextView) v.findViewById(R.id.secondLine);
        }
        public ImageView getImage() {
            return this.img;
        }

        public void bind(final Streamer item, final OnItemClickListener listener) {
        layout.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public void add(int position, Streamer item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Streamer> myDataset, MainActivity ctx, StreamController sc) {
        values = myDataset;
        this.context = ctx;
        this.sc = sc;
        System.out.println("LLL : " + values.toString());
        listener = new StreamerListener(values, sc, ctx);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Streamer streamer = values.get(position);
        //System.out.println("ID : " + streamer.getId());
        //System.out.println(user.getProfile_image_url());
        holder.bind(values.get(position), listener);
        System.out.println("POS : " + position);
        //final User user = urlImage.get(position);
        Glide.with(this.context)
                .load(streamer.getProfile_image_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());

        holder.txtHeader.setText(streamer.getUser_name());
        holder.txtSecond.setText(streamer.getTitle());
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


}
