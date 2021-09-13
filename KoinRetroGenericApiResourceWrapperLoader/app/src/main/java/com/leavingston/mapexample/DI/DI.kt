package com.leavingston.mapexample

import org.koin.dsl.module
import com.leavingston.mapexample.ViewModel.downloadDataViewModel
import com.leavingston.mapexample.networking.RemoteApiService
import com.leavingston.mapexample.repository.downloadDataRepo
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val module = module {

    var retrofitInstance : Retrofit? = null

    fun provideUseApi(retrofit: Retrofit) : RemoteApiService{
        return  retrofit.create((RemoteApiService::class.java))
    }


    fun provideHttpClient() : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        return  okHttpClient.build()
    }

    fun provideRetrofit( client: OkHttpClient) : Retrofit {
        return if (retrofitInstance == null){
            retrofitInstance = Retrofit.Builder()
                .baseUrl(constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            retrofitInstance as Retrofit
        }
        else retrofitInstance as Retrofit
    }

    single { provideUseApi(get()) }
    single { provideHttpClient() }
    single { downloadDataRepo(get()) }
    single { provideRetrofit(get() ) }
    viewModel { downloadDataViewModel(get()) }


}