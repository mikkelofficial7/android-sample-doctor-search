package com.android.mobile.alteacaretest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.android.mobile.alteacaretest.databinding.ItemSpinnerBinding
import com.android.mobile.alteacaretest.model.detail.Specialization


class SpinnerSpecializationAdapter(context: Context, data: List<Specialization>) :
    ArrayAdapter<Specialization?>(context, 0, data) {

    companion object {
        var onSpecializeSelect:(List<Specialization>) -> Unit = {}
    }

    private val listState: ArrayList<Specialization> = data as ArrayList<Specialization>
    private var isSpinnerLoadFromView = false

    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup?
    ): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val binding: ItemSpinnerBinding
        var row = convertView

        if (row == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = ItemSpinnerBinding.inflate(inflater, parent, false)
            row = binding.root
        } else {
            binding = ItemSpinnerBinding.bind(row)
        }

        binding.spinnerName.text = listState[position].name
        binding.spinnerName.text = listState[position].name

        isSpinnerLoadFromView = true
        binding.spinnerCheckbox.isChecked = listState[position].isChecked
        isSpinnerLoadFromView = false

        if (position == 0) {
            binding.spinnerCheckbox.visibility = View.INVISIBLE
        } else {
            binding.spinnerCheckbox.visibility = View.VISIBLE
        }

        binding.spinnerCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isSpinnerLoadFromView) listState[position].isChecked = isChecked

            onSpecializeSelect(getAllCheckedSpecialize())
        }

        return row
    }

    private fun getAllCheckedSpecialize() : List<Specialization> {
        return listState.filter { it.isChecked }
    }
}