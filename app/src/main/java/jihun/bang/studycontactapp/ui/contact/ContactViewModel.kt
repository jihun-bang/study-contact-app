package jihun.bang.studycontactapp.ui.contact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jihun.bang.studycontactapp.data.contact.ContactApi
import jihun.bang.studycontactapp.data.contact.ContactsResponse
import jihun.bang.studycontactapp.ui.BaseViewModel

class ContactViewModel(private val contactApi: ContactApi) : BaseViewModel() {
    private val _contactsLiveData = MutableLiveData<ContactsResponse>()
    val contactsLiveData: LiveData<ContactsResponse>
        get() = _contactsLiveData

    init {
        getContacts()
    }

    fun getContacts() {
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
}