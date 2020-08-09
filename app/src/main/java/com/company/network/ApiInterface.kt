package com.company.network

import retrofit2.Call
import com.company.testapp.model.Todo
import retrofit2.http.GET


interface ApiInterface {
    @GET("todos")
    fun fetchAllPosts(): Call<List<Todo>>
}