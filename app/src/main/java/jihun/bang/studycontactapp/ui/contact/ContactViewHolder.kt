package jihun.bang.studycontactapp.ui.contact

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jihun.bang.studycontactapp.data.contact.ContactModel
import jihun.bang.studycontactapp.databinding.ContactItemBinding
import jihun.bang.studycontactapp.ui.contact.util.RecyclerViewInterface

class RecyclerViewHolder(
    private val binding: ContactItemBinding,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    init {
        itemView.setOnClickListener(this)
    }

    // 데이터와 뷰를 묶음
    @SuppressLint("SetTextI18n")
    fun bind(model: ContactModel) {
//        Log.d("로그", "[RecyclerViewHolder][bind] Called Model = $model")
        binding.model = model
        binding.executePendingBindings()
    }

    override fun onClick(p0: View?) {
        this.recyclerViewInterface.onItemClicked(binding.model!!.id)
    }
}