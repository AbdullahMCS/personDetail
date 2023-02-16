package com.example.myfirstapp.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.data.model.room.RoomItemModel
import com.example.myfirstapp.databinding.ItemRoomBinding

class RoomsAdapter(
    val data: ArrayList<RoomItemModel>,
) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    inner class ViewHolder(
        val view: ItemRoomBinding
    ): RecyclerView.ViewHolder(view.root) {
        fun initUI(roomItemModel: RoomItemModel) {
            val context = itemView.context
            view.apply {
                tvRoomId.text = context.getString(R.string.room_id, roomItemModel.id)
                tvState.text = context.getString(R.string.room_state, roomItemModel.isOccupied.toString())
                tvCapacity.text = context.getString(R.string.room_capacity, roomItemModel.maxOccupancy.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUI(data[position])
    }

}
