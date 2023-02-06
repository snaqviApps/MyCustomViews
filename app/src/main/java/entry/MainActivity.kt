package entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import ghar.learn.mycustomviews.GithubUserProfileFragment
import ghar.learn.mycustomviews.R
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.databinding.ActivityMainBinding
import ghar.learn.mycustomviews.databinding.CustomviewfirstBinding
import ghar.learn.mycustomviews.model.GithubPojo
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModel
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModelFactory

class MainActivity : AppCompatActivity() {

    private val _TAG: String by lazy { localClassName }
    private lateinit var repository: BackEndRepository         // for later considerations
    private lateinit var viewBasedBinding: ActivityMainBinding
    private lateinit var viewBasedBindingCustomView: CustomviewfirstBinding
    private lateinit var viewModel: BasicRetrofitCallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container_view_main, GithubUserProfileFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        viewBasedBinding = ActivityMainBinding.inflate(layoutInflater)
        viewBasedBindingCustomView = CustomviewfirstBinding.inflate(layoutInflater)
        setContentView(viewBasedBinding.root)
        viewModel = ViewModelProvider(this,
            BasicRetrofitCallViewModelFactory(repository = BackEndRepository()))[BasicRetrofitCallViewModel::class.java]
        viewBasedBindingCustomView.customXMLViewModel = viewModel
//        viewBasedBinding = ViewTreeLifecycleOwner

        viewBasedBinding.idMainCustomView.customMemberButton.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener {
                Log.i(_TAG, "Hi button using viewBinding")
                Toast.makeText(this, "Hi from custom-button", Toast.LENGTH_SHORT).show()
                viewModel.uiDataProvider.observe(this) { buttonGithubAccess: GithubPojo? ->
                    buttonGithubAccess?.let {
                        Toast.makeText(
                            this,
                            "gitHub call response list size: ${buttonGithubAccess}",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("main", "Hi button using githubResponse : ${buttonGithubAccess}")
                    } ?: run {
                        Toast.makeText(
                            this,
                            "gitHub call response list size is zero: ${buttonGithubAccess}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.i(_TAG, "Hi button using githubResponse : ${buttonGithubAccess ?: ""}")
                    }
                }
            }
        }
//        displayFragment()
    }

    private fun displayGithubResponseInToast() {

    }
}