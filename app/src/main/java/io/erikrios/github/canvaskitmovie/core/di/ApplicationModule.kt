package io.erikrios.github.canvaskitmovie.core.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.erikrios.github.canvaskitmovie.BuildConfig
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.erikrios.github.canvaskitmovie.core.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.core.data.source.remote.RemoteDataSource
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.DatabaseContract.DATABASE_NAME
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.CinemaDatabase
import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiHelper
import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiHelperImpl
import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiService
import io.erikrios.github.canvaskitmovie.core.utils.NetworkHelper
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

    @Provides
    @Singleton
    @Named("localeDataSource")
    fun provideLocaleDataSource(localDataSource: LocalDataSource): DataSource = localDataSource

    @Singleton
    @Provides
    fun provideCinemaRepositoryImpl(
        networkHelper: NetworkHelper,
        @Named("localeDataSource")
        localeDataSource: DataSource,
        @Named("remoteDataSource")
        remoteDataSource: DataSource
    ) =
        CinemaRepositoryImpl(networkHelper, localeDataSource, remoteDataSource) as ITrendingRepository

    @Singleton
    @Provides
    fun provideFavoriteCinemaDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CinemaDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(database: CinemaDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun provideFavoriteTvShowDao(database: CinemaDatabase) = database.tvShowDao()
}