package com.example.demoapp.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.data.model.immerSphere.SearchListModel
import com.example.demoapp.databinding.CellUserItemsBinding

class ItemAdapter(private val items: SearchListModel) : RecyclerView.Adapter<ItemAdapter.UserItemsVH>() {



        class UserItemsVH(val cellUserItemsBinding: CellUserItemsBinding) :
        RecyclerView.ViewHolder(cellUserItemsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemsVH {
        val cellUserItemsBinding: CellUserItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.cell_user_items, parent, false
        )
        return UserItemsVH(cellUserItemsBinding)
    }



    override fun onBindViewHolder(holder: UserItemsVH, position: Int) {
        // Bind the data to the views within each item

        holder.cellUserItemsBinding.tvName.text=items.data[position].composer
        holder.cellUserItemsBinding.tvAddress.text=items.data[position].name

    }

    override fun getItemCount(): Int {
        return items.data.size
    }
}
