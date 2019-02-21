package com.guojiel.library.placedrawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/**
 * Created by guojiel on 2019/2/20.
 *
 */
public class PlaceDrawable extends Drawable implements Drawable.Callback {

    private static DrawableLoader mDrawableLoader;

    private Drawable wrap;
    private String mUrl;
    private Paint mPaint;

    public PlaceDrawable(){
        this(0xFFCCCCCC);
    }

    public PlaceDrawable(int placeColor){
        mPaint = new Paint();
        mPaint.setColor(placeColor);
    }

    public void setUrl(String url){
        if(url.equals(mUrl) && wrap != null){
            return;
        }
        this.mUrl = url;
        mDrawableLoader.loadDrawable(url, new DrawableLoader.Listener() {
            @Override
            public void drawable(Drawable drawable) {
                setWrap(drawable);
            }
        });
    }

    private void setWrap(Drawable wrap){
        this.wrap = wrap;
        wrap.setCallback(this);
        wrap.setBounds(getBounds());
        if(wrap instanceof Animatable) {
            ((Animatable) wrap).start();
        }
        invalidateSelf();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        if(wrap != null){
            wrap.setBounds(left, top, right, bottom);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(wrap != null){
            wrap.draw(canvas);
        }else {
            canvas.drawRect(getBounds(), mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if(wrap != null){
            wrap.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if(wrap != null){
            wrap.setColorFilter(colorFilter);
        }
    }

    @Override
    public int getOpacity() {
        if(wrap != null){
            return wrap.getOpacity();
        }
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        final Callback callback = getCallback();
        if(callback != null){
            callback.invalidateDrawable(this);
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        final Callback callback = getCallback();
        if(callback != null){
            callback.scheduleDrawable(this, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        final Callback callback = getCallback();
        if(callback != null){
            callback.unscheduleDrawable(this, what);
        }
    }

    public static void setDrawableLoader(DrawableLoader loader){
        mDrawableLoader = loader;
    }

}
