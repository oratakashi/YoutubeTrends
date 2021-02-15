package com.oratakashi.youtube.favorite.ui.main


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.fxn.BubbleTabBar
import com.fxn.OnBubbleClickListener
import com.oratakashi.youtube.core.di.FavoriteModule
import com.oratakashi.youtube.favorite.R
import com.oratakashi.youtube.favorite.databinding.ActivityFavoriteBinding
import com.oratakashi.youtube.favorite.utils.ImageHelper
import eightbitlab.com.blurview.RenderScriptBlur
import org.koin.core.context.unloadKoinModules


class FavoriteActivity : AppCompatActivity() {

    private val binding: ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        reduceDragSensitivity()

        with(binding) {
            ImageHelper.apply {
                getPicasso(
                    ivAvatar,
                    "https://d17ivq9b7rppb3.cloudfront.net/original/jobs/fullstack_web_developer_160620212126.png"
                )
            }
            vpMain.also {
                it.adapter = FavoriteAdapter(this@FavoriteActivity)
                it.offscreenPageLimit = 4
            }
            bnMenu.also {
                it.setupViewPager(vpMain)
                it.addBubbleListener(object : OnBubbleClickListener {
                    override fun onBubbleClick(id: Int) {
                        when (id) {
                            R.id.navigation_all -> vpMain.setCurrentItem(0, true)
                            R.id.navigation_games -> vpMain.setCurrentItem(1, true)
                            R.id.navigation_music -> vpMain.setCurrentItem(2, true)
                            R.id.navigation_sport -> vpMain.setCurrentItem(3, true)
                        }
                    }
                })
            }
            ivBack.setOnClickListener { finish() }
            //Setting for auto selected section
            Handler(Looper.getMainLooper()).postDelayed({
                vpMain.setCurrentItem(intent.getIntExtra("data", 0), true)
            }, 200L)
        }
    }

    private fun BubbleTabBar.setupViewPager(viewPager: ViewPager2) {
        val tabs = this
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabs.setSelected(position)
            }
        })
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

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(FavoriteModule.viewModelModule)
    }
}