package com.oratakashi.youtube.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentHomeBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.home.HomeViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import com.oratakashi.youtube.ui.main.MainInterface
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), MainInterface {

    private lateinit var binding: FragmentHomeBinding

    private val adapter: HomeAdapter by lazy {
        HomeAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            srHome.setOnRefreshListener {
                srHome.isRefreshing = false
                viewModel.getTrends(viewLifecycleOwner)
            }

            rvHome.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getTrends(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when (state) {
                    is MainState.Loading -> {
                        lavHome.setAnimation(R.raw.loading)
                        lavHome.playAnimation()
                        lavHome.visibility = View.VISIBLE
                        rvHome.visibility = View.GONE
                    }
                    is MainState.Result -> {
                        lavHome.visibility = View.GONE
                        rvHome.visibility = View.VISIBLE
                        adapter.submitList(state.data)
                    }
                    is MainState.Error -> {
                        lavHome.visibility = View.VISIBLE
                        lavHome.setAnimation(R.raw.error)
                        lavHome.playAnimation()
                        rvHome.visibility = View.GONE
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
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}