package com.oratakashi.youtube.ui.music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.AdapterMainBinding
import com.oratakashi.youtube.presentation.model.main.Items
import com.oratakashi.youtube.utils.Converter
import com.oratakashi.youtube.utils.ImageHelper
import org.koin.java.KoinJavaComponent

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
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
        }
    }

    fun submitList(list : List<Items>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) : Items {
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    private val data : MutableList<Items> = ArrayList()

    private val context: Context by KoinJavaComponent.inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterMainBinding) : RecyclerView.ViewHolder(view.root)
}