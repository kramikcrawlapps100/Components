package com.example.components.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.components.R;
import com.example.components.databinding.ActivityDialogBinding;
import com.example.components.databinding.DialogCustomBinding;
import com.example.components.dialog.CustomDialogClass;
import com.example.components.dialog.CustomProgressDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

public class DialogActivity extends AppCompatActivity {

    ActivityDialogBinding activityDialogBinding;
    Dialog dialog;
    DialogCustomBinding dialogCustomBinding;
    Animation animation;
    Transition slideTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDialogBinding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(activityDialogBinding.getRoot());
//        slideTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide_in_out);
//        activityDialogBinding.openDialog.setOnClickListener(this);

        activityDialogBinding.openDialog.setOnClickListener(v -> {

            showCustomDialog();

//            CustomDialogClass c = new CustomDialogClass(this);
//            c.show();

//            showBottomSheetDialog();

//            PopupWindow(v);
//            snackBar();

//            CustomProgressDialog customProgressDialog = new CustomProgressDialog(this);
//            customProgressDialog.setMessage("Please wait data is Processing for 3 seconds");
//            customProgressDialog.show();

//            Toast toast = Toast.makeText(DialogActivity.this, "title.getText().toString()", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP | Gravity.END,0,0);
//            toast.show();

            animateImage();

//            animationProperty();
//            TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView().getRootView(), slideTransition);
//            setContentView(R.layout.fragment_second);
        });

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView().getRootView(), slideTransition);
//        setContentView(R.layout.activity_dialog);
//    }

    private void animationProperty() {
        ValueAnimator animator = ValueAnimator.ofFloat(-300f, 1200f);

        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                activityDialogBinding.imageView1.setX(value);
            }
        });

// Start the animation
        animator.start();

    }

    private void animateImage() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
//        animation.setRepeatCount(Animation.REVERSE);
//        animation.setInterpolator(new BounceInterpolator());
//        animation.setStartOffset(5000);
        animation.setZAdjustment(Animation.ZORDER_BOTTOM);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Toast.makeText(getApplicationContext(), "Animation Started", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Toast.makeText(getApplicationContext(), "Animation Ended", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                Toast.makeText(getApplicationContext(), "Animation Repeat", Toast.LENGTH_SHORT).show();
//            }
//        });
        activityDialogBinding.imageView1.startAnimation(animation);

//        Render render = new Render(this);
//        ImageView image = (ImageView) findViewById(R.id.imageView1);
//        render.setAnimation(Rotate.OutDownRight(image));
//        render.start();
    }

    private void snackBar() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Welcome To Main Activity", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", v -> snackbar.dismiss());
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public void PopupWindow(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dialog_custom, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.TOP, 100, 300);
        View pView = popupWindow.getContentView();

        EditText title = pView.findViewById(R.id.edit_text_input);

        Button okButton = pView.findViewById(R.id.button_ok);

        assert okButton != null;
        okButton.setOnClickListener(v -> {
            assert title != null;
            Toast toast = Toast.makeText(DialogActivity.this, title.getText().toString(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.END,0,0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                toast.addCallback(new Toast.Callback() {
                    @Override
                    public void onToastShown() {
                        super.onToastShown();
                    }

                    @Override
                    public void onToastHidden() {
                        super.onToastHidden();
                    }
                });
            }
            toast.show();
            popupWindow.dismiss();
        });

        Button cancelButton = pView.findViewById(R.id.button_cancel);
        assert cancelButton != null;
        cancelButton.setOnClickListener(v ->{
            popupWindow.dismiss();
        });

        // dismiss the popup window when touched
        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_custom);

        EditText title = bottomSheetDialog.findViewById(R.id.edit_text_input);

        Button okButton = bottomSheetDialog.findViewById(R.id.button_ok);
        assert okButton != null;
        okButton.setOnClickListener(v -> {
            assert title != null;
            Toast.makeText(DialogActivity.this, title.getText().toString(), Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        Button cancelButton = bottomSheetDialog.findViewById(R.id.button_cancel);
        assert cancelButton != null;
        cancelButton.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.show();
    }

    public void showCustomDialog() {
        dialog = new Dialog(this);
        dialogCustomBinding = DialogCustomBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogCustomBinding.getRoot());


        dialogCustomBinding.buttonOk.setOnClickListener(v -> {
            Toast.makeText(DialogActivity.this, dialogCustomBinding.editTextInput.getText().toString(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialogCustomBinding.buttonCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

//    @Override
//    public void onClick(View v) {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please wait data is Processing for 3 seconds");
//        progressDialog.show();
//
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(3000);
//                    if (progressDialog.isShowing())
//                        progressDialog.dismiss();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
}