package jihun.bang.studycontactapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import jihun.bang.studycontactapp.databinding.ActivityLoginBinding
import jihun.bang.studycontactapp.ui.contact.ContactActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            Log.d("로그", "[LoginActivity][onCreate] btnLogin Click")
            viewModel.loginRequest(binding.txtId.text.toString(), binding.txtPassword.text.toString())
        }

        viewModel.loginRequestLiveData.observe(this) {
            if (it.complete) {
                Intent(this, ContactActivity()::class.java).let { startActivity(it) }
            } else {
                Log.d("로그", "[LoginActivity][onCreate] Failed login")
            }
        }
    }
}