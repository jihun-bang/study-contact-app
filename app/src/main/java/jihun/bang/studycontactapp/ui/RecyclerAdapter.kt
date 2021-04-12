package jihun.bang.studycontactapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jihun.bang.studycontactapp.R
import jihun.bang.studycontactapp.data.ContactModel
import jihun.bang.studycontactapp.data.ContactsResponse

class RecyclerAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {
    val modelList = mutableListOf<ContactModel>()
    // View Holder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // 연결할 레이아웃 설정
        Log.d("로그", "[RecyclerAdapter][onCreateViewHolder] Called")
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        )
    }

    // 목록의 아이템 수
    override fun getItemCount(): Int {
        return modelList.size
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(modelList[position])
    }

    fun submitList(modelList: List<ContactModel>) {
        this.modelList.clear()
        this.modelList.addAll(modelList)
    }

    fun updateItem(contactsResponse: ContactsResponse){
        modelList.addAll(contactsResponse.contacts - modelList)
    }
}