package com.dicoding.picodiploma.kamenrider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private Context context;
    ImageView imgPhoto;
    TextView tv_name, tv_heisei, tv_realese, tv_eps, tv_production, tv_sinopsis;
    public static final String EXTRA_NAME = "nm", EXTRA_HEI = "hei", EXTRA_RLS = "rls", EXTRA_EPS = "eps", EXTRA_PRO = "rpo", EXTRA_SIN = "SIN", EXTRA_LOGO = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgPhoto = findViewById(R.id.img_item_photo);
        tv_name = findViewById(R.id.tv_item_name);
        tv_heisei = findViewById(R.id.tv_item_heisei);
        tv_realese = findViewById(R.id.tv_item_realese);
        tv_eps = findViewById(R.id.tv_item_totaleps);
        tv_production = findViewById(R.id.tv_item_producer);
        tv_sinopsis = findViewById(R.id.tv_item_sinopsis);

        tv_name.setText(getIntent().getStringExtra(EXTRA_NAME));
        tv_heisei.setText(getIntent().getStringExtra(EXTRA_HEI));
        tv_realese.setText(getIntent().getStringExtra(EXTRA_RLS));
        tv_eps.setText(getIntent().getStringExtra(EXTRA_EPS));
        tv_production.setText(getIntent().getStringExtra(EXTRA_PRO));
        tv_sinopsis.setText(getIntent().getStringExtra(EXTRA_SIN));

        try {
            new DownLoadImageTask(imgPhoto).execute(getIntent().getStringExtra(EXTRA_LOGO));

        } catch (Exception e){
            System.out.println(e);
        }

        getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_NAME));
    }

}

class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    /*
        doInBackground(Params... params)
            Override this method to perform a computation on a background thread.
     */
    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}