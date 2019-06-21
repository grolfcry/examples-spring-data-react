package com.tasks.web

import com.tasks.web.model.FormData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FormControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `test REST API post data`() {
        val result = restTemplate.postForEntity("/sendform", FormData(-1, "username", "123", "aaa@aaa.ru", "fio", null), String::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(null, result.body)
    }

}