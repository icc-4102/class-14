package com.example.clase13

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.clase13.factory.covidCaseEntityFactory
import com.example.clase13.factory.covidCaseModelFactory
import com.example.clase13.model.CovidCaseEntityMapper
import com.example.clase13.model.CovidCasesDao
import com.example.clase13.model.EntityMapper
import com.example.clase13.modules.appModule
import com.example.clase13.modules.networkModule
import com.example.clase13.repository.CovidCaseRepository
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.atLeast
import org.mockito.Mockito.times
import kotlin.test.assertEquals

class CovidCaseRepositoryTest: KoinTest {

    val covidCaseRepository by inject<CovidCaseRepository>()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(listOf(appModule, networkModule))
    }

    @get:Rule
    val mockProvider = MockProviderRule.create{ clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `when calling load local cases function, expected to be call at least one time`(){
        val daoMock = declareMock<CovidCasesDao> {
            given(getAllCases()).willReturn(listOf(covidCaseEntityFactory()))
        }
        covidCaseRepository
        Mockito.verify(daoMock,atLeast(1)).getAllCases()
    }

    @Test
    fun `when calling load case from a country, expected to be success`(){
        val daoMock = declareMock<CovidCasesDao> {
            given(getCountry("Chile")).willReturn(covidCaseEntityFactory())
        }
        val entityMock = declareMock<CovidCaseEntityMapper> {
            given(mapFromCached(covidCaseEntityFactory())).willReturn(covidCaseModelFactory())
        }
        covidCaseRepository.loadCaseFromLocation("Chile")
        Mockito.verify(daoMock,times(1)).getCountry("Chile")
        Mockito.verify(entityMock,times(1)).mapFromCached(covidCaseEntityFactory())
        assertEquals(daoMock.getCountry("Chile"), covidCaseEntityFactory())
    }


}