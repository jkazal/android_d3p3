package com.example.rickandmorty.ui.addnewmeetingform

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.user.User
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.databinding.ItemSelectableparticipantBinding

class AddNewMeetingFormStep2Adapter(private val listener: UserItemListener) : RecyclerView.Adapter<UserViewHolder>() {

    interface UserItemListener {
        fun onSelectUser(userId: String)
    }

    private val items = ArrayList<User>()

    fun setItems(items: ArrayList<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemSelectableparticipantBinding = ItemSelectableparticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(items[position])
}

class UserViewHolder(private val itemBinding: ItemSelectableparticipantBinding, private val listener: AddNewMeetingFormStep2Adapter.UserItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var user: User

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: User) {
        this.user = item
        itemBinding.selectableParticipantName.text = item.email
    }

    override fun onClick(v: View?) {
        listener.onSelectUser(user.id)
    }
}

