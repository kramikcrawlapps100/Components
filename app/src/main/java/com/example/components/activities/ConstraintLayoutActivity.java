package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

import com.example.components.R;

public class ConstraintLayoutActivity extends AppCompatActivity {

    ConstraintLayout layout;
    private final ConstraintSet constraintSetOld = new ConstraintSet();
    private final ConstraintSet constraintSetNew = new ConstraintSet();
    private boolean altLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        layout = findViewById(R.id.layout);

        constraintSetOld.clone(layout);
        constraintSetNew.clone(this,R.layout.activity_constraint_layout_alt);
    }

    public void SwapView(View view) {
        TransitionManager.beginDelayedTransition(layout);
        if (!altLayout){
            constraintSetNew.applyTo(layout);
            altLayout = true;
        }
        else {
            constraintSetOld.applyTo(layout);
            altLayout = false;
        }
    }
}