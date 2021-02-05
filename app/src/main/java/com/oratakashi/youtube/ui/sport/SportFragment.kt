package com.oratakashi.youtube.ui.sport

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentSportBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.sport.SportViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import com.oratakashi.youtube.ui.main.MainInterface
import org.koin.androidx.viewmodel.ext.android.viewModel

class SportFragment : Fragment(), MainInterface {

    lateinit var binding: FragmentSportBinding

    private val adapter: SportAdapter by lazy {
        SportAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            srSport.setOnRefreshListener {
                srSport.isRefreshing = false
                viewModel.getSport(viewLifecycleOwner)
            }

            rvSport.also {
                rvSport.adapter = adapter
                rvSport.layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getSport(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MainState.Loading -> {
                        lavSport.setAnimation(R.raw.loading)
                        lavSport.playAnimation()
                        lavSport.visibility = View.VISIBLE
                        rvSport.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        lavSport.visibility = View.GONE
                        rvSport.visibility = View.VISIBLE

                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        lavSport.visibility = View.VISIBLE
                        lavSport.setAnimation(R.raw.error)
                        lavSport.playAnimation()
                        rvSport.visibility = View.GONE

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
        binding = FragmentSportBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel: SportViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = SportFragment()
    }
}