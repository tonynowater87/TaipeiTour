package com.tonynowater.cathaytest.feature.attractions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tonynowater.cathaytest.databinding.AdapterAttractionBinding
import com.tonynowater.cathaytest.feature.model.AttractionUiModel

class AttractionsListAdapter :
    ListAdapter<AttractionUiModel, AttractionsListAdapter.AttractionsViewHolder>(
        DiffAttractionUiModel
    ) {

    var onClickAttraction: ((model: AttractionUiModel) -> Unit)? = null

    inner class AttractionsViewHolder(val binding: AdapterAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: AttractionUiModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionsViewHolder {
        return AttractionsViewHolder(
            AdapterAttractionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AttractionsViewHolder, position: Int) {
        val model = getItem(position)
        holder.binding.root.setOnClickListener {
            onClickAttraction?.invoke(model)
        }
        holder.bind(model)
    }
}

object DiffAttractionUiModel : DiffUtil.ItemCallback<AttractionUiModel>() {
    override fun areItemsTheSame(oldItem: AttractionUiModel, newItem: AttractionUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AttractionUiModel,
        newItem: AttractionUiModel
    ): Boolean {
        return oldItem == newItem
    }
}