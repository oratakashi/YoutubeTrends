package com.oratakashi.youtube.favorite.ui.all

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.youtube.core.di.FavoriteModule
import com.oratakashi.youtube.favorite.databinding.FragmentAllBinding
import com.oratakashi.youtube.favorite.ui.main.FavoriteInterface
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.presentation.viewmodel.favorite.all.AllViewModel
import com.oratakashi.youtube.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class AllFragment : Fragment(), FavoriteInterface {

    lateinit var binding: FragmentAllBinding

    private val adapter: AllAdapter by lazy {
        AllAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvAll.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.getAll(viewLifecycleOwner).observe(viewLifecycleOwner, adapter::submitList)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
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

    override fun onItemSelected(item: Items) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private val viewModel: AllViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance() = AllFragment()
    }
}