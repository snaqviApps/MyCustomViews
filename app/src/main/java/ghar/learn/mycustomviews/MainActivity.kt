package ghar.learn.mycustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private lateinit var customButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customButton = findViewById<LinearLayout>(R.id.id_main_custmView).findViewById(R.id.cvButtonViewXML)
        customButton.let {
            it.visibility = View.VISIBLE
            it.setOnClickListener {
                Log.i("bu", "Hi button")
            }
        }

    }
}