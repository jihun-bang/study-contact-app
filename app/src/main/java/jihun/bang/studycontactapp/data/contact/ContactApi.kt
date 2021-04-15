package jihun.bang.studycontactapp.data.contact

import io.reactivex.Single
import retrofit2.http.*

interface ContactApi {
    @GET("api/contacts")
    fun getContacts(): Single<ContactsResponse>

    @GET("api/contacts/{cursorId}")
    fun getContacts(@Path("cursorId") cursorId: Long): Single<ContactsResponse>

    @POST("api/contact")
    fun createContact(@Body model: CreateContactModel): Single<ContactResponse>

    @DELETE("api/contact/{id}")
    fun deleteContact(@Path("id") id: Long): Single<ContactResponse>
}