package com.assignment.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.R
import com.assignment.model.Data
import com.assignment.ui.adapter.DataAdapter
import com.assignment.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class DataListActivity : AppCompatActivity(), View.OnClickListener {

    var adapter : DataAdapter? = null
    var viewModel: DataViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)

        if (isNetworkAvailable()!!){
            observeViewModel(viewModel!!)
        }else{
            Toast.makeText(
                applicationContext,
                "Please check network connection",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnRefresh.setOnClickListener(this)
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            observeViewModel(viewModel!!)
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun observeViewModel(viewModel: DataViewModel) {
        progress.visibility = View.VISIBLE
        viewModel.dataListObservable.observe(this, Observer {
            progress.visibility = View.GONE
                if (it!=null){
                    getData(it)
                }
        })
    }

    private fun getData(it: Data) {
        title = it.title
        adapter?.list = it.rows
        recyclerviewCanada?.adapter = DataAdapter(it.rows,this)
        recyclerviewCanada?.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerviewCanada?.setHasFixedSize(true)
    }

    //network check we need add in base class but now i am using sample so i created this class only
    private fun isNetworkAvailable(): Boolean? {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnRefresh ->{
                observeViewModel(viewModel!!)
            }
        }
    }
}