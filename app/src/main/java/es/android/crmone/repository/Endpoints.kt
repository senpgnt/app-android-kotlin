package es.android.crmone.repository

import es.android.crmone.models.User
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {
    @GET("todos")
    fun getPeople(): Call<List<User>>
}