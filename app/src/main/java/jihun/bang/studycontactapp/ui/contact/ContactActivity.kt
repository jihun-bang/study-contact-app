package jihun.bang.studycontactapp.ui.contact

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jihun.bang.studycontactapp.databinding.ActivityContactBinding
import jihun.bang.studycontactapp.ui.contact.util.RecyclerViewInterface
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ContactActivity : AppCompatActivity(), RecyclerViewInterface {
    private val binding by lazy {
        ActivityContactBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
    private val viewModel: ContactViewModel by viewModel()
    private val recyclerAdapter by lazy { ContactRecyclerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("로그", "[MainActivity][onCreate] Called")
        // RecyclerView 설정
        binding.recyclerView.apply {
            Log.d("로그", "[MainActivity][onCreate] binding.recyclerView.apply Called")
            layoutManager =
                LinearLayoutManager(this@ContactActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }

        with(binding.swipeRefreshLayout) {
            setOnRefreshListener {
                viewModel.getContacts()
            }
        }
        with(binding.btnAdd) {
            setOnClickListener {
                viewModel.addContact(UUID.randomUUID().toString().substring(0, 5), UUID.randomUUID().toString().substring(0, 5) + "@test.com")
                binding.recyclerView.scrollToPosition(0)
                /*
                // Fragment Popup
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<AddContactFragment>(R.id.fragment_add_contact)
                }
                 */
            }
        }
        with(viewModel) {
            getContacts()
            createContactLiveData.observe(this@ContactActivity) {
                Log.d("로그", "[ContactActivity][onCreate] createContactLiveData.observe")
                Toast.makeText(this@ContactActivity, "Add ${it.contact.name}", Toast.LENGTH_SHORT).show()
            }

            contactsLiveData.observe(this@ContactActivity) {
                Log.d("로그", "[ContactActivity][onCreate] contactsLiveData.observe")
                recyclerAdapter.updateItem(it.contacts)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onPause() {
        super.onPause()

        Log.d("로그", "[MainActivity][onPause] Called")
    }

    override fun onItemClicked(id: Long) {
        Log.d("로그", "[MainActivity][onItemClicked] id = $id")
        viewModel.deleteContact(id)
    }
}