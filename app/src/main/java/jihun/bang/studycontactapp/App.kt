package jihun.bang.studycontactapp

import android.app.Application
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
    }
}