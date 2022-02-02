package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Calculator calculator = new Calculator();
    TextView result;

    Button but_one;
    Button but_two;
    Button but_three;
    Button but_four;
    Button but_five;
    Button but_six;
    Button but_seven;
    Button but_eight;
    Button but_nine;
    Button but_zero;
    Button but_add;
    Button but_subtract;
    Button but_divide;
    Button but_time;
    Button but_clear;

    //advanced buttons
    Button but_modulo;
    Button but_max;
    Button but_min;
    Button but_power;

    LinearLayout advance;
    boolean advanceState;

    //this mod button change the calculator between standard and scientific
    Button mod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);

        but_one = findViewById(R.id.one);
        but_one.setOnClickListener(this);
        but_two = findViewById(R.id.two);
        but_two.setOnClickListener(this);
        but_three = findViewById(R.id.three);
        but_three.setOnClickListener(this);
        but_four = findViewById(R.id.four);
        but_four.setOnClickListener(this);
        but_five = findViewById(R.id.five);
        but_five.setOnClickListener(this);
        but_six = findViewById(R.id.six);
        but_six.setOnClickListener(this);
        but_seven = findViewById(R.id.seven);
        but_seven.setOnClickListener(this);
        but_eight = findViewById(R.id.eight);
        but_eight.setOnClickListener(this);
        but_nine = findViewById(R.id.nine);
        but_nine.setOnClickListener(this);
        but_zero = findViewById(R.id.zero);
        but_zero.setOnClickListener(this);
        but_add = findViewById(R.id.add);
        but_add.setOnClickListener(this);
        but_subtract = findViewById(R.id.subtract);
        but_subtract.setOnClickListener(this);
        but_time = findViewById(R.id.time);
        but_time.setOnClickListener(this);
        but_divide = findViewById(R.id.divide);
        but_divide.setOnClickListener(this);
        but_clear = findViewById(R.id.clear);
        but_clear.setOnClickListener(this);

        //advanced buttons
        but_modulo = findViewById(R.id.modulo);
        but_modulo.setOnClickListener(this);
        but_min = findViewById(R.id.min);
        but_min.setOnClickListener(this);
        but_power = findViewById(R.id.pow);
        but_power.setOnClickListener(this);
        but_max = findViewById(R.id.max);
        but_max.setOnClickListener(this);

        mod = findViewById(R.id.advance);
        //set the advance version to invisible at the onCreate
        advance = findViewById(R.id.advancePlatform);
        advanceState = false;
        advance.setVisibility(View.GONE);

    }


    //Handle the equal sign (=)
    public void equalOnClicked(View view) {

        String temp = (String) result.getText();
        //convert the text into proper format
        temp = temp.replace("Pow","^");
        temp = temp.replace("Max",">");
        temp = temp.replace("Min","<");


        calculator.push(temp);
        calculator.calculate();

        String str;
        if (calculator.getResult() == Calculator.ERROR) {
            str = formatExpression(result.getText()) + " = Not an Operator";
        }else {
            str = formatExpression(result.getText()) + " = " + calculator.getResult();
        }
        result.setText(str);
        //ready for next calculation
        calculator.setStateToTrue();
    }

    public String formatExpression(CharSequence text){
        StringBuilder temp = new StringBuilder();
        for (int i = 0 ; i < text.length() ; i++){
            temp.append(" ").append(text.charAt(i));
        }
        return temp.toString();
    }

    @Override
    public void onClick(View view) {
       // int id = view.getId();
        String tempStr = ((Button)view).getText().toString();
        if (calculator.getState()) {
            result.setText("");
            calculator.clear();
        }

        //into this calculation
        calculator.setStateToFalse();
        if(tempStr.equals("C")){
            result.setText("");
            calculator.clear();
            //ready for calculation
            calculator.setStateToTrue();
        }else{
            String tem = result.getText().toString() + tempStr;
            tem = tem.replace("POW","Pow");
            tem = tem.replace("MAX","Max");
            tem = tem.replace("MIN","Min");
            result.setText(tem);
        }



    }


    //show the advance module
    public void showAdvance(View view){
        if(!advanceState){
            mod.setBackgroundColor(Color.rgb(114,91,103));
            //mod.setBackground(getResources().getDrawable(R.drawable.custom_button_two));
            mod.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.custom_button_two,null));
            mod.setText(getString(R.string.standard));

            //show the advanced buttons
            advance.setVisibility(View.VISIBLE);
            advanceState = true;
        }else{
            mod.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.custom_button,null));
            mod.setText(R.string.advance);

            //hide the advanced buttons
            advance.setVisibility(View.GONE);
            advanceState = false;
        }
    }
}