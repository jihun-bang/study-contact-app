package jihun.bang.studycontactapp.data

data class ContactModel(val email: String, val id: Int, val name: String)

data class ContactsResponse(val contacts: List<ContactModel>)