package com.route.news_app_c37.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.news_app_c37.api.model.newsResponse.News
import com.route.news_app_c37.databinding.ItemNewsBinding

class NewsAdapter(var items: List<News?>?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(val viewBinding: ItemNewsBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(news: News?) {
            viewBinding.news = news
            viewBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position);
        holder.bind(item)
    }

    override fun getItemCount(): Int = items?.size ?: 0
    fun changeData(articles: List<News?>?) {
        items = articles;
        notifyDataSetChanged()
    }
}