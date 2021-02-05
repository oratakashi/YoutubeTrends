package com.oratakashi.youtube.favorite.ui.music

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.core.di.FavoriteModule
import com.oratakashi.youtube.favorite.databinding.FragmentMusicBinding
import com.oratakashi.youtube.favorite.ui.main.FavoriteInterface
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.viewmodel.favorite.music.MusicViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class MusicFragment : Fragment(), FavoriteInterface {

    lateinit var binding: FragmentMusicBinding

    private val adapter: MusicAdapter by lazy {
        MusicAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvMusic.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.getMusic(viewLifecycleOwner).observe(viewLifecycleOwner, adapter::submitList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(FavoriteModule.viewModelModule)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMusic()
    }

    override fun onItemSelected(item: Items) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private val viewModel: MusicViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = MusicFragment()
    }
}