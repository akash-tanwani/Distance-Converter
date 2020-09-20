package com.example.distanceconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView inputLable;
    private TextView outputLable;
    EditText input;
    TextView output;
    TextView history;
    RadioButton rb;
    String sb= "";
    String result= "";

    private void setUpComponents() {
        inputLable = findViewById(R.id.lable1);
        outputLable = findViewById(R.id.lable2);
        rb = findViewById(R.id.radio1);
        input = findViewById(R.id.enterValue);
        output = findViewById(R.id.ans);
        history = findViewById(R.id.textView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpComponents();
        history.setMovementMethod(new ScrollingMovementMethod());

    }


    public void radioClicked(View v) {
        switch (v.getId()) {
            case R.id.radio1:
                inputLable.setText(R.string.miles);
                outputLable.setText(R.string.kilometer);
                break;

            case R.id.radio2:
                inputLable.setText(R.string.kilometer);
                outputLable.setText(R.string.miles);
                break;
        }
    }

    public void convertButtonClicked(View view) {
        if (input.getText().toString().equals(("")) ) {
            input.setError("Required!");
        } else {
            double out = Double.parseDouble(input.getText().toString());
            if (rb.isChecked()) {
                out = out * 1.60934;
                result = Double.toString(Math.round(out * 10.0) / 10.0);
                sb= String.format("%s Mi ==> %s Km\n", input.getText().toString(), result)+sb;
            } else {
                out = out * 0.621371;
                result = Double.toString(Math.round(out * 10.0) / 10.0);
                sb= String.format("%s Km ==> %s Mi\n", input.getText().toString(), result)+sb;
            }
            output.setText(result);
            history.setText(sb);
            input.setText("");
        }
    }


    public void clearButtonClicked(View view) {
        sb="";
        history.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("output",result);
        outState.putString("History",sb);
        outState.putString("inputlable", inputLable.getText().toString());
        outState.putString("outputlable", outputLable.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        result=savedInstanceState.getString("output");
        sb=savedInstanceState.getString("History");
        String a=savedInstanceState.getString("inputlable");
        String b=savedInstanceState.getString("outputlable");
        inputLable.setText(a);
        outputLable.setText(b);
        history.setText(sb);
        output.setText(result);
    }


}