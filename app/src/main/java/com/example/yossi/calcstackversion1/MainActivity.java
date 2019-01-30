package com.example.yossi.calcstackversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText et;
    Button btn;
    TextView tv;

    Stack<Double> st;
    String  strStack = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        st = new Stack<>();



    }

    @Override
    public void onClick(View v) {

        String input = et.getText().toString();

        if(input.equals("+") || input.equals("-") ||input.equals("*") ||input.equals("/") )
        {
            calculate(input);
        }
        else {


            try {
                st.push(Double.valueOf(input));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


        }

        printStack();
        et.setText("");

    }

    private void calculate(String input) {

        double x,y;

        if(!st.isEmpty()) { // בדיקה שקיימים איברים במחסנית
            x = st.pop();
            if (!st.isEmpty()) {
                y = st.pop();

                // רק אם קיים גם האיבר השני לפחות במחסנית יתבצע החישוב
                if (input.equals("+")) {
                    st.push(x + y);
                } else if (input.equals("-")) {
                    st.push(x - y);
                } else if (input.equals("*")) {
                    st.push(x * y);
                } else if (input.equals("/")) {

                    if (y != 0)
                        st.push(x / y);
                    else {
                        st.push(y);
                        st.push(x);
                        Toast.makeText(this, "חלוקה באפס", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {

                st.push(x);
                Toast.makeText(this, "אין מספיק ערכים לחישוב", Toast.LENGTH_SHORT).show();
            }

            //-------


        }
    }

    private void printStack() {

        Stack<Double> tmp = new Stack<>();

        while (!st.isEmpty())
        {

            double value =  st.top();
            value =Double.parseDouble(new DecimalFormat("##.##").format(value));

            strStack += value + "\n";
            tmp.push(st.pop());

        }

        while (!tmp.isEmpty())
            st.push(tmp.pop());

        tv.setText(strStack);
        strStack = "";


    }
}
