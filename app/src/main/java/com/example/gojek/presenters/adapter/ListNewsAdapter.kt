package com.example.gojek.presenters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gojek.databinding.ListNewsBinding
import com.example.gojek.domain.model.News

class ListNewsAdapter(private val dataSet: List<News>
): RecyclerView.Adapter<ListNewsAdapter.ListNewsViewHolder>(){

    inner class ListNewsViewHolder (
        private val binding: ListNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.apply {
                imgNews.setImageResource(news.imgSrc)
                tvTitle.text = itemView.resources.getString(news.title)
                tvDescription.text = itemView.resources.getString(news.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        val bindingLayout =
            ListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNewsViewHolder(bindingLayout)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}