package jihun.bang.studycontactapp.ui.contact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jihun.bang.studycontactapp.data.contact.*
import jihun.bang.studycontactapp.ui.BaseViewModel

class ContactViewModel(private val contactApi: ContactApi) : BaseViewModel() {
    private val _contactsLiveData = MutableLiveData<ContactsResponse>()
    val contactsLiveData: LiveData<ContactsResponse>
        get() = _contactsLiveData

    private val _createContactLiveData = MutableLiveData<ContactResponse>()
    val createContactLiveData: LiveData<ContactResponse>
        get() = _createContactLiveData

    private var cursorId: Long = 0
    private var hasMore = true

    fun getContacts() {
        hasMore = true
        addDisposable(
            contactApi
                .getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _contactsLiveData.value = it
                    Log.d("로그", "[ContactViewModel][getContacts] ${it.contacts.map { it.name }}")
                }, { e ->
                    Log.e("로그", "[ContactViewModel][getContacts] $e")
                })

        )
    }

    fun getMoreContacts() {
        cursorId += 30
        addDisposable(
            contactApi
                .getContacts(cursorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _contactsLiveData.value = it
                    Log.d("로그", "[ContactViewModel][getContacts] ${it.contacts.map { it.name }}")
                }, { e ->
                    Log.e("로그", "[ContactViewModel][getContacts] $e")
                })

        )
    }

    fun addContact(name: String, email: String) {
        addDisposable(
            contactApi
                .createContact(CreateContactModel(name, email))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("로그", "[ContactViewModel][addContact] $it")
                    _createContactLiveData.value = it
                    getContacts()
                }, { e ->
                    e.printStackTrace()
                    Log.e("로그", "[ContactViewModel][addContact] $e")
                })

        )
    }
}