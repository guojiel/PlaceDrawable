package com.guojiel.library.placedrawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

/**
 * Created by guojiel on 2019/2/21.
 *
 */
public class PlaceDrawableTextView extends AppCompatTextView {

    public PlaceDrawableTextView(Context context) {
        super(context);
    }

    public PlaceDrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlaceDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(text instanceof Spanned){
            ImageSpan[] spans = ((Spanned) text).getSpans(0, text.length(), ImageSpan.class);
            if(spans != null && spans.length != 0){
                for (ImageSpan span : spans) {
                    Drawable drawable = span.getDrawable();
                    if (drawable != null) {
                        drawable.setCallback(this);
                        drawable.setVisible(true, true);
                    }
                }
            }
        }
        super.setText(text, type);
    }

    private boolean verifySpanDrawable(@NonNull Drawable who) {
        CharSequence text = getText();
        boolean self = false;
        if(text instanceof Spanned){
            ImageSpan[] spans = ((Spanned) text).getSpans(0, text.length(), ImageSpan.class);
            if(spans != null && spans.length != 0){
                for (ImageSpan span : spans) {
                    if (span.getDrawable() == who) {
                        self = true;
                        break;
                    }
                }
            }
        }
        return self;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if(verifySpanDrawable(drawable)){
            invalidate();
            return ;
        }
        super.invalidateDrawable(drawable);
    }

}
