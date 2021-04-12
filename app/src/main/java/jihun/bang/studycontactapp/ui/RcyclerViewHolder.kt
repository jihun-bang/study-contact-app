package jihun.bang.studycontactapp.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jihun.bang.studycontactapp.R
import jihun.bang.studycontactapp.data.ContactModel

class RecyclerViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val txtNameId: TextView = itemView.findViewById(R.id.txtNameId)
    private val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    init {
        itemView.setOnClickListener(this)
    }

    // 데이터와 뷰를 묶음
    @SuppressLint("SetTextI18n")
    fun bind(model: ContactModel) {
        Log.d("로그", "[RecyclerViewHolder][bind] Called Model = $model")
        txtNameId.text = "${model.name}(${model.id})"
        txtEmail.text = model.email
    }

    override fun onClick(p0: View?) {
        Log.d("로그", "[RecyclerViewHolder][onClick] position = $absoluteAdapterPosition")
    }
}