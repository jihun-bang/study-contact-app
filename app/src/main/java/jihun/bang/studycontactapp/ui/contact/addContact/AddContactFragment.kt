package jihun.bang.studycontactapp.ui.contact.addContact

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import jihun.bang.studycontactapp.databinding.FragmentAddContactBinding
import jihun.bang.studycontactapp.ui.contact.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddContactFragment : Fragment() {
    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("로그", "[AddContactFragment][onCreate] Called")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("로그", "[AddContactFragment][onCreateView] Called")
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        val fragmentTransaction = fragmentManager?.beginTransaction()

        with(binding.btnConfirm) {
            setOnClickListener {
                Log.d("로그", "[AddContactFragment][onCreateView] btnConfirm Called")
                closeKeyboard()
                viewModel.addContact(binding.txtEmail.text.toString(), binding.txtName.text.toString())
//                fragmentTransaction?.remove(this@AddContactFragment)?.commit()
            }
        }
        with(binding.btnCancel) {
            setOnClickListener {
                Log.d("로그", "[AddContactFragment][onCreateView] btnCancel Called")
                closeKeyboard()
                fragmentTransaction?.remove(this@AddContactFragment)?.commit()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("로그", "[AddContactFragment][onDestroyView] Called")
        _binding = null
    }

    private fun closeKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}