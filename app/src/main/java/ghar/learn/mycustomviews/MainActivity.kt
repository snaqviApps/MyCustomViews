package ghar.learn.mycustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ghar.learn.mycustomviews.backend.BackEndRepository
import ghar.learn.mycustomviews.databinding.ActivityMainBinding
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModel
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var repository : BackEndRepository         // for later considerations
    private lateinit var viewBasedBinding : ActivityMainBinding
    private lateinit var viewModel : BasicRetrofitCallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBasedBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(viewBasedBinding.root)

//        viewModel = ViewModelProvider(this)[BasicRetrofitCallViewModel::class.java]       // no-factory approach
        viewModel = ViewModelProvider(this, BasicRetrofitCallViewModelFactory(repository = BackEndRepository()))[BasicRetrofitCallViewModel::class.java]

        viewBasedBinding.idMainCustmView.customMemberButton.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener {
                Log.i("bu", "Hi button using viewBinding")
                Toast.makeText(this, "Hi from custom-button", Toast.LENGTH_SHORT).show()
            }
        }

    }
}