package com.android.mobile.alteacaretest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.android.mobile.alteacaretest.databinding.ActivityDetailBinding
import com.android.mobile.alteacaretest.room.RoomHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.webkit.WebView

import android.annotation.SuppressLint
import android.view.View

import android.webkit.WebViewClient





@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val TAG_MOVIE_ID = "movie_id"
        const val TAG_MOVIE_TITLE = "movie_title"
    }

    private lateinit var binding: ActivityDetailBinding
    private var movieId: String = ""
    private var movieTitle: String = ""
    private var WEB_URL = "https://www.imdb.com/title/"

    @Inject
    lateinit var roomHelper: RoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        movieId = intent.getStringExtra(TAG_MOVIE_ID).orEmpty()
        movieTitle = intent.getStringExtra(TAG_MOVIE_TITLE).orEmpty()

        setContentView(binding.root)
        setupActionBar()

        showLoading()
        loadUrl()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }


    private fun setupActionBar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = movieTitle
    }

    private fun showLoading() {
        binding.pbLoad.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbLoad.visibility = View.GONE
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrl() {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl("${WEB_URL}${movieId}/")

        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                hideLoading()
            }
        }
    }
}