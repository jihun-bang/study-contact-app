package jihun.bang.studycontactapp.data.contact

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ContactApi {
    @GET("api/contacts")
    fun getContacts(): Single<ContactsResponse>

    @POST("api/contact")
    fun createContact(@Body model: CreateContactModel): Single<ContactResponse>

    @GET("api/contacts/{cursorId}")
    fun getContacts(@Path("cursorId") cursorId: Long): Single<ContactsResponse>
}