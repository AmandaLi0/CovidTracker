package com.example.covidtracker

import android.text.BoringLayout


//make sure variable name matches API's json variable name
data class CountyData(
    val state: String?,
    val county: String?,
    val cdcTransmissionLevel: Int?,
    val lastUpdatedDate: String?,
    val actuals: Actuals,
    val metrics: Metrics
){
    data class Actuals(
        val cases: Int?,
        val newCases: Int?
    )
    data class Metrics(
        val testPositivityRatio: Double?,
        val weeklyNewCasesPer100K: Double?
    )
}
