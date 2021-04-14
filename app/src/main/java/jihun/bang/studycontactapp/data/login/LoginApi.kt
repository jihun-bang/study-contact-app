package jihun.bang.studycontactapp.data.login

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/login")
    fun loginRequest(@Body model: LoginModel): Single<LoginResultModel>
}