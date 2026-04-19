package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapp.data.api.RetrofitInstance
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.ui.NewsAdapter
import com.example.newsapp.ui.NewsViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi Repository dan ViewModel secara manual (simpel)
        val repository = NewsRepository(RetrofitInstance.api)
        viewModel = NewsViewModel(repository)

        // 2. Setup UI
        val rvNews = findViewById<RecyclerView>(R.id.rvNews)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        rvNews.layoutManager = LinearLayoutManager(this)

        // 3. Ambil Data
        viewModel.fetchNews()
        Log.d("NewsApp", "Lagi ambil data nih...")

        // 4. Observasi data dari ViewModel (Syarat No. 5: State Management)
        viewModel.articles.observe(this) { articles ->
            if (articles != null) {
                newsAdapter = NewsAdapter(articles) { article ->
                    // Syarat No. 3: Klik Berita (Sementara pakai Toast)
                    Toast.makeText(this, "Membuka: ${article.title}", Toast.LENGTH_SHORT).show()
                }
                rvNews.adapter = newsAdapter
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.isError.observe(this) { isError ->
            if (isError) {
                Toast.makeText(this, "Koneksi Gagal!", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Fitur Pull to Refresh (Syarat No. 4)
        swipeRefresh.setOnRefreshListener {
            viewModel.fetchNews()
            swipeRefresh.isRefreshing = false
        }
    }
}