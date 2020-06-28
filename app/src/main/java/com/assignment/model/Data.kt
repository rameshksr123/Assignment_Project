package com.assignment.model
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("rows")
    var rows: List<Row>? = null
}