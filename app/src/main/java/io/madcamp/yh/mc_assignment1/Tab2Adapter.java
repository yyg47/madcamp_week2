package io.madcamp.yh.mc_assignment1;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.ImageViewHolder> {
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

    public ArrayList<Pair<String, String>> dataset;

    public Tab2Adapter(ArrayList<Pair<String, String>> dataset) {
        this.dataset = dataset;
    }

    public void add(String uri, String name) {
        dataset.add(new Pair<>(uri, name));
        notifyDataSetChanged();
    }

    public void remove(int i) {
        dataset.remove(i);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_tab2_image, viewGroup, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {
        ImageViewHolder vh = (ImageViewHolder)viewHolder;
        Pair<String, String> p = dataset.get(i);
        vh.imageView.setImageURI(Uri.parse(p.first));
        vh.textView.setText(p.second);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
