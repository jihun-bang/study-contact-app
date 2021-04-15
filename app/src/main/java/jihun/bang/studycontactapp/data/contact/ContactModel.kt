package jihun.bang.studycontactapp.data.contact

data class CreateContactModel(val name: String, val email: String)

data class ContactModel(val id: Long, val name: String, val email: String)

data class ContactsResponse(val contacts: List<ContactModel>)

data class ContactResponse(val contact: ContactModel)

