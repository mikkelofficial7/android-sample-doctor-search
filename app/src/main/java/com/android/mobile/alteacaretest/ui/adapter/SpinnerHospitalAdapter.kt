package com.android.mobile.alteacaretest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.android.mobile.alteacaretest.databinding.ItemSpinnerBinding
import com.android.mobile.alteacaretest.model.detail.Hospital


class SpinnerHospitalAdapter(context: Context, data: List<Hospital>) :
    ArrayAdapter<Hospital?>(context, 0, data) {

    companion object {
        var onHospitalSelect:(List<Hospital>) -> Unit = {}
    }

    private val listState: ArrayList<Hospital> = data as ArrayList<Hospital>
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
        parent: ViewGroup?
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

        binding.spinnerCheckbox.tag = position
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
            val getPosition = buttonView.tag as Int
            if (!isSpinnerLoadFromView) listState[getPosition].isChecked = isChecked

            onHospitalSelect(getAllCheckedHospital())
        }

        return row
    }

    private fun getAllCheckedHospital() : List<Hospital> {
        return listState.filter { it.isChecked }
    }
}