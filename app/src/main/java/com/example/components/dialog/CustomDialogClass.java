package com.example.components.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.components.R;

public class CustomDialogClass extends Dialog implements View.OnClickListener{

    public Activity c;
    public Button ok, cancel;
    public EditText title;

    public CustomDialogClass(@NonNull Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_custom);

        ok = findViewById(R.id.button_ok);
        cancel = findViewById(R.id.button_cancel);
        title = findViewById(R.id.edit_text_input);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                Toast.makeText(c, title.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.button_cancel:
                dismiss();
                break;
        }
        dismiss();
    }
}
