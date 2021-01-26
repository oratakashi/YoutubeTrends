package com.oratakashi.youtube.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.ActivityMainBinding
import com.oratakashi.youtube.utils.ImageHelper
import eightbitlab.com.blurview.RenderScriptBlur


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            reduceDragSensitivity()
            ImageHelper.apply {
                getPicasso(
                    this@with.ivAvatar,
                    "https://d17ivq9b7rppb3.cloudfront.net/original/jobs/fullstack_web_developer_160620212126.png"
                )
                getPicasso(
                    this@with.ivImage,
                    "https://forums.macrumors.com/attachments/8ce03f00-332e-49e5-a90a-430c3d10181b-jpeg.926035/"
                )
            }
            setupBlur()
            vpMain.also {
                it.adapter = MainAdapter(this@MainActivity)
                it.offscreenPageLimit = 4
            }
            bnMenu.also { bnMenu ->
                bnMenu.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
                bnMenu.add(MeowBottomNavigation.Model(1, R.drawable.ic_game))
                bnMenu.add(MeowBottomNavigation.Model(2, R.drawable.ic_music))
                bnMenu.add(MeowBottomNavigation.Model(3, R.drawable.ic_sport))
                bnMenu.show(1, true)
                bnMenu.setupViewPager(vpMain)
                bnMenu.setOnClickMenuListener { menu ->
                    vpMain.setCurrentItem(menu.id, true)
                }
            }
            ivFav.also { ivFav ->
                ivFav.setOnClickListener {
                    try {
                        startActivity(
                            Intent(
                                applicationContext,
                                Class.forName("com.oratakashi.youtube.favorite.ui.main.FavoriteActivity")
                            ).also {
                                it.putExtra("data", vpMain.currentItem)
                            }
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            applicationContext,
                            "Module not installed!",
                            Toast.LENGTH_SHORT
                        ).show()
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
                        0 -> tvTitle.text = getString(R.string.title_trending)
                        1 -> tvTitle.text = getString(R.string.title_game)
                        2 -> tvTitle.text = getString(R.string.title_music)
                        3 -> tvTitle.text = getString(R.string.title_sport)
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
    }

    private fun reduceDragSensitivity() {
        try {
            val ff =
                ViewPager2::class.java.getDeclaredField("mRecyclerView")
            ff.isAccessible = true
            val recyclerView = ff[binding.vpMain] as RecyclerView
            val touchSlopField =
                RecyclerView::class.java.getDeclaredField("mTouchSlop")
            touchSlopField.isAccessible = true
            val touchSlop = touchSlopField[recyclerView] as Int
            touchSlopField[recyclerView] = touchSlop * 4
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}