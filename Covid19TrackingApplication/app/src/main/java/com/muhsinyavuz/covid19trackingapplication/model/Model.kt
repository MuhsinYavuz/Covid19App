package com.muhsinyavuz.covid19trackingapplication.model


import com.google.gson.annotations.SerializedName
data class Model(
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("get")
    val `get`: String,
    @SerializedName("history")
    val history: List<History>,
    @SerializedName("parameters")
    val parameters: List<Any>,
    @SerializedName("response")
    val response: List<String>,
    @SerializedName("results")
    val results: Int,
    @SerializedName("statistics")
    var statistics: List<Statistic>
)data class History(
    @SerializedName("cases")
    val cases: CasesH,
    @SerializedName("continent")
    val continent: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("day")
    val day: String,
    @SerializedName("deaths")
    val deaths: DeathsH,
    @SerializedName("population")
    val population: Int,
    @SerializedName("tests")
    val tests: TestsH,
    @SerializedName("time")
    val time: String
) data class CasesH(
    @SerializedName("active")
    val active: Int,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("1M_pop")
    val mPop: String,
    @SerializedName("new")
    val new: String,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("total")
    val total: Int
) data class DeathsH(
    @SerializedName("1M_pop")
    val mPop: String,
    @SerializedName("new")
    val new: String,
    @SerializedName("total")
    val total: Int
)
data class TestsH(
    @SerializedName("1M_pop")
    val mPop: String,
    @SerializedName("total")
    val total: Int
) data class Statistic(
    @SerializedName("cases")
    val cases: Cases,
    @SerializedName("continent")
    val continent: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("day")
    val day: String,
    @SerializedName("deaths")
    val deaths: Deaths,
    @SerializedName("population")
    val population: Int,
    @SerializedName("tests")
    val tests: Tests,
    @SerializedName("time")
    val time: String
) data class Cases(
    @SerializedName("active")
    val active: Int,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("1M_pop")
    val mPop: String,
    @SerializedName("new")
    val new: String,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("total")
    val total: Int
)   data class Deaths(
    @SerializedName("1M_pop")
    val mPop: String,
    @SerializedName("new")
    val new: String,
    @SerializedName("total")
    val total: Int
)
data class Tests(
    @SerializedName("1M_pop")
    val mPop: String,
    @SerializedName("total")
    val total: Int
)


