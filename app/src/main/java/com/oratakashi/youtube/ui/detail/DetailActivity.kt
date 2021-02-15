package com.oratakashi.youtube.ui.detail

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.ActivityDetailBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.viewmodel.detail.DetailViewModel
import com.oratakashi.youtube.utils.Converter
import com.oratakashi.youtube.utils.ImageHelper
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import eightbitlab.com.blurview.RenderScriptBlur
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val data: Items by lazy {
        intent.getParcelableExtra("data")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            ivBack.setOnClickListener { finish() }
            ytPlayer.also {
                lifecycle.addObserver(it)
                it.initialize(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.cueVideo(data.id, 0F)
                            youTubePlayer.play()
                        }
                    }, true
                )
                it.addFullScreenListener(object : YouTubePlayerFullScreenListener {
                    override fun onYouTubePlayerEnterFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }

                    override fun onYouTubePlayerExitFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    }
                })
                it.enableBackgroundPlayback(true)

            }

            tvTitle.text = data.title
            tvDescription.text = data.description
            tvInfo.text = String.format(
                getString(R.string.placeholder_info_adapter),
                data.channelTitle,
                Converter.numberConvert(data.viewCount.toInt()) + " views",
                Converter.dateFormat(
                    data.publishedAt
                        .replace("T", " ")
                        .replace("Z", ""),
                    "yyyy-MM-dd HH:mm:ss",
                    "dd MMMM"
                )
            )
            tvLike.text = Converter.numberConvert(data.likeCount.toInt())
            tvDislike.text = Converter.numberConvert(data.dislikeCount.toInt())
            tvComment.text = Converter.numberConvert(data.commentCount.toInt())

            llFav.setOnClickListener {
                viewModel.add(data)
            }

            viewModel.getState(data, this@DetailActivity).observe(this@DetailActivity, {
                when (it) {
                    true -> ivFav.setBackgroundResource(R.drawable.ic_fav_on)
                    false -> ivFav.setBackgroundResource(R.drawable.ic_fav_off)
                }
            })
        }
    }

    private val viewModel: DetailViewModel by viewModel()
}