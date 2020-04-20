package com.jagor.testproductsapp

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule

private const val DISK_CACHE_100_MB = 1024 * 1024 * 100L

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val diskCacheSize = DISK_CACHE_100_MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSize))
    }
}