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

        public void setBitmap(Uri uri) {
            imageView.setImageURI(uri);
        }
    }

    public ArrayList<Pair<Uri, String>> dataset;

    public Tab2Adapter(ArrayList<Pair<Uri, String>> dataset) {
        this.dataset = dataset;
    }

    public void add(Uri uri, String tag) {
        dataset.add(new Pair<>(uri, tag));
        notifyDataSetChanged();
    }

    public Uri remove(int i) {
        Uri uri = dataset.get(i).first;
        dataset.remove(i);
        notifyDataSetChanged();
        return uri;
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
        final ImageViewHolder vh = viewHolder;
        Pair<Uri, String> p = dataset.get(i);
        vh.setBitmap(p.first);
        vh.textView.setText(p.second);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public String getOriginalPath(int position) {
        Uri uri = dataset.get(position).first;
        String dir;
        String filename;
        try {
            String thPath = uri.getPath();
            int i = thPath.lastIndexOf("/");
            dir = thPath.substring(0, i) + "/";
            filename = thPath.substring(i + 1);
        } catch(NullPointerException e) {
            return null;
        }
        return dir + "O_" + filename;
    }
}
