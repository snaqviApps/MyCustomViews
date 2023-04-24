package entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import ghar.learn.mycustomviews.GithubUserProfileFragment
import ghar.learn.mycustomviews.R
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.databinding.ActivityMainBinding
import ghar.learn.mycustomviews.databinding.CustomviewfirstBinding
import ghar.learn.mycustomviews.model.GithubUserProfile
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModel
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewBinding: ActivityMainBinding
    private val _mainTag = this::class.simpleName
    private lateinit var repository: BackEndRepository         // for later considerations
    private lateinit var viewBasedBindingCustomView: CustomviewfirstBinding
    private lateinit var viewModel: BasicRetrofitCallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayGithubUserInfoOnFragment()                                   // fragment instantiation

        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)      // crashing when changed <fragment> tab in activity_main.xml
        setContentView(mainViewBinding.root)

//        viewBasedBindingCustomView = CustomviewfirstBinding.inflate(layoutInflater)
//        viewModel = ViewModelProvider(this, BasicRetrofitCallViewModelFactory(repository = BackEndRepository()))[BasicRetrofitCallViewModel::class.java]
//        viewBasedBindingCustomView.customXMLViewModel = viewModel
//        viewBasedBinding = ViewTreeLifecycleOwner

//        viewBasedBinding.idMainCustomView.customMemberButton.let {
//            it.visibility = View.VISIBLE
//            it.setOnClickListener {
//                Log.i(_mainTag, "Hi button using viewBinding")
//                Toast.makeText(this, "Hi from custom-button", Toast.LENGTH_SHORT).show()
//                viewModel.uiDataProvider.observe(this) { githubData: GithubUserProfile? ->
//                    githubData?.let {
//                        Toast.makeText(this, "avatarUrl: ${githubData.avatarUrl}", Toast.LENGTH_LONG).show()
////                        viewBasedBinding.idMainCustomView.customMemberTextView.text = githubData.avatarUrl
//                        Log.i(_mainTag, "Hi button using githubResponse : $githubData")
//                    } ?: run {
//                        Toast.makeText(this, "gitHub call response list size is zero: ", Toast.LENGTH_SHORT).show()
//                        Log.i(_mainTag, "Hi button using githubResponse")
//                    }
//                }
//            }
//        }
    }

    private fun displayGithubUserInfoOnFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view_main, GithubUserProfileFragment())             // works
        }
    }
}