package com.example.dolphin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dolphin.model.Post;

import java.net.URL;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Post listItem = listItems.get(position);

        URL imageUrl = listItem.getImage_url();
        ImageLoader imageLoader = VolleyImageLOoader.getInstance(context).getImageLoader();
        //TODO: typecast 'image_url'back to String from URL
        imageLoader.get(String.valueOf(imageUrl), ImageLoader.getImageListener(holder.image, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

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
