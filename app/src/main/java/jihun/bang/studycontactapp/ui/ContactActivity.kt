package jihun.bang.studycontactapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jihun.bang.studycontactapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
    private val viewModel: ContactViewModel by viewModel()
    private val recyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerAdapter.submitList(viewModel.contactsLiveData.value?.contacts ?: listOf())

        // RecyclerView 설정
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@ContactActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }

        binding.btnRefresh.setOnClickListener {
            Log.d("로그", "[MainActivity][onCreate] btnRefresh Click")
            viewModel.getContacts()
        }

        viewModel.contactsLiveData.observe(this) {
            recyclerAdapter.updateItem(it)
        }
    }
}