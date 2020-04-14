package com.relaxingproject

import com.relaxingproject.classes.Log
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date
class LogTest {
    @Test
    fun logClassSaveTitle() {
        var log = Log()
        log.title = "Test"
        assertEquals("Test", log.title)
    }
    @Test
    fun logClassSaveText() {
        var log = Log()
        log.text = "This is a test text"
        assertEquals("This is a test text", log.text)
    }
    @Test
    fun logClassSaveDate() {
        var log = Log()
        //log.date = Date(2020,4,1)
        //assertEquals(Date(2020,4,1), log.date)
        log.date = "1/1/2020"
        assertEquals("1/1/2020", log.date)
    }
}
