package com.example.rtacodechallenge.feature.countries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rtacodechallenge.R
import com.example.rtacodechallenge.base.BaseFragment
import com.example.rtacodechallenge.feature.countrieslist.CountryListViewModel
import kotlinx.android.synthetic.main.fragment_countries.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class CountriesFragment : BaseFragment() {
    override val layout: Int
        get() = R.layout.fragment_countries
    private val viewModel: CountryListViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        viewModel.getLiveData().observe(viewLifecycleOwner,{list->
            tvCountries.text = list.joinToString { it }
        })


        btnChooseCountries.setOnClickListener {
            findNavController().navigate(CountriesFragmentDirections
                .actionCountriesFragmentToCountriesListFragment())
        }
    }

}