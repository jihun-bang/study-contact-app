package jihun.bang.studycontactapp

import android.app.Application
import com.facebook.stetho.Stetho
import jihun.bang.studycontactapp.di.networkModule
import jihun.bang.studycontactapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(networkModule, viewModelModule)
        }
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

    }
}