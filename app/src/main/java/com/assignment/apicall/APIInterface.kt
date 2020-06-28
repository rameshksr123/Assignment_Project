package com.assignment.apicall

import com.assignment.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getDataList(): Call<Data?>?

}