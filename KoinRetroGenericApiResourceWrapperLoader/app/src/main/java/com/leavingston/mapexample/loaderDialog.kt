package com.leavingston.mapexample

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout

class loaderDialog(context : Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loader_dialog
        )
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT ,
        LinearLayout.LayoutParams.MATCH_PARENT
        )

    }


}