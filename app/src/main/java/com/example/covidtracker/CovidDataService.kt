package com.example.covidtracker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CovidDataService {
    //make functions to retrieve date from our endpoints

    //get county data for all countries in a state
    //only include path to the file,
    //query params are handled in the function parameters
    @GET("county/{state}.json")
    fun getCountyDataByState(
        @Path("state") state: String,
        @Query("apiKey") apiKey: String
    ) : Call<List<CountyData>>
}