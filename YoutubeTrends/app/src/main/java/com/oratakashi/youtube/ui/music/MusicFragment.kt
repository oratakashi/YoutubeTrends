package com.oratakashi.youtube.ui.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentMusicBinding
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.music.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MusicFragment : Fragment() {

    lateinit var binding: FragmentMusicBinding

    private val adapter: MusicAdapter by lazy {
        MusicAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.also {
            it.srMusic.setOnRefreshListener {
                it.srMusic.isRefreshing = false
                viewModel.getMusic(viewLifecycleOwner)
            }

            it.rvMusic.also { rvMusic ->
                rvMusic.adapter = adapter
                rvMusic.layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getMusic(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when(state){
                    is MainState.Loading        -> {
                        it.lavMusic.visibility = View.VISIBLE
                        it.rvMusic.visibility = View.GONE
                    }
                    is MainState.Result         -> {
                        it.lavMusic.visibility = View.GONE
                        it.rvMusic.visibility = View.VISIBLE

                        adapter.submitList(state.data)
                    }
                    is MainState.Error          -> {
                        it.lavMusic.visibility = View.GONE
                        it.rvMusic.visibility = View.VISIBLE

                        state.error.printStackTrace()
                        Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
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