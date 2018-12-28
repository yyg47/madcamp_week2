package io.madcamp.yh.mc_assignment1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
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
            /* Bitmap bm = postprocessOfCamera(uri);
            if(bm != null) {
                imageView.setImageBitmap(bm);
            } */
        }

        private Bitmap postprocessOfCamera(Uri uri) {
            /* try {
                Bitmap image = BitmapFactory.decodeStream(imageView.getContext().getContentResolver().openInputStream(uri));
                ExifInterface ei = new ExifInterface(imageView.getContext().getContentResolver().openInputStream(uri));
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);
                Bitmap rotatedBitmap = null;
                float angle = 0.0f;
                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90: angle = 90.f; break;
                    case ExifInterface.ORIENTATION_ROTATE_180: angle = 180.f; break;
                    case ExifInterface.ORIENTATION_ROTATE_270: angle = 270.f; break;
                }
                Log.d("Angle", "" + angle);
                Bitmap rotated = image;
                if(angle != 0.0f) rotated = rotateImage(image, angle);
                return rotated;
            } catch(IOException e) {
                e.printStackTrace();
                return null;
            } */ return null;
        }

        private static Bitmap rotateImage(Bitmap source, float angle) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
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
        ImageViewHolder vh = (ImageViewHolder)viewHolder;
        Pair<Uri, String> p = dataset.get(i);
        vh.setBitmap(p.first);
        vh.textView.setText(p.second);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
