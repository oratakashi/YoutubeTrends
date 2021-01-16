package com.oratakashi.youtube.ui.music

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
                when (state) {
                    is MainState.Loading -> {
                        it.lavMusic.visibility = View.VISIBLE
                        it.rvMusic.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        it.lavMusic.visibility = View.GONE
                        it.rvMusic.visibility = View.VISIBLE

                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        it.lavMusic.visibility = View.GONE
                        it.rvMusic.visibility = View.VISIBLE

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