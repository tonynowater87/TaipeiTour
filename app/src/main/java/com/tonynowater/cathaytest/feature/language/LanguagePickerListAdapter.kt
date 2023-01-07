package com.tonynowater.cathaytest.feature.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tonynowater.cathaytest.databinding.AdapterLanguagePickerBinding
import com.tonynowater.cathaytest.feature.model.LanguagePickerUiModel

object DiffCallbackLanguagePickerUiModel : DiffUtil.ItemCallback<LanguagePickerUiModel>() {
    override fun areItemsTheSame(
        oldItem: LanguagePickerUiModel,
        newItem: LanguagePickerUiModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: LanguagePickerUiModel,
        newItem: LanguagePickerUiModel
    ): Boolean {
        return oldItem == newItem
    }
}

class LanguagePickerListAdapter :
    ListAdapter<LanguagePickerUiModel, LanguagePickerListAdapter.LanguagePickerViewHolder>(
        DiffCallbackLanguagePickerUiModel
    ) {

    var onClickLanguage: ((model: LanguagePickerUiModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagePickerViewHolder {
        return LanguagePickerViewHolder(
            AdapterLanguagePickerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LanguagePickerViewHolder, position: Int) {
        val model = getItem(position)
        holder.binding.radioButton.setOnClickListener {
            onClickLanguage?.invoke(model)
        }
        holder.bind(model)
    }

    inner class LanguagePickerViewHolder(val binding: AdapterLanguagePickerBinding) :
        ViewHolder(binding.root) {
        fun bind(model: LanguagePickerUiModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}