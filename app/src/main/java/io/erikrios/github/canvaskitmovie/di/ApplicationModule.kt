package io.erikrios.github.canvaskitmovie.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.erikrios.github.canvaskitmovie.BuildConfig
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepository
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepositoryImpl
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.data.source.remote.RemoteDataSource
import io.erikrios.github.canvaskitmovie.network.TheMovieDatabaseApiHelper
import io.erikrios.github.canvaskitmovie.network.TheMovieDatabaseApiHelperImpl
import io.erikrios.github.canvaskitmovie.network.TheMovieDatabaseApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Named("apiKey")
    fun provideApiKey() = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideLogger() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideQueryInterceptor(@Named("apiKey") apiKey: String) = Interceptor { chain ->
        var request = chain.request()
        var originalHttpUrl = request.url

        originalHttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        request = request.newBuilder()
            .url(originalHttpUrl)
            .build()

        return@Interceptor chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor, queryInterceptor: Interceptor) =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .callTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(queryInterceptor)
                .addInterceptor(logger)
                .build()
        } else {
            OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(queryInterceptor)
                .build()
        }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("baseUrl") baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideTheMovieDatabaseApiService(retrofit: Retrofit): TheMovieDatabaseApiService =
        retrofit.create(TheMovieDatabaseApiService::class.java)

    @Provides
    @Singleton
    fun provideTheMovieDatabaseApiHelper(apiHelper: TheMovieDatabaseApiHelperImpl): TheMovieDatabaseApiHelper =
        apiHelper

    @Provides
    @Singleton
    @Named("remoteDataSource")
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): DataSource = remoteDataSource

    @Singleton
    @Provides
    fun provideCinemaRepositoryImpl() = CinemaRepositoryImpl() as CinemaRepository
}