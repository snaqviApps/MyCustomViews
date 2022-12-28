package ghar.learn.mycustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import ghar.learn.mycustomviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBasedBinding : ActivityMainBinding
    private lateinit var customButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBasedBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(viewBasedBinding.root)

//        customButton = findViewById<LinearLayout>(R.id.id_main_custmView).findViewById(R.id.cvButtonViewXML)
//        customButton.let {
//            it.visibility = View.VISIBLE
//            it.setOnClickListener {
//                Log.i("bu", "Hi button")
//            }
//        }

        viewBasedBinding.idMainCustmView.customMemberButton?.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener {
                Log.i("bu", "Hi button using viewBinding")
            }
        }

    }
}