package com.example.covidtracker

import android.os.Parcelable
import android.text.BoringLayout
import kotlinx.parcelize.Parcelize


//make sure variable name matches API's json variable name
@Parcelize
data class CountyData(
    val state: String?,
    val county: String?,
    val cdcTransmissionLevel: Int,
    val lastUpdatedDate: String?,
    val actuals: Actuals,
    val metrics: Metrics
) : Parcelable, Comparable<CountyData> {
    override fun compareTo(other: CountyData): Int {
        return this.cdcTransmissionLevel - other.cdcTransmissionLevel
    }
}
    @Parcelize
    data class Actuals(
        val cases: Int?,
        val newCases: Int?
    ) : Parcelable
    @Parcelize
    data class Metrics(
        val testPositivityRatio: Double?,
        val weeklyNewCasesPer100k: Double?
    ): Parcelable


