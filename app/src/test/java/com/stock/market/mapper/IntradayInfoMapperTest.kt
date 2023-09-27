package com.stock.market.mapper

import com.stock.market.data.mapper.toIntradayInfo
import com.stock.market.data.remote.dto.IntradayInfoDto
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

class IntradayInfoMapperTest {

    @Test
    fun testToIntradayInfo() {
        // Create a mock for IntradayInfoDto
        val mockDto = mock(IntradayInfoDto::class.java)

        // Define the values you want to return when properties are accessed
        `when`(mockDto.timestamp).thenReturn("2023-09-27 14:30:00")
        `when`(mockDto.close).thenReturn(100.0)

        // Call the toIntradayInfo() function
        val intradayInfo = mockDto.toIntradayInfo()

        // Verify that IntradayInfo is created correctly
        assertEquals(LocalDateTime.of(2023, 9, 27, 14, 30, 0), intradayInfo.date)
        assertEquals(100.0, intradayInfo.close)

        // Verify that the properties of mockDto were accessed
        verify(mockDto).timestamp
        verify(mockDto).close

        // Optionally, you can verify that there were no other interactions with the mock
        verifyNoMoreInteractions(mockDto)
    }
}