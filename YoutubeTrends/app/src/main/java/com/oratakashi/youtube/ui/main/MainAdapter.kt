package com.oratakashi.youtube.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oratakashi.youtube.ui.game.GameFragment
import com.oratakashi.youtube.ui.home.HomeFragment
import com.oratakashi.youtube.ui.music.MusicFragment
import com.oratakashi.youtube.ui.sport.SportFragment

class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment = when (position){
        0       -> HomeFragment.newInstance()
        1       -> GameFragment.newInstance()
        2       -> MusicFragment.newInstance()
        else    -> SportFragment.newInstance()
    }
}