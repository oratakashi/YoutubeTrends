package com.oratakashi.youtube.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentGameBinding
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.game.GameViewModel
import com.oratakashi.youtube.ui.home.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding

    private val adapter : GameAdapter by lazy {
        GameAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.also {
            it.srGame.setOnRefreshListener {
                it.srGame.isRefreshing = false
                viewModel.getGames(viewLifecycleOwner)
            }
            it.rvGame.also { rvGame ->
                rvGame.adapter = adapter
                rvGame.layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.getGames(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MainState.Loading -> {
                        it.lavGame.visibility = View.VISIBLE
                        it.rvGame.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        it.lavGame.visibility = View.GONE
                        it.rvGame.visibility = View.VISIBLE
                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        it.lavGame.visibility = View.GONE
                        it.rvGame.visibility = View.VISIBLE
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
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel : GameViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}