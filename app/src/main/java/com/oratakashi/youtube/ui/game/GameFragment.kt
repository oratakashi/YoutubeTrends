package com.oratakashi.youtube.ui.game

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentGameBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.game.GameViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import com.oratakashi.youtube.ui.main.MainInterface
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameFragment : Fragment(), MainInterface {

    private lateinit var binding: FragmentGameBinding

    private val adapter: GameAdapter by lazy {
        GameAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            srGame.setOnRefreshListener {
                srGame.isRefreshing = false
                viewModel.getGames(viewLifecycleOwner)
            }
            rvGame.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.getGames(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MainState.Loading -> {
                        lavGame.setAnimation(R.raw.loading)
                        lavGame.playAnimation()
                        lavGame.visibility = View.VISIBLE
                        rvGame.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        lavGame.visibility = View.GONE
                        rvGame.visibility = View.VISIBLE
                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        lavGame.visibility = View.VISIBLE
                        lavGame.setAnimation(R.raw.error)
                        lavGame.playAnimation()
                        rvGame.visibility = View.GONE
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
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel: GameViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}