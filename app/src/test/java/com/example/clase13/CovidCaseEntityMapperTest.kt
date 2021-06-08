package com.example.clase13

import com.example.clase13.factory.covidCaseModelFactory
import com.example.clase13.model.CovidCaseEntityMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class CovidCaseEntityMapperTest {

    val mapper = CovidCaseEntityMapper()

    @Test
    fun `given a covid case model, expect a covid case entity`(){
        val covidCaseModel = covidCaseModelFactory()
        val covidCaseEntity = mapper.mapToCached(covidCaseModel)
        assertEquals(covidCaseEntity.active,covidCaseModel.active.toString())
        assertNotEquals(covidCaseEntity.active,covidCaseModel.active)
    }

    @Test
    fun `given a covid case model with today deaths null, expect to fail`() {
        val covidCaseModel = covidCaseModelFactory()
        covidCaseModel.todayDeaths = null
        Assert.assertThrows(NullPointerException::class.java) {
            mapper.mapToCached(covidCaseModel)
        }
    }

}