package com.guojiel.placedrawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.guojiel.library.placedrawable.DrawableLoader;
import com.guojiel.library.placedrawable.PlaceDrawable;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlaceDrawable.setDrawableLoader(new DrawableLoader() {
            @Override
            public void loadDrawable(String url, final Listener listener) {
                Glide.with(MainActivity.this).load(url).into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        listener.drawable(resource);
                    }
                });
            }
        });
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.mTv);
        findViewById(R.id.mSetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceDrawable drawable = new PlaceDrawable();
                drawable.setBounds(0,0,100,100);
                drawable.setUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2896419389,2357132417&fm=26&gp=0.jpg");
                ImageSpan span = new ImageSpan(drawable);
                SpannableString spannable = new SpannableString("左骚: 一个小小小~s1234567890123456789");
                spannable.setSpan(span, 12, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mTv.setText(spannable);
            }
        });
    }


}
