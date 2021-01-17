package com.oratakashi.youtube.favorite.ui.all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oratakashi.youtube.core.di.FavoriteModule
import com.oratakashi.youtube.favorite.R
import com.oratakashi.youtube.favorite.databinding.FragmentAllBinding
import com.oratakashi.youtube.presentation.viewmodel.favorite.all.AllViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class AllFragment : Fragment() {

    lateinit var binding : FragmentAllBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(FavoriteModule.viewModelModule)
    }

    private val viewModel : AllViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = AllFragment()
    }
}