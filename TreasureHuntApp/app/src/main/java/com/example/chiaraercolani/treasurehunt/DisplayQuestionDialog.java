package com.example.chiaraercolani.treasurehunt;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Dialog used to display a question whileplayong a hunt
 */
public class DisplayQuestionDialog extends DialogFragment {

    TextView textView;
    LinearLayout view;

    AlertDialog.Builder builder;

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    JoinedHuntStartActivity.MyDialogCloseListener closeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = (LinearLayout) inflater.inflate(R.layout.question_dialog, null);
        builder.setView(view);

        textView = (TextView)view.findViewById(R.id.question_asked);

        return builder.create();
    }


    /**
     * Associate each answer to a button and set the text inside the button according to what
     * @param step
     */
    public void setStep(Step step){
        textView.setText(step.getQuestion());

        button1 = (Button)view.findViewById(R.id.question_dialog_button_1);
        button2 = (Button)view.findViewById(R.id.question_dialog_button_2);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);

        //use random number to mix the order of the possible answers
        Random random = new Random(System.currentTimeMillis());

        if (step.getWrongAnswer3().isEmpty()) {
            if(step.getWrongAnswer2().isEmpty()){
                //only to answers possible
                if (random.nextInt(2) == 0) {
                    button1.setText(step.getGoodAnswer());
                    button2.setText(step.getWrongAnswer1());
                }else{
                    button2.setText(step.getGoodAnswer());
                    button1.setText(step.getWrongAnswer1());
                }
            }else {
                //3 answers possible
                button3 = new Button(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                view.addView(button3, lp);
                switch (random.nextInt(3)) {
                    case  0:
                        button1.setText(step.getGoodAnswer());
                        button2.setText(step.getWrongAnswer1());
                        button3.setText(step.getWrongAnswer2());
                        break;
                    case 1 :
                        button2.setText(step.getGoodAnswer());
                        button3.setText(step.getWrongAnswer1());
                        button1.setText(step.getWrongAnswer2());
                        break;
                    case 2 :
                        button3.setText(step.getGoodAnswer());
                        button1.setText(step.getWrongAnswer1());
                        button2.setText(step.getWrongAnswer2());
                        break;
                }
            }
        }else{
            //4 answerspossible
            button3 = new Button(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.addView(button3, lp);
            button4 = new Button(getActivity());
            view.addView(button4, lp);
            switch (random.nextInt(4)) {
                case  0:
                    button1.setText(step.getGoodAnswer());
                    button2.setText(step.getWrongAnswer1());
                    button3.setText(step.getWrongAnswer2());
                    button4.setText(step.getWrongAnswer3());
                    break;
                case 1 :
                    button2.setText(step.getGoodAnswer());
                    button3.setText(step.getWrongAnswer1());
                    button1.setText(step.getWrongAnswer3());
                    button4.setText(step.getWrongAnswer2());
                    break;
                case 2 :
                    button3.setText(step.getGoodAnswer());
                    button1.setText(step.getWrongAnswer2());
                    button2.setText(step.getWrongAnswer3());
                    button4.setText(step.getWrongAnswer1());
                    break;
                case 3 :
                    button4.setText(step.getGoodAnswer());
                    button1.setText(step.getWrongAnswer2());
                    button2.setText(step.getWrongAnswer3());
                    button3.setText(step.getWrongAnswer1());
                    break;
            }
        }

    }

    /**
     * Define listeners for the user to answer
     * check i the user answer correctly
     * @param step
     */
    public void getAnswer (final Step step){
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (button1.getText()==step.getGoodAnswer()){
                    getDialog().dismiss();

                }else{
                    Toast.makeText(v.getContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (button2.getText()==step.getGoodAnswer()){
                    getDialog().dismiss();
                }else{
                    Toast.makeText(v.getContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(button3!=null) {
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (button3.getText() == step.getGoodAnswer()) {
                        getDialog().dismiss();
                    } else {
                        Toast.makeText(v.getContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(button4!=null) {
            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (button4.getText() == step.getGoodAnswer()) {
                        getDialog().dismiss();
                    } else {
                        Toast.makeText(v.getContext(), "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void DismissListner(JoinedHuntStartActivity.MyDialogCloseListener closeListener){
        this.closeListener = closeListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(closeListener!=null){
            closeListener.handleDialogClose(null);
        }

    }


}
