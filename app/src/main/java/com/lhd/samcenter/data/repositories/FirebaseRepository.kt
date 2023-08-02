//package com.lhd.samcenter.data.repositories
//
//import com.lhd.samcenter.data.models.Product
//import com.lhd.samcenter.di.IoDispatcher
//import com.example.myapplication.data.services.FirebaseRemoteService
//import kotlinx.coroutines.CoroutineDispatcher
//
//import javax.inject.Inject
//
//class FirebaseRepository @Inject constructor(
//    private val firebaseRemoteService: FirebaseRemoteService,
//    @IoDispatcher private val dispatcher: CoroutineDispatcher
//) {
//    suspend fun getAll(): List<Product> {
//        return firebaseRemoteService.getAll()
//    }
//
//    suspend fun addUser(product: Product) {
//
//    }
//
//}