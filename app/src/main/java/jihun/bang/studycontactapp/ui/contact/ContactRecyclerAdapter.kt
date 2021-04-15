package jihun.bang.studycontactapp.ui.contact

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import jihun.bang.studycontactapp.data.contact.ContactModel
import jihun.bang.studycontactapp.databinding.ContactItemBinding
import jihun.bang.studycontactapp.ui.contact.util.ContactDiffCallback
import jihun.bang.studycontactapp.ui.contact.util.RecyclerViewInterface

class ContactRecyclerAdapter(private val recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<RecyclerViewHolder>() {
    private val oldList = mutableListOf<ContactModel>()

    // 목록의 아이템 수
    override fun getItemCount(): Int {
        return oldList.size
    }

    // View Holder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // 연결할 레이아웃 설정
        Log.d("로그", "[RecyclerAdapter][onCreateViewHolder] Called")
        return RecyclerViewHolder(
            ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), recyclerViewInterface
        )
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Log.d("로그", "[RecyclerAdapter][onBindViewHolder] Called")
        holder.bind(oldList[position])
    }

    fun updateItem(newList: List<ContactModel>) {
        Log.d("로그", "[RecyclerAdapter][updateItem] Called")
        val diffUtil = ContactDiffCallback(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        this.oldList.clear()
        this.oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}