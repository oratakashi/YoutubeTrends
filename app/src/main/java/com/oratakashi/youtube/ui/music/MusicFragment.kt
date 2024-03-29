package com.oratakashi.youtube.ui.music

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentMusicBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.music.MusicViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import com.oratakashi.youtube.ui.main.MainInterface
import org.koin.androidx.viewmodel.ext.android.viewModel


class MusicFragment : Fragment(), MainInterface {

    lateinit var binding: FragmentMusicBinding

    private val adapter: MusicAdapter by lazy {
        MusicAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            srMusic.setOnRefreshListener {
                srMusic.isRefreshing = false
                viewModel.getMusic(viewLifecycleOwner)
            }

            rvMusic.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getMusic(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MainState.Loading -> {
                        lavMusic.setAnimation(R.raw.loading)
                        lavMusic.playAnimation()
                        lavMusic.visibility = View.VISIBLE
                        rvMusic.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        lavMusic.visibility = View.GONE
                        rvMusic.visibility = View.VISIBLE

                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        lavMusic.visibility = View.VISIBLE
                        lavMusic.setAnimation(R.raw.error)
                        lavMusic.playAnimation()
                        rvMusic.visibility = View.GONE
                        state.error.printStackTrace()
                        Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }

    override fun onItemSelected(item: Items) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel: MusicViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = MusicFragment()
    }
}