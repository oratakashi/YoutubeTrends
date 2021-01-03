package com.oratakashi.youtube.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentHomeBinding
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val adapter : HomeAdapter by lazy {
        HomeAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.also {

            it.srHome.setOnRefreshListener {
                it.srHome.isRefreshing = false
                viewModel.getTrends(viewLifecycleOwner)
            }

            it.rvHome.also { rvHome ->
                rvHome.adapter = adapter
                rvHome.layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getTrends(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MainState.Loading -> {
                        it.lavHome.setAnimation(R.raw.loading)
                        it.lavHome.playAnimation()
                        it.lavHome.visibility = View.VISIBLE
                        it.rvHome.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        it.lavHome.visibility = View.GONE
                        it.rvHome.visibility = View.VISIBLE
                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        it.lavHome.visibility = View.VISIBLE
                        it.lavHome.setAnimation(R.raw.error)
                        it.lavHome.playAnimation()
                        it.rvHome.visibility = View.GONE
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
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel : HomeViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}