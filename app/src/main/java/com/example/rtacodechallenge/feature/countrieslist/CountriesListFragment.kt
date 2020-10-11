package com.example.rtacodechallenge.feature.countrieslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rtacodechallenge.R
import com.example.rtacodechallenge.base.BaseFragment
import com.example.rtacodechallenge.data.model.CountriesList
import com.example.rtacodechallenge.data.model.Country
import com.example.rtacodechallenge.utils.UseCaseResponse
import kotlinx.android.synthetic.main.fragment_countries_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class CountriesListFragment : BaseFragment() {
    override val layout: Int
        get() = R.layout.fragment_countries_list
    private val viewModel: CountryListViewModel by viewModel()
    private var countriesList = listOf<Country>()
    private lateinit var countryAdapter: CountryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpViewModel()
//        searchOnCountriesList()
    }

    private fun setUpViewModel() {
        viewModel.getCountriesList().observe(viewLifecycleOwner, { response ->
            when (response) {
                is UseCaseResponse.Success -> response.data?.let { data ->
                    countriesList = data
                    countryAdapter.submitList(data)
                }
            }
        })
    }


    private fun setUpViews() {
        countryAdapter = CountryAdapter(mutableListOf(), object : CountryAdapterCallback {
            override fun onAddClicked(country: Country) {
                viewModel.handleToggleItemsSelection(country)
            }
        })
        rvCountries.adapter = countryAdapter
        btnDone.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun searchOnCountriesList() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(editable: Editable?) {
                filter(editable.toString())
            }

        })
    }

    private fun filter(keyword: String) {
//        mutableListOf<Country>().apply {
//            for (item in countriesList) {
//                if (item.name.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT)))
//                    add(item)
//            }
//            countryAdapter.updateItem(this)
//        }
    }
}