package com.example.dolphin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dolphin.model.Post;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Post> listItems;
    private Context context;

    public DataAdapter(List<Post> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_post, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post listItem = listItems.get(position);

        //holder.image.setImageBitmap(listItem.getImage());
        //holder.supporting_url = listItem.getSupporting_url();

        //Glide.with(context).load(listItem.getImage_url()).into(holder.image);
        holder.caption.setText(listItem.getCaption());
        holder.description.setText(listItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private String supporting_url;
        private TextView caption;
        private TextView description;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.feed_image);
            caption = itemView.findViewById(R.id.textViewCaption);
            description = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
