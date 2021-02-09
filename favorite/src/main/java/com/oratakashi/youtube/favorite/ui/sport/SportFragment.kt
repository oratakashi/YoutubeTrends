package com.oratakashi.youtube.favorite.ui.sport

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.core.di.FavoriteModule
import com.oratakashi.youtube.favorite.databinding.FragmentFavSportBinding
import com.oratakashi.youtube.favorite.ui.main.FavoriteInterface
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.viewmodel.favorite.sport.SportViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class SportFragment : Fragment(), FavoriteInterface {

    private lateinit var binding: FragmentFavSportBinding

    private val adapter: SportAdapter by lazy {
        SportAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvSport.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.getSport(viewLifecycleOwner).observe(viewLifecycleOwner, adapter::submitList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavSportBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(FavoriteModule.viewModelModule)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSport()
    }

    private val viewModel: SportViewModel by viewModel()

    override fun onItemSelected(item: Items) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = SportFragment()
    }
}