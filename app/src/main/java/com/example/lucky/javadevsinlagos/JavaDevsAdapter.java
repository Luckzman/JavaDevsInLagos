package com.example.lucky.javadevsinlagos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.bitmap;
import static android.R.attr.resource;


/**
 * Created by LUCKY on 8/17/2017.
 */

public class JavaDevsAdapter extends ArrayAdapter<JavaDevs> {

    public JavaDevsAdapter(Context context, List<JavaDevs> javaDevs) {
        super(context, 0, javaDevs);
    }
    //the method returns an inflated view of the list items to the activity_main.xml
    public View getView(int position, View convertView, ViewGroup parent) {
        JavaDevs javaDevs = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        CircleImageView img = (CircleImageView) convertView.findViewById(R.id.dev_image);

        Glide.with(getContext()).load(javaDevs.getImageUrlJavaDevs()).into(img);//Glide
        TextView textView = (TextView) convertView.findViewById(R.id.dev_username);
        textView.setText(javaDevs.getUserJavaDevs().toUpperCase());

        return convertView;
    }
    /*
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
           /* InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
           return d;

                String urldisplay = url;
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;

        } catch (Exception e) {
            return null;
        }
        */

}
