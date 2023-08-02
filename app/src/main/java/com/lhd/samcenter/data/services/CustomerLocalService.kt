package com.lhd.samcenter.data.services

import com.lhd.samcenter.data.database.daos.CustomerDao
import javax.inject.Inject

class CustomerLocalService @Inject constructor(private val customerDao: CustomerDao) {
}