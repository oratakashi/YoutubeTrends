package com.oratakashi.youtube.favorite.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oratakashi.youtube.favorite.ui.all.AllFragment
import com.oratakashi.youtube.favorite.ui.game.GameFragment
import com.oratakashi.youtube.favorite.ui.music.MusicFragment
import com.oratakashi.youtube.favorite.ui.sport.SportFragment

class FavoriteAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when(position){
        0       -> AllFragment.newInstance()
        1       -> GameFragment.newInstance()
        2       -> MusicFragment.newInstance()
        else    -> SportFragment.newInstance()
    }
}