package com.example.myfirstapp.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.data.model.people.PeopleItemModel
import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.data.repository.Repository
import com.example.myfirstapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _peopleList = MutableLiveData<NetworkResult<PeopleModel>>()
    val peopleList: LiveData<NetworkResult<PeopleModel>> = _peopleList

    private val _person = MutableLiveData<PeopleItemModel>()
    val person: LiveData<PeopleItemModel>
        get() = _person // question here about get() instead of normal assignment

    fun getPeopleList() {
        viewModelScope.launch {
            _peopleList.value = NetworkResult.Loading()
            val result = repository.getPeople()

            if (result.isSuccessful) {
                _peopleList.value = NetworkResult.Success(result.body()!!)
            } else {
                _peopleList.value = NetworkResult.Error(result.message())
            }
        }
    }

    fun setPerson(person: PeopleItemModel) {
        _person.value = person
    }
}