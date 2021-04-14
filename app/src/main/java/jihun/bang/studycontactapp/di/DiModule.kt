package jihun.bang.studycontactapp.di

import jihun.bang.studycontactapp.data.contact.ContactApi
import jihun.bang.studycontactapp.data.login.LoginApi
import jihun.bang.studycontactapp.ui.contact.ContactViewModel
import jihun.bang.studycontactapp.ui.login.LoginViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.35.133:5000")
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ContactApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.35.133:5000")
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }
}

val viewModelModule = module {
    viewModel { ContactViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}