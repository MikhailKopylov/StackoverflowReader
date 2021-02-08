package com.amk.stackoverflowreader.mvp.model.entity.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}