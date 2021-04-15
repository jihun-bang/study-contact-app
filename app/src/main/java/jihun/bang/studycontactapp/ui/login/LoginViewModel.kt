package jihun.bang.studycontactapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jihun.bang.studycontactapp.data.login.LoginApi
import jihun.bang.studycontactapp.data.login.LoginModel
import jihun.bang.studycontactapp.data.login.LoginResultModel
import jihun.bang.studycontactapp.ui.base.BaseViewModel

class LoginViewModel(private val loginApi: LoginApi) : BaseViewModel() {
    private val _loginRequestLiveData = MutableLiveData<LoginResultModel>()
    val loginRequestLiveData: LiveData<LoginResultModel>
        get() = _loginRequestLiveData

    fun loginRequest(id: String, password: String) {
        addDisposable(
            loginApi
                .loginRequest(LoginModel(id, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _loginRequestLiveData.value = it
                    Log.d("로그", "[LoginViewModel][loginRequest] $it")
                }, { e ->
                    Log.e("로그", "[LoginViewModel][loginRequest] $e")
                })

        )
    }
}