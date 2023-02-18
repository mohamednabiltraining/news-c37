package com.route.news_app_c37.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.news_app_c37.databinding.ItemCategoryBinding

class CategoriesRecyclerAdapter(val items: List<Category>) :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        onItemClickListener?.let { clickListener ->
            holder.itemBinding.container.setOnClickListener {
                clickListener.onItemClick(position, items[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = items.size
    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(pos: Int, item: Category)
    }

    class ViewHolder(val itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            itemBinding.category = category;
            itemBinding.invalidateAll()
        }
    }
}