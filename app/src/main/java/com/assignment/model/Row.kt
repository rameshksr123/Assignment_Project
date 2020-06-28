package com.assignment.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Row {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("imageHref")
    var imageHref: String? = null
}