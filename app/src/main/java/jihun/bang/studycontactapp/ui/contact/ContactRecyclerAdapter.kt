package jihun.bang.studycontactapp.ui.contact

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jihun.bang.studycontactapp.data.contact.ContactModel
import jihun.bang.studycontactapp.data.contact.ContactsResponse
import jihun.bang.studycontactapp.databinding.ContactItemBinding
import okhttp3.internal.notify

class ContactRecyclerAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {
    val modelList = mutableListOf<ContactModel>()

    // 목록의 아이템 수
    override fun getItemCount(): Int {
        Log.d("로그", "[RecyclerAdapter][getItemCount] ${modelList.size}")
        return modelList.size
    }

    // View Holder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // 연결할 레이아웃 설정
        Log.d("로그", "[RecyclerAdapter][onCreateViewHolder] Called")
        return RecyclerViewHolder(
            ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Log.d("로그", "[RecyclerAdapter][onBindViewHolder] Called")
        holder.bind(modelList[position])
    }

    fun updateItem(contactsResponse: ContactsResponse) {
        Log.d("로그", "[RecyclerAdapter][updateItem] Called")
        this.modelList.clear()
        this.modelList.addAll(contactsResponse.contacts)
        notifyDataSetChanged()
    }
}