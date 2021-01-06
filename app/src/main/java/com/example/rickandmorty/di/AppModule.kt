package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.remote.CharacterRemoteDataSource
import com.example.rickandmorty.data.remote.CharacterService
import com.example.rickandmorty.data.repository.CharacterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(ApplicationComponent::class)
object AppModule : CoroutineScope {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit {
        var boolUseProxy = true;
        if ( boolUseProxy ) {
            var lsProxyHost = "dijproxy.mcs.priv"
            var lsProxyPort = 8080
            var loProxy : Proxy
            var loOkHttpClient: OkHttpClient = OkHttpClient()
            launch {
                loProxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(lsProxyHost, lsProxyPort))
                loOkHttpClient = OkHttpClient.Builder().proxy(loProxy).build()
            }
            Thread.sleep(2000)
            return Retrofit.Builder()
                .client(loOkHttpClient)
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        else {
            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService) = CharacterRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: CharacterDao) =
        CharacterRepository(remoteDataSource, localDataSource)

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}