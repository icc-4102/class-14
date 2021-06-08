package com.example.clase13.factory

import com.example.clase13.model.CountryInfo
import com.example.clase13.model.CovidCaseModel

fun covidCaseModelFactory(): CovidCaseModel {
    return CovidCaseModel(
        null,
        "Chile",
        CountryInfo("Bandera de chile"),
        1000,
        20,
        40,
        1,
        50,
        30
    )
}