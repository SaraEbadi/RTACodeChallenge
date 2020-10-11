package com.example.rtacodechallenge.feature.countrieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rtacodechallenge.data.model.Country
import com.example.rtacodechallenge.data.repository.CountriesRepository
import com.example.rtacodechallenge.utils.UseCaseResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class CountryListViewModel(private val repository: CountriesRepository) : ViewModel() {

    private val _selectedList: MutableLiveData<List<String>> = MutableLiveData()

    val selectedList: LiveData<List<String>>?
        get() = _selectedList

    private var selectedCountriesList = mutableListOf<String>()

    init {
        getCountriesList()
    }

    fun getCountriesList(): LiveData<UseCaseResponse<List<Country>>> {
        val liveData : MutableLiveData<UseCaseResponse<List<Country>>> = MutableLiveData()
        viewModelScope.launch {
            liveData.postValue(handleCountriesListResponse(repository.getCountries()))
        }
        return liveData
    }

    private fun handleCountriesListResponse(response: Response<List<Country>>): UseCaseResponse<List<Country>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return UseCaseResponse.Success(it)
            }
        }
        return UseCaseResponse.Error(response.message())
    }

    fun handleToggleItemsSelection(country: Country){
        country.apply {
            if (isAdded) {
                selectedCountriesList.add(name)
            }else
                selectedCountriesList.remove(name)
        }
        _selectedList.value = selectedCountriesList
    }

    fun getLiveData(): LiveData<List<String>> =
        _selectedList
}