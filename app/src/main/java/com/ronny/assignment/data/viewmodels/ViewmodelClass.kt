package com.ronny.assignment.data.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.ronny.assignment.data.api.ResponseStatus
import com.ronny.assignment.data.database.Item
import com.ronny.assignment.models.TrendingRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewmodelClass : ViewModel() {
    var currentPage = 0
    var currentPageDB = 0
    var items: MutableLiveData<List<Item>> = MutableLiveData()
    var responseStatus=MutableLiveData<ResponseStatus<TrendingRepo>>()

    fun ge3tApiData(apiService:RespostoryClass,url: String,sort:String,order:String,q:String,per_page:String){
        viewModelScope.launch{
                try {
                    responseStatus.value=ResponseStatus.loading()
                    currentPage++
                    val res = apiService.getRepo(url,sort, order,currentPage,q,per_page)
                    if (res.isSuccessful) {
                        items.value=res.body()!!.items
                        responseStatus.value=ResponseStatus.success(res.body())
                        withContext(Dispatchers.IO)
                        {

                            for (item in items.value!!) {
                                item.page= currentPage
                            }
                            apiService.createTable(items.value!!)
                        }
                    } else {
                        responseStatus.value=ResponseStatus.error("Failed")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    responseStatus.value=ResponseStatus.error("Failed")
                }
        }
    }

    fun getDBData(apiService:RespostoryClass)
    {
        currentPageDB++
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                items.postValue(apiService.getItem(currentPageDB.toLong()))
            }
        }
    }
}