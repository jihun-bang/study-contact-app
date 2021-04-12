package jihun.bang.studycontactapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jihun.bang.studycontactapp.databinding.ActivityMainBinding
import jihun.bang.studycontactapp.di.networkModule
import jihun.bang.studycontactapp.di.viewModelModule
import jihun.bang.studycontactapp.ui.ContactViewModel
import jihun.bang.studycontactapp.ui.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
    private val viewModel: ContactViewModel by viewModel()
    private val recyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(networkModule, viewModelModule)
        }

        recyclerAdapter.submitList(viewModel.contactsLiveData.value?.contacts ?: listOf())

        // RecyclerView 설정
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
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