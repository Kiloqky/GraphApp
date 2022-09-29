package ru.kiloqky.graphtableapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiloqky.graphtableapp.BuildConfig
import ru.kiloqky.graphtableapp.data.api.Api
import ru.kiloqky.graphtableapp.data.api.apiworker.ApiWorker
import ru.kiloqky.graphtableapp.data.api.apiworker.ApiWorkerImpl
import ru.kiloqky.graphtableapp.data.repository.gallery.GalleryRepositoryImpl
import ru.kiloqky.graphtableapp.data.repository.point.PointRepositoryImpl
import ru.kiloqky.graphtableapp.domain.repository.GalleryRepository
import ru.kiloqky.graphtableapp.domain.repository.PointRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

    @Provides
    fun provideApiWorker(api: Api): ApiWorker = ApiWorkerImpl(api)

    @Provides
    fun providePointsRepository(apiWorker: ApiWorker): PointRepository =
        PointRepositoryImpl(apiWorker)

    @Provides
    fun provideGalleryRepository(@ApplicationContext applicationContext: Context): GalleryRepository =
        GalleryRepositoryImpl(applicationContext)

    @Provides
    fun provideResources(@ApplicationContext applicationContext: Context) = applicationContext.resources
}