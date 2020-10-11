package com.example.rtacodechallenge.feature.countrieslist

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rtacodechallenge.R
import com.example.rtacodechallenge.data.model.Country
import com.example.rtacodechallenge.utils.toggleSelection
import kotlinx.android.synthetic.main.country_item.view.*

class CountryAdapter(
    private var countriesList: MutableList<Country>,
    private val countryAdapterCallback: CountryAdapterCallback
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun submitList(newCountriesList: List<Country>) {
        val diffCallback = CountryListDiffCallback(countriesList, newCountriesList)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)
        updateItem(newCountriesList.toMutableList())
        diffResult.dispatchUpdatesTo(this@CountryAdapter)
    }

    fun updateItem(list: MutableList<Country>) {
        countriesList = list
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
                tvCountryName.text = country.name
                if (country.isAdded) {
                    btnAdd.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.colorDarkGray
                        )
                    )
                    btnAdd.text = "Added"
                } else {
                    btnAdd.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            R.color.colorPrimary
                        )
                    )

                    btnAdd.text = "Add"
                }
                btnAdd.setOnClickListener {
                    country.isAdded = !country.isAdded
                    if (country.isAdded) {
                        btnAdd.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context,
                                R.color.colorDarkGray
                            )
                        )
                        btnAdd.text = "Added"
                    } else {
                        btnAdd.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPrimary
                            )
                        )

                        btnAdd.text = "Add"
                    }
                    countryAdapterCallback.onAddClicked(country)
                }
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
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