package com.tasks.web

import com.tasks.web.db.FormDataRepository
import com.tasks.web.model.FormData
import com.tasks.web.service.ProcessCheckingResultServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ProcessCheckingResultServiceImplTest {
    @InjectMocks
    lateinit var service: ProcessCheckingResultServiceImpl
    @Mock
    lateinit var mailSender: MailSender
    @Mock
    lateinit var repository: FormDataRepository
    private val formData = FormData(null, "username", "123", "aaa@aaa.ru", "fio", true)
    @Captor
    lateinit var captor: ArgumentCaptor<FormData>

    @Before
    fun before() {
        Mockito.`when`(repository.save(formData)).thenReturn(formData.copy(id = 1))
    }
    @Test
    fun `test process method with mocks`() {
        service.process(formData)
        verify(repository).save(captor.capture()) //or verify(repository, only()).save(eq(formData))
        Assert.assertEquals(formData,captor.value)
        verify(mailSender, only()).send(safeEq(formData.email), anyString())
    }
    fun <T : Any> safeEq(value: T): T = eq(value) ?: value
}