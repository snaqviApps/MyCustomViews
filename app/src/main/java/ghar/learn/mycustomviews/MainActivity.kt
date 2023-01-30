package ghar.learn.mycustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.databinding.ActivityMainBinding
import ghar.learn.mycustomviews.databinding.CustomviewfirstBinding
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModel
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModelFactory

class MainActivity : AppCompatActivity() {

    private val _TAG: String by lazy { localClassName }
    private lateinit var repository : BackEndRepository         // for later considerations
    private lateinit var viewBasedBinding : ActivityMainBinding
    private lateinit var viewBasedBindingCustomView : CustomviewfirstBinding
    private lateinit var viewModel : BasicRetrofitCallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBasedBinding = ActivityMainBinding.inflate(layoutInflater)
        viewBasedBindingCustomView = CustomviewfirstBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)
        setContentView(viewBasedBinding.root)

//        viewModel = ViewModelProvider(this)[BasicRetrofitCallViewModel::class.java]       // no-factory approach
        viewModel = ViewModelProvider(this, BasicRetrofitCallViewModelFactory(repository = BackEndRepository()))[BasicRetrofitCallViewModel::class.java]
        viewBasedBindingCustomView.customXMLViewModel = viewModel
//        viewBasedBinding = ViewTreeLifecycleOwner

        viewBasedBinding.idMainCustmView.customMemberButton.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener {
                Log.i(_TAG, "Hi button using viewBinding")
                Toast.makeText(this, "Hi from custom-button", Toast.LENGTH_SHORT).show()
                viewModel.uiDataProvider.observe(this) { buttonGithubAccess ->
                    buttonGithubAccess?.let {
                        Toast.makeText(this, "gitHub call response list size: ${buttonGithubAccess.size}", Toast.LENGTH_LONG).show()
                        Log.i("main", "Hi button using githubResponse : ${buttonGithubAccess.size}")
                    } ?:run {
                        Toast.makeText(this, "gitHub call response list size is zero: ${buttonGithubAccess?.size}", Toast.LENGTH_SHORT).show()
                        Log.i(_TAG, "Hi button using githubResponse : ${buttonGithubAccess?.size.toString() ?: ""}")
                    }
                }
            }
        }
//        displayGithubResponseInToast()
    }

    private fun displayGithubResponseInToast() {
//        viewModel.uiDataProvider.observe(this, Observer{
        this.viewModel.uiDataProvider.observe(this) {
            it?.let {
                Toast.makeText(this, "gitHub call response list size: ${it.size}", Toast.LENGTH_SHORT).show()
                Log.i(_TAG, "Hi button using githubResponse : ${it.size}")

            } ?: run {
                Toast.makeText(this, "gitHub call response came with No results", Toast.LENGTH_SHORT).show()
            }

        }
    }
}