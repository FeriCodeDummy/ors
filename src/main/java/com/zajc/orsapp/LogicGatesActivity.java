package com.zajc.orsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LogicGatesActivity extends AppCompatActivity {

    // Elementi xml
    Spinner operation;
    EditText bin1, bin2;
    ImageButton convert;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_gates);


        {
            operation = findViewById(R.id.operationSpinner);
            bin1 = findViewById(R.id.firstBinEditText);
            bin2 = findViewById(R.id.secondBinEditText);
            result = findViewById(R.id.logicResultTextView);
            convert = findViewById(R.id.logicGatesConvertButton);
        }
        {
            convert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num1 = bin1.getText().toString();
                    String num2 = bin2.getText().toString();

                    if (isbin(num1) && isbin(num2)) {
                        String[] values = refomatBinaryNumber(num1, num2);
                        num1 = values[0];
                        num2 = values[1];
                        String[] sArr1 = num1.split("");
                        String[] sArr2 = num2.split("");

                        // Arr[0] = št.1
                        // Arr[1] = št2

                        StringBuilder stringBuilder = new StringBuilder();
                        switch (operation.getSelectedItem().toString()) {
                            case "OR":
                                for (int i = 0; i < sArr1.length; i++) {
                                    if (sArr1[i].equals("1") || sArr2[i].equals("1")) {
                                        stringBuilder.append("1");
                                    } else {
                                        stringBuilder.append("0");
                                    }
                                }
                                break;

                            case "AND":
                                for (int i = 0; i < sArr1.length; i++) {
                                    if (sArr1[i].equals("1") && sArr2[i].equals("1")) {
                                        stringBuilder.append("1");
                                    } else {
                                        stringBuilder.append("0");
                                    }
                                }
                                break;

                            case "NOR":
                                for (int i = 0; i < sArr1.length; i++) {
                                    if (sArr1[i].equals("1") || sArr2[i].equals("1")) {
                                        stringBuilder.append("0");
                                    } else {
                                        stringBuilder.append("1");
                                    }
                                }
                                break;

                            case "NAND":
                                for (int i = 0; i < sArr1.length; i++) {
                                    if (sArr1[i].equals("1") && sArr2[i].equals("1")) {
                                        stringBuilder.append("0");
                                    } else {
                                        stringBuilder.append("1");
                                    }
                                }
                                break;
                            case "XOR":
                                for (int i = 0; i < sArr1.length; i++) {
                                    if ((sArr1[i].equals("1") && sArr2[i].equals("0")) || (sArr1[i].equals("0") && sArr2[i].equals("1"))) {
                                        stringBuilder.append("1");
                                    } else {
                                        stringBuilder.append("0");
                                    }
                                }
                                break;


                            case "XNOR":

                                for (int i = 0; i < sArr1.length; i++) {
                                    if ((sArr1[i].equals("1") && sArr2[i].equals("0")) || (sArr1[i].equals("0") && sArr2[i].equals("1"))) {
                                        stringBuilder.append("0");
                                    } else {
                                        stringBuilder.append("1");
                                    }
                                }
                                break;

                            case "NOT":
                                for (String s : sArr1) {
                                    if (s.equals("1")) {
                                        stringBuilder.append("0");
                                    } else {
                                        stringBuilder.append("1");
                                    }
                                }
                                break;
                            default:
                                stringBuilder.append("Error somehow");
                                break;
                        }

                        result.setText(stringBuilder.toString());

                    } else {
                        result.setText("Not binary number");
                    }
                }
            });
        }
    }

    private String[] refomatBinaryNumber(String num1, String num2){ // Če je eno število daljše od drugega, tistemu ki je krajše doda na prvo mesto toliko '0' da sta enako dolga

        if (num1.split("").length < num2.split("").length){
            StringBuilder sb = new StringBuilder();
            sb.append(num1);
            for (int i = 0; i < num2.split("").length - num1.split("").length; i++){
                sb.insert(0, "0");
            }
            return new String[]{sb.toString(), num2};

        } else if (num1.split("").length > num2.split("").length){
            StringBuilder sb = new StringBuilder();
            sb.append(num1);
            for (int i = 0; i < num1.split("").length - num2.split("").length; i++){
                sb.insert(0, "0");
            }
            return new String[]{num1, sb.toString()};
        }
        return new String[]{num1, num2};
    }

    private boolean matchLength(String num1, String num2) {
        return true;
    }

    private boolean isbin(String number) { // Preveri če je število dvojiško
        try{
            Integer.parseInt(number, 2);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}