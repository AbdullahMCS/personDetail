package com.example.myfirstapp.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstapp.R
import com.example.myfirstapp.data.model.people.PeopleItemModel
import com.example.myfirstapp.databinding.ItemPeopleBinding

class PeopleAdapter(
    val data: ArrayList<PeopleItemModel>,
    val function: (person: PeopleItemModel) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    inner class ViewHolder(val view: ItemPeopleBinding) : RecyclerView.ViewHolder(view.root) {

        fun initUI(peopleItemModel: PeopleItemModel) {
            view.tvTitle.text = "${peopleItemModel.firstName} ${peopleItemModel.lastName}"
            view.tvDesc.text = peopleItemModel.jobtitle
            with(itemView) {
                Glide.with(context) // question here about the reliability, can we put this
                    // code in a util class and reuse it, because it used in multiple locations
                    .load(peopleItemModel.avatar)
                    .placeholder(R.drawable.animate_loading)
                    .centerCrop()
                    .into(view.ivProfilePic)
            }

            view.root.setOnClickListener { function.invoke(peopleItemModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPeopleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUI(data[position])
    }

}
