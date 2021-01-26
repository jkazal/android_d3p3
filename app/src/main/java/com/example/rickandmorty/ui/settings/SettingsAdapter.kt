package com.example.rickandmorty.ui.settings

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.databinding.ItemRoomBinding

class SettingsAdapter(private val listener: SettingItemListener) : RecyclerView.Adapter<SettingViewHolder>() {

    interface SettingItemListener {
        fun onClickedRoom(roomId: String)
    }

    private val items = ArrayList<D3P3Space>()

    fun setItems(items: ArrayList<D3P3Space>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val binding: ItemRoomBinding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) = holder.bind(items[position])
}

class SettingViewHolder(private val itemBinding: ItemRoomBinding, private val listener: SettingsAdapter.SettingItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var d3P3Space: D3P3Space

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: D3P3Space) {
        this.d3P3Space = item
        itemBinding.selectableParticipantName.text = item.name
    }

    override fun onClick(v: View) {
        Log.d("Johann", "click")
        listener.onClickedRoom(d3P3Space.id)
    }
}

