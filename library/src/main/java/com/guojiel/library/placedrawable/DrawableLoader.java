package com.guojiel.library.placedrawable;

import android.graphics.drawable.Drawable;

/**
 * Created by guojiel on 2019/2/20.
 *
 */
public interface DrawableLoader {

    void loadDrawable(String url, Listener listener);

    interface Listener{
        void drawable(Drawable drawable);
    }

}
