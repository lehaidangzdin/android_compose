package com.lhd.samcenter.data.services

import com.lhd.samcenter.base.network.BaseRemoteService
import com.lhd.samcenter.base.network.NetworkResult
import com.lhd.samcenter.data.apis.FakeStoreApi
import com.lhd.samcenter.data.modelJson.ResAddProductJson
import com.lhd.samcenter.data.modelJson.ResProductJson
import com.lhd.samcenter.data.models.Product
import javax.inject.Inject

class FakeStoreRemoteService @Inject constructor(private val fakeStoreApi: FakeStoreApi) :
    BaseRemoteService() {

    suspend fun getAllProduct(): NetworkResult<List<ResProductJson>> {
        return callApi { fakeStoreApi.getAllProduct() }
    }

    suspend fun getAProduct(id: String): NetworkResult<ResProductJson> {
        return callApi { fakeStoreApi.getAProduct(id) }
    }

    suspend fun addProduct(product: Product): NetworkResult<ResAddProductJson> {
        return callApi { fakeStoreApi.addProduct(product) }
    }

    suspend fun deleteProduct(id: String): NetworkResult<ResProductJson> {
        return callApi { fakeStoreApi.deleteProduct(id) }
    }


}