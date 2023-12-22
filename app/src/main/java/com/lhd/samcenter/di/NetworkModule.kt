package com.lhd.samcenter.di

import android.app.Application
import android.content.Context
import com.lhd.samcenter.BuildConfig
import com.lhd.samcenter.data.apis.FakeStoreApi
import com.lhd.samcenter.data.repositories.AssetDownloadWorkerRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    fun provideFakeStoreAPI(@Named("FakeStore") retrofit: Retrofit): FakeStoreApi {
        return retrofit.create(FakeStoreApi::class.java)
    }


    // download assets repository
//    @Provides
//    fun provideAssetRepository(context: Context): AssetDownloadWorkerRepository {
//        return AssetDownloadWorkerRepository(context)
//    }

    @Module
    @InstallIn(ViewModelComponent::class)
    object AssetModule {

        @Provides
        @ViewModelScoped
        fun provideContext(application: Application): Context {
            return application.applicationContext
        }

        @Provides
        @ViewModelScoped
        fun provideAssetRepository(context: Context): AssetDownloadWorkerRepository {
            return AssetDownloadWorkerRepository(context)
        }
    }


//    @Provides
//    @Singleton
//    fun providerFirebaseStorage(): FirebaseFirestore {
//        return FirebaseFirestore.getInstance()
//    }


    @Provides
    @Singleton
    @Named("FakeStore")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

}