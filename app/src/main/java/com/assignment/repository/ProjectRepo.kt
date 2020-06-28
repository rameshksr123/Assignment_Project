package com.assignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.apicall.APIInterface
import com.assignment.apiclient.APIClient.client
import com.assignment.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectRepo {

    val data = MutableLiveData<Data?>()

    private var apiInterface: APIInterface? = null

    val dataList: LiveData<Data?>
        get() {
            apiInterface!!.getDataList()!!.enqueue(object : Callback<Data?> {
                override fun onResponse(
                    call: Call<Data?>,
                    response: Response<Data?>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<Data?>,
                    t: Throwable
                ) {
                    Log.e("Error", t.message.toString())
                }
            })
            return data
        }

    companion object {
        private const val TAG = "Repository"
        val instance = ProjectRepo()
    }

    init {
        apiInterface = client!!.create(APIInterface::class.java)
    }
}