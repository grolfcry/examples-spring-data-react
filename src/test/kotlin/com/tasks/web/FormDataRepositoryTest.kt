package com.tasks.web

import com.tasks.web.db.FormDataRepository
import com.tasks.web.model.FormData
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class FormDataRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var repository: FormDataRepository

    @Test
    fun `test save and get`() {
        val formData =  FormData(null, "username", "123", "aaa@aaa.ru", "fio", null)
        val saved = entityManager.persist(formData)
        entityManager.flush()

        val found = repository.findById(saved.id!!).get()
        Assert.assertEquals(found.userName, formData.userName)
    }
}