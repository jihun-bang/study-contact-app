package jihun.bang.studycontactapp.data.contact

import io.reactivex.Single
import retrofit2.http.GET

interface ContactApi {
    @GET("api/contacts")
    fun getContacts(): Single<ContactsResponse>
}