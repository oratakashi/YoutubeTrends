package com.oratakashi.youtube.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.AdapterMainBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.ui.main.MainInterface
import com.oratakashi.youtube.utils.Converter
import com.oratakashi.youtube.utils.ImageHelper
import org.koin.java.KoinJavaComponent.inject

class HomeAdapter(
    private val parent : MainInterface
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (getItem(position).urlMedium.isNotEmpty()) {
                ImageHelper.getPicasso(view.ivImage, getItem(position).urlMedium)
            } else if (getItem(position).urlStandard.isNotEmpty()) {
                ImageHelper.getPicasso(view.ivImage, getItem(position).urlStandard)
            }
            view.tvTitle.text = getItem(position).title
            view.tvInfo.text = String.format(
                context.getString(R.string.placeholder_info_adapter),
                getItem(position).channelTitle,
                Converter.numberConvert(getItem(position).viewCount.toInt()) + " views",
                Converter.dateFormat(
                    getItem(position).publishedAt
                        .replace("T", " ")
                        .replace("Z", ""),
                    "yyyy-MM-dd HH:mm:ss",
                    "dd MMMM"
                )
            )

            if (position == data.size - 1) {
                val marginParams = view.clAdapter.layoutParams as ViewGroup.MarginLayoutParams
                marginParams.setMargins(0, 20, 0, 200)
            }

            view.root.setOnClickListener { parent.onItemSelected(getItem(position)) }
        }
    }

    private val data: MutableList<Items> = ArrayList()

    fun submitList(list: List<Items>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Items {
        return data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = data.size

    private val context: Context by inject(Context::class.java)

    class ViewHolder(val view: AdapterMainBinding) : RecyclerView.ViewHolder(view.root)
}