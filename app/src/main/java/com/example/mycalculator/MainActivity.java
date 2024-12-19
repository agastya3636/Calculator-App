package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import  org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv, solutionTv;
    MaterialButton buttonc, buttonopenbracket, buttonclosebracket;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonmultiply, buttonplus, buttonminus, buttondivide;
    MaterialButton buttonac, buttondecimal, buttonequal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);


        button1 = assignId(R.id.button_1);
        button2 = assignId(R.id.button_2);
        button3 = assignId(R.id.button_3);
        button4 = assignId(R.id.button_4);
        button5 = assignId(R.id.button_5);
        button6 = assignId(R.id.button_6);
        button7 = assignId(R.id.button_7);
        button8 = assignId(R.id.button_8);
        button9 = assignId(R.id.button_9);
        button0 = assignId(R.id.button_0);
        buttonac = assignId(R.id.button_ac);
        buttonc = assignId(R.id.button_c);
        buttonopenbracket = assignId(R.id.button_open_bracket);
        buttonclosebracket = assignId(R.id.button_close_bracket);
        buttonequal = assignId(R.id.button_equal);
        buttondecimal = assignId(R.id.button_decimal);
        buttonplus = assignId(R.id.button_plus);
        buttonminus = assignId(R.id.button_minus);
        buttonmultiply = assignId(R.id.button_multiply);
        buttondivide = assignId(R.id.button_divide);
    }


    MaterialButton assignId(int id) {
        MaterialButton bt = findViewById(id);
        bt.setOnClickListener(this);
        return bt;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        if(dataToCalculate.length()==0)
            dataToCalculate="0";
        solutionTv.setText(dataToCalculate);

        String result = getResult(dataToCalculate);

        if(!result.equals("ERR"))
        {
            resultTv.setText(result);
        }
    }

    String getResult(String data) {
        try {
            if(data.length()==0)
                return "ERR";
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERR";
        } finally {
            Context.exit();
        }
    }

}
