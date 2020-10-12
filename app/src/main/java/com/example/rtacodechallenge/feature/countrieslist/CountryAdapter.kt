package com.example.rtacodechallenge.feature.countrieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rtacodechallenge.R
import com.example.rtacodechallenge.data.model.Country
import com.example.rtacodechallenge.utils.changeToggleSelectedItemsView
import kotlinx.android.synthetic.main.country_item.view.*

class CountryAdapter(
    private var countriesList: MutableList<Country>,
    private val countryAdapterCallback: CountryAdapterCallback
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun submitList(newCountriesList: List<Country>) {
        val diffCallback = CountryListDiffCallback(countriesList, newCountriesList)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)
        updateItem(newCountriesList)
        diffResult.dispatchUpdatesTo(this@CountryAdapter)
    }

    fun updateItem(list: List<Country>) {
        countriesList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_item, parent, false)
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    override fun getItemCount(): Int = countriesList.size

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {
            with(itemView) {
                country.apply {
                    tvCountryName.text = name
                    btnAdd.changeToggleSelectedItemsView(isAdd)
                    btnAdd.setOnClickListener {
                        isAdd = !isAdd
                        btnAdd.changeToggleSelectedItemsView(isAdd)
                        countryAdapterCallback.onAddClicked(country)
                    }
                }
            }
        }
    }
}

class CountryListDiffCallback(
    private val newList: List<Country>, private val oldList: List<Country>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(newItemPosition: Int, oldItemPosition: Int) =
        newList[newItemPosition] == oldList[oldItemPosition]

    override fun areContentsTheSame(newItemPosition: Int, oldItemPosition: Int) =
        newList[newItemPosition].name == oldList[oldItemPosition].name
}

interface CountryAdapterCallback {
    fun onAddClicked(country: Country)
}