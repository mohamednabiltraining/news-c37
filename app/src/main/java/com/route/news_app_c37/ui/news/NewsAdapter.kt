package com.route.news_app_c37.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.news_app_c37.R
import com.route.news_app_c37.api.model.newsResponse.News
import com.route.news_app_c37.databinding.ItemNewsBinding

class NewsAdapter(var items: List<News?>?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(val viewBinding: ItemNewsBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position);
        holder.viewBinding.author.text = item?.author
        holder.viewBinding.title.text = item?.title
        holder.viewBinding.time.text = item?.publishedAt

        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .placeholder(R.drawable.ic_image)
            .into(holder.viewBinding.image)
    }

    override fun getItemCount(): Int = items?.size ?: 0
    fun changeData(articles: List<News?>?) {
        items = articles;
        notifyDataSetChanged()
    }
}