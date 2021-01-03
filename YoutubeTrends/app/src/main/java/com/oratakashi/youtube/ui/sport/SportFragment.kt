package com.oratakashi.youtube.ui.sport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.FragmentSportBinding
import com.oratakashi.youtube.presentation.state.MainState
import com.oratakashi.youtube.presentation.viewmodel.sport.SportViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SportFragment : Fragment() {

    lateinit var binding : FragmentSportBinding

    private val adapter : SportAdapter by lazy {
        SportAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.also {
            it.srSport.setOnRefreshListener {
                it.srSport.isRefreshing = false
                viewModel.getSport(viewLifecycleOwner)
            }

            it.rvSport.also { rvSport ->
                rvSport.adapter = adapter
                rvSport.layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getSport(viewLifecycleOwner).observe(viewLifecycleOwner, { state ->
                when(state){
                    is MainState.Loading        -> {
                        it.lavSport.visibility = View.VISIBLE
                        it.rvSport.visibility = View.GONE
                    }
                    is MainState.Result         -> {
                        it.lavSport.visibility = View.GONE
                        it.rvSport.visibility = View.VISIBLE

                        adapter.submitList(state.data)
                    }
                    is MainState.Error          -> {
                        it.lavSport.visibility = View.GONE
                        it.rvSport.visibility = View.VISIBLE

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
        binding = FragmentSportBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel : SportViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = SportFragment()
    }
}