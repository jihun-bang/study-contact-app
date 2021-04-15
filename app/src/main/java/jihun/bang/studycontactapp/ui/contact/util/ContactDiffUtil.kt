package jihun.bang.studycontactapp.ui.contact.util

import androidx.recyclerview.widget.DiffUtil
import jihun.bang.studycontactapp.data.contact.ContactModel

class ContactDiffCallback(
    private val oldItems: List<ContactModel>,
    private val newItems: List<ContactModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldItems.size

    override fun getNewListSize(): Int =
        newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return (oldItem == newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}