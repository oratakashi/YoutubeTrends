package com.oratakashi.youtube.favorite.ui.all

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.AdapterMainBinding
import com.oratakashi.youtube.favorite.utils.ImageHelper
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.utils.Converter
import org.koin.java.KoinJavaComponent.inject

class AllAdapter : RecyclerView.Adapter<AllAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            if (getItem(position).urlMedium.isNotEmpty()) {
                ImageHelper.getPicasso(ivImage, getItem(position).urlMedium)
            } else if (getItem(position).urlStandard.isNotEmpty()) {
                ImageHelper.getPicasso(ivImage, getItem(position).urlStandard)
            }
            tvTitle.text = getItem(position).title
            tvInfo.text = String.format(
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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun submitList(list: List<Items>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Items {
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    private val data: MutableList<Items> = ArrayList()

    private val context: Context by inject(Context::class.java)

    class ViewHolder(val view: AdapterMainBinding) : RecyclerView.ViewHolder(view.root)
}