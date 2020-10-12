package com.example.rtacodechallenge.feature.countrieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rtacodechallenge.data.model.Country
import com.example.rtacodechallenge.data.repository.CountriesRepository
import com.example.rtacodechallenge.utils.GeneralResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class CountryListViewModel(private val repository: CountriesRepository) : ViewModel() {

    val selectedCountriesListLiveData: LiveData<List<String>>?
        get() = selectedCountiesListMutableLiveData

    val countriesListLiveData: LiveData<GeneralResponse<List<Country>>>
        get() = countriesListMutableLiveData

    private val selectedCountiesListMutableLiveData = MutableLiveData<List<String>>()
    private val countriesListMutableLiveData = MutableLiveData<GeneralResponse<List<Country>>>()
    private var selectedCountriesList = mutableListOf<String>()

    init {
        getCountriesList()
    }

    private fun getCountriesList() {
        countriesListMutableLiveData.postValue(GeneralResponse.Loading())
        viewModelScope.launch {
            countriesListMutableLiveData.postValue(handleCountriesListResponse(repository.getCountries()))
        }
    }

    private fun handleCountriesListResponse(response: Response<List<Country>>): GeneralResponse<List<Country>> {
        if (response.isSuccessful) {
            response.body()?.let { return GeneralResponse.Success(it) }
        }
        return GeneralResponse.Error(response.message())
    }

    fun handleToggleItemsSelection(country: Country) {
        country.apply {
            if (isAdd && !selectedCountriesList.contains(name)) {
                selectedCountriesList.add(name)
            } else {
                selectedCountriesList.remove(name)
            }
        }
        selectedCountiesListMutableLiveData.value = selectedCountriesList
    }

}