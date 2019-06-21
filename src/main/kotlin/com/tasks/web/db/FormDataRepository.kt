package com.tasks.web.db

import com.tasks.web.model.FormData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FormDataRepository : CrudRepository<FormData, Long>