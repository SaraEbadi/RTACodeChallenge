package com.example.rtacodechallenge.feature.countrieslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.navigation.fragment.findNavController
import com.example.rtacodechallenge.R
import com.example.rtacodechallenge.base.BaseFragment
import com.example.rtacodechallenge.data.model.Country
import com.example.rtacodechallenge.utils.GeneralResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_countries_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*

class CountriesListFragment : BaseFragment() {
    override val layout: Int
        get() = R.layout.fragment_countries_list
    private val viewModel: CountryListViewModel by sharedViewModel()
    private var countriesList = mutableListOf<Country>()
    private lateinit var countryAdapter: CountryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpViewModel()
        searchOnCountriesList()
    }

    private fun setUpViewModel() {
        viewModel.countriesListLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is GeneralResponse.Success -> response.data?.let { data ->
                    countriesList = data.toMutableList()
                    countryAdapter.submitList(data)
                    pgCenter.visibility = INVISIBLE
                }
                is GeneralResponse.Error ->
                    Snackbar.make(clCountriesList,getString(R.string.server_error), Snackbar.LENGTH_LONG).show()
                is GeneralResponse.Loading -> pgCenter.visibility = VISIBLE
            }
        })
    }


    private fun setUpViews() {
        countryAdapter = CountryAdapter(mutableListOf(), object : CountryAdapterCallback {
            override fun onAddClicked(country: Country) {
                viewModel.changeToggleSelectedItems(country)
            }
        })
        rvCountries.adapter = countryAdapter
        btnDone.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun searchOnCountriesList() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString())
            }

        })
    }

    private fun filter(keyword: String) {
        mutableListOf<Country>().apply {
            for (item in countriesList) {
                if (item.name.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))
                    add(item)
            }
            countryAdapter.updateItem(this)
        }
    }

}