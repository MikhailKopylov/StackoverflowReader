package com.amk.stackoverflowreader.mvp.model.entity.contexImplementation

import android.widget.ImageView
import com.amk.stackoverflowreader.mvp.model.entity.image.IImageLoader
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}