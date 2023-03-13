package com.example.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.components.R;

public class CustomProgressDialog extends Dialog {

    public CustomProgressDialog(Context context) {
        super(context);
        setContentView(R.layout.custom_progress_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
    }

    public void setMessage(String message) {
        TextView loadingText = findViewById(R.id.loadingText);
        loadingText.setText(message);
    }
}
