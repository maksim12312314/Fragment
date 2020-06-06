package com.example.myapplication

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListViewModelFactory(val state: Bundle?):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(state) as T
    }

}





class ListViewModel(state: Bundle?):ViewModel() {
    val items:LiveData<List<ToDoModel>> = state?.let{
        val list = it.getParcelableArrayList<Parcelable>("LIST")
        val listik = list as List<ToDoModel>
        wrapInMutableLiveData(listik)


    }?:ListLiveData()

    fun wrapInMutableLiveData(items:List<ToDoModel>): LiveData<List<ToDoModel>> = MutableLiveData<List<ToDoModel>>().apply {
        postValue(items)
    }

}