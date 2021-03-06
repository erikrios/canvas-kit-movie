package io.github.erikrios.canvaskitmovie.core.di

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.erikrios.canvaskitmovie.core.BuildConfig
import io.github.erikrios.canvaskitmovie.core.data.repository.MovieRepository
import io.github.erikrios.canvaskitmovie.core.data.repository.TrendingRepository
import io.github.erikrios.canvaskitmovie.core.data.repository.TvShowRepository
import io.github.erikrios.canvaskitmovie.core.data.source.local.LocalDataSource
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.CinemaDatabase
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.DATABASE_NAME
import io.github.erikrios.canvaskitmovie.core.data.source.remote.RemoteDataSource
import io.github.erikrios.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiHelper
import io.github.erikrios.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiHelperImpl
import io.github.erikrios.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiService
import io.github.erikrios.canvaskitmovie.core.domain.repository.IMovieRepository
import io.github.erikrios.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.github.erikrios.canvaskitmovie.core.domain.repository.ITvShowRepository
import io.github.erikrios.canvaskitmovie.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CinemaDatabase>().movieDao() }
    factory { get<CinemaDatabase>().tvShowDao() }
    factory { get<CinemaDatabase>().trendingDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("erikriosetiawan".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CinemaDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        Interceptor { chain ->
            var request = chain.request()
            var originalHttpUrl = request.url

            originalHttpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            request = request.newBuilder()
                .url(originalHttpUrl)
                .build()

            return@Interceptor chain.proceed(request)
        }
    }
    single<Gson> {
        GsonBuilder()
            .setLenient()
            .create()
    }
    single {
        val hostname = BuildConfig.HOST
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .callTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(get<Interceptor>())
                .certificatePinner(certificatePinner)
                .build()
        } else {
            OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(get<Interceptor>())
                .certificatePinner(certificatePinner)
                .build()
        }
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(TheMovieDatabaseApiService::class.java)
    }
    single<TheMovieDatabaseApiHelper> {
        TheMovieDatabaseApiHelperImpl(get())
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
    single<ITvShowRepository> { TvShowRepository(get(), get(), get()) }
    single<ITrendingRepository> { TrendingRepository(get(), get(), get()) }
}