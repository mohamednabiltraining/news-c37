package com.route.news_app_c37

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageFromUrl(
    imageView: ImageView,
    url: String
) {
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("cardColor")
fun changeCardBackgroundColor(cardView: CardView, colorId: Int) {
    cardView.setCardBackgroundColor(
        ContextCompat.getColor(
            cardView.context,
            colorId
        )
    )
}

@BindingAdapter("imageId")
fun changeImageWithByResourceId(
    imageView: ImageView,
    resId: Int
) {
    imageView.setImageResource(resId)
}