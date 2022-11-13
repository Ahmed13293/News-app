package com.innovitics.newsfeedsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innovitics.newsfeedsapp.R
import com.innovitics.newsfeedsapp.data.models.ArticlesListModel
import com.innovitics.newsfeedsapp.databinding.NewsItemLayoutBinding

class NewsAdapter(
    var items: ArrayList<ArticlesListModel>,
    var onItemClicked: OnItemClickedInterface) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var binding: NewsItemLayoutBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = NewsItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem: ArticlesListModel = items[position]


        holder.itemView.setOnClickListener {
            onItemClicked.onArticleClicked(listItem)
        }

        binding.newsTitle.text = listItem.title
        binding.newsDesc.text = "By" + " "+ listItem.source.name
        binding.newsDate.text = listItem.publishedAt
        Glide.with(holder.itemView.context).load(listItem.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.newsImg)
        holder.itemView.setOnClickListener {
            onItemClicked.onArticleClicked(listItem)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemBinding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    interface OnItemClickedInterface {
        fun onArticleClicked(listItem: ArticlesListModel)
    }
}