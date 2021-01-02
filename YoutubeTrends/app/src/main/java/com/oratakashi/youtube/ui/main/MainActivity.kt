package com.oratakashi.youtube.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.ActivityMainBinding
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.usecase.UseCase
import com.oratakashi.youtube.utils.ImageHelper
import eightbitlab.com.blurview.RenderScriptBlur
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.also {
            ImageHelper.apply {
                getPicasso(
                    it.ivAvatar,
                    "https://d17ivq9b7rppb3.cloudfront.net/original/jobs/fullstack_web_developer_160620212126.png"
                )
                getPicasso(
                    it.ivImage,
                    "https://forums.macrumors.com/attachments/8ce03f00-332e-49e5-a90a-430c3d10181b-jpeg.926035/"
                )
            }
            setupBlur()
            it.vpMain.also { vpMain ->
                vpMain.adapter = MainAdapter(this)
                vpMain.offscreenPageLimit = 4
            }
            it.bnMenu.also { bnMenu ->
                bnMenu.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
                bnMenu.add(MeowBottomNavigation.Model(1, R.drawable.ic_game))
                bnMenu.add(MeowBottomNavigation.Model(2, R.drawable.ic_music))
                bnMenu.add(MeowBottomNavigation.Model(3, R.drawable.ic_sport))
                bnMenu.show(1, true)
                bnMenu.setupViewPager(it.vpMain)
                bnMenu.setOnClickMenuListener { menu ->
                    it.vpMain.setCurrentItem(menu.id, true)
                }
            }
            it.ivFav.also { ivFav ->
                ivFav.setOnClickListener {
                    try {

                        startActivity(
                            Intent(
                                this,
                                Class.forName("com.oratakashi.youtube.favorite.Favorite")
                            )
                        )
                    }catch (e : Exception){
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "Module not installed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun MeowBottomNavigation.setupViewPager(viewPager: ViewPager2) {
        val tabs = this
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabs.show(position, true)
                binding.tvTitle.also { tvTitle ->
                    when (position) {
                        0   -> tvTitle.text = getString(R.string.title_trending)
                        1   -> tvTitle.text = getString(R.string.title_game)
                        2   -> tvTitle.text = getString(R.string.title_music)
                        3   -> tvTitle.text = getString(R.string.title_sport)
                    }
                }
            }
        })
    }

    private fun setupBlur() {
        binding.vBackground.setupWith(window.decorView.findViewById(android.R.id.content))
            .setFrameClearDrawable(window.decorView.background)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(25f)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
        binding.vLayer.setupWith(window.decorView.findViewById(android.R.id.content))
            .setFrameClearDrawable(window.decorView.background)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(25f)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
    }
}