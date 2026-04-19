package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.R
import com.example.newsapp.data.models.Article


class NewsAdapter(private val articles: List<Article>, private val onClick: (Article) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val desc: TextView = view.findViewById(R.id.tvDescription)
        val image: ImageView = view.findViewById(R.id.imgNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        holder.desc.text = article.description

        // Menggunakan Coil untuk load gambar
        holder.image.load(article.urlToImage) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
        }

        holder.itemView.setOnClickListener { onClick(article) }
    }

    override fun getItemCount(): Int = articles.size
}