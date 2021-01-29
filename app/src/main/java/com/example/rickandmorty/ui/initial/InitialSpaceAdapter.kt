package com.example.rickandmorty.ui.initial

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.databinding.ItemUpcomingeventBinding
import kotlinx.android.synthetic.main.item_upcomingevent.*

class InitialSpaceAdapter(private val listener: InitialItemListener) : RecyclerView.Adapter<UpcomingMeetingViewHolder>() {

    interface InitialItemListener {
        fun onClickedMeetingRecycler(reservationId: String)
    }

    private val items = ArrayList<Reservation>()

    fun setItems(items: ArrayList<Reservation>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMeetingViewHolder {
        val binding: ItemUpcomingeventBinding = ItemUpcomingeventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingMeetingViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UpcomingMeetingViewHolder, position: Int) = holder.bind(items[position])
}

class UpcomingMeetingViewHolder(private val itemBinding: ItemUpcomingeventBinding, private val listener: InitialSpaceAdapter.InitialItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var reservation: Reservation

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Reservation) {
        this.reservation = item
        itemBinding.eventName.text = item.label
        itemBinding.organizerLabel.text = item.organizer.email
        itemBinding.StartsAtLabel.text = "START: " + item.dateDebut
        itemBinding.EndsAtLabel.text = "END: " + item.dateFin
    }

    override fun onClick(v: View?) {
        listener.onClickedMeetingRecycler(reservation.id)
    }
}

