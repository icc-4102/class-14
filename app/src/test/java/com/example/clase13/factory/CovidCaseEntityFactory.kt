package com.example.clase13.factory

import com.example.clase13.model.CovidCaseEntity

fun covidCaseEntityFactory(): CovidCaseEntity {
    return CovidCaseEntity(
        "Chile",
        "Bandera de chile",
        "10000",
        "20",
        "3843",
        "20",
        "20",
        "73"
    )
}