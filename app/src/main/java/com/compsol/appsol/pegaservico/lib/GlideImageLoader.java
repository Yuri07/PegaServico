package com.compsol.appsol.pegaservico.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.compsol.appsol.pegaservico.lib.base.ImageLoader;

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public void setLoaderContext(Context context) {
        this.glideRequestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequestManager
                .load(URL)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .apply(RequestOptions.overrideOf(800,800))
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }

}
