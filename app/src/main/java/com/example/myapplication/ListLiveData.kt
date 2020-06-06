package com.example.myapplication

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

data class Ret (
    val userId:String,
    val id:Int,
    val title:String,
    val completed:Boolean

)


fun getPage(url:String):Ret? {

    val client = OkHttpClient()


    val request = Request.Builder()
        .url(url)
        .build();

    try {
        val response = client.newCall(request).execute()
        val text = response.body?.string()
        val ret = Gson().fromJson(text, Ret::class.java)
        return ret
    }
    catch (e:Throwable) {
    }
return null

}


class ListLiveData():LiveData<List<ToDoModel>>() {
    override fun onActive() {
        super.onActive()
        GlobalScope.launch {
            val list = mutableListOf<ToDoModel>()
            val ret = getPage("https://jsonplaceholder.typicode.com/todos/1")
            ret?.apply {
                list.add(ToDoModel(ret.title, ret.completed.toString(), ret.id.toString()))
                postValue(list)
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
    }
}