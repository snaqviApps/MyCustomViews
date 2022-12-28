package ghar.learn.mycustomviews

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomViewFirst(context: Context?, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    val customMemberImgeView : ImageView
    val customMemberTextView : TextView
    val customMemberButton : Button

    init {
        inflate(context, R.layout.customviewfirst, this)
        customMemberImgeView = findViewById(R.id.cvImageViewXML)
        customMemberTextView = findViewById(R.id.cvTextViewXML)
        customMemberButton = findViewById(R.id.cvButtonViewXML)
    }

//    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet)

}