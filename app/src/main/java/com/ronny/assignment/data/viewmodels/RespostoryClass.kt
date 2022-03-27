package com.ronny.assignment.data.viewmodels

import com.ronny.assignment.data.api.ApiService
import com.ronny.assignment.data.database.DAOInterface
import com.ronny.assignment.data.database.Item
import javax.inject.Inject

class RespostoryClass @Inject constructor(val apiService: ApiService,val daoInterface: DAOInterface) {

    suspend fun getRepo(url: String,sort:String,order:String,page:Int,q:String,per_page:String) = apiService.getRepo(url, sort, order, page, q, per_page)

     suspend fun createTable(item: List<Item>){
        daoInterface.insertItems(item)
    }

     suspend fun getItem(page:Long):List<Item>{
        return daoInterface.getItems(page)
    }

}