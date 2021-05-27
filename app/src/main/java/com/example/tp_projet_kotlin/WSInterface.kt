package com.example.tp_projet_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WSInterface {
    @GET("madrental/get-vehicules.php")
    fun listCar(): Call<List<RetourWSGet>>



}