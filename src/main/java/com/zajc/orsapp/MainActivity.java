package com.zajc.orsapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView io;
    Button equals, sqrt, del, lbracket, rbracket, dot, ac, pow, sin, cos, tan, one, two,three, four, five, six, seven, eight, nine, zero, lg, ln, minus, plus, times, divided, mod, pi, fact, auto;
    ImageButton conv, logic;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(); // Zahtevaj dovoljenja za branje in dostop do vseh datotek

        {
            // Povezava z xml
            conv = findViewById(R.id.convButton);
            equals = findViewById(R.id.equalsButton);
            auto = findViewById(R.id.autoButton);
            logic = findViewById(R.id.logicButton);
            ac = findViewById(R.id.AllClearButton);
            dot = findViewById(R.id.dotButton);
            pi = findViewById(R.id.piButton);
            sqrt = findViewById(R.id.sqrtButton);
            io = findViewById(R.id.mainTextView);
            fact = findViewById(R.id.factButton);
            one = findViewById(R.id.oneButton);
            two = findViewById(R.id.twoButton);
            three = findViewById(R.id.threeButton);
            four = findViewById(R.id.fourButton);
            five = findViewById(R.id.fiveButton);
            six = findViewById(R.id.sixButton);
            seven = findViewById(R.id.sevenButton);
            eight = findViewById(R.id.eightButton);
            nine = findViewById(R.id.nineButton);
            zero = findViewById(R.id.zeroButton);
            equals = findViewById(R.id.equalsButton);
            minus = findViewById(R.id.minusButton);
            plus = findViewById(R.id.plusButton);
            times = findViewById(R.id.timesButton);
            divided = findViewById(R.id.divideButton);
            lbracket = findViewById(R.id.leftBracketButton);
            rbracket = findViewById(R.id.rightBracketButton);
            del = findViewById(R.id.delButton);
            mod = findViewById(R.id.moduloButton);
            cos = findViewById(R.id.cosButton);
            sin = findViewById(R.id.sinButton);
            tan = findViewById(R.id.tanButton);
            pow = findViewById(R.id.powButton);
            lg = findViewById(R.id.lgButton);
            ln = findViewById(R.id.lnButton);
        }
        try{

                //Toast.makeText(getApplicationContext(), "This should work", Toast.LENGTH_SHORT).show();
                StringBuilder sb = new StringBuilder();
                ArrayList<String> expressions = getIntent().getStringArrayListExtra("expressions");
                for (String expression : expressions){
                    sb.append(expression).append("=").append(calculate(Expressionize(expression))).append("\n");
                }
                io.setText(sb.toString());

        }
        catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(), "Yeah didn't work did it", Toast.LENGTH_SHORT).show();}

        }


        {
            equals.setOnClickListener(view -> {
                // Ko je pritinsnjen '=' izra??unaj ra??un
                io.setText(calculate(Expressionize(io.getText().toString())));
            });

            // Za??eni file manager za izbiro datoteke (FileListActivity)
            auto.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.this, FileListActivity.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",path);
                    startActivity(intent);
            });

            // Dodaj '%' ra??unu
            mod.setOnClickListener(view -> io.setText(io.getText().toString() + "mod("));

            lg.setOnClickListener(view -> io.setText(io.getText().toString() + "log("));

            ln.setOnClickListener(view -> io.setText(io.getText().toString() + "ln("));
            // Dodaj 'pi' ra??unu
            pi.setOnClickListener(view -> io.setText(io.getText().toString() + "\uD835\uDF45"));


            // Dodaj 'sin(' ra??unu
            sin.setOnClickListener(view -> io.setText(io.getText().toString() + "sin("));

            //Dodaj 'cos(' ra??unu
            cos.setOnClickListener(view -> io.setText(io.getText().toString() + "cos("));

            //Dodaj '^' ra??unu
            pow.setOnClickListener(view -> io.setText(io.getText().toString() + "^"));


            // Dodaj 'tan(' ra??unu
            tan.setOnClickListener(view -> io.setText(io.getText().toString() + "tan("));

            // Odpri okno z ??tevilskimi vrati, LogicGatesActivity
            logic.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), LogicGatesActivity.class));
                finish();
            });

            // Odpri okno za pretvorbo ??tevil ConvertNumericalSystemsActivity
            conv.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), ConvertNumericalSystemsActivity.class));
                finish();
            });

            // Dodaj '.' ra??unu
            dot.setOnClickListener(view -> io.setText(io.getText().toString() + "."));


            // Dodaj fakulteto ra??unu
            fact.setOnClickListener(view -> io.setText(io.getText().toString() + "!"));

            // Zbri??i vse kar je na ekranu
            ac.setOnClickListener(view -> io.setText(""));

            // Zbri??i en element na ekranu
            del.setOnClickListener(view -> {
                StringBuilder sb= new StringBuilder(io.getText().toString());
                if (sb.length() > 0){
                    sb.deleteCharAt(sb.length()-1);
                    io.setText(sb.toString());
                }
            });


            // Dodaj koren ra??unu
            sqrt.setOnClickListener(view -> io.setText(io.getText().toString() + "???("));


            // Dodaj '0' ra??unu
            zero.setOnClickListener(view -> io.setText(io.getText().toString() + "0"));

            // Dodaj '1' ra??unu
            one.setOnClickListener(view -> io.setText(io.getText().toString() + "1"));

            // Dodaj '2' ra??unu
            two.setOnClickListener(view -> io.setText(io.getText().toString() + "2"));

            // Dodaj '3' ra??unu
            three.setOnClickListener(view -> io.setText(io.getText().toString() + "3"));

            // Dodaj '4' ra??unu
            four.setOnClickListener(view -> io.setText(io.getText().toString() + "4"));


            // Dodaj '5' ra??unu
            five.setOnClickListener(view -> io.setText(io.getText().toString() + "5"));


            // Dodaj '6' ra??unu
            six.setOnClickListener(view -> io.setText(io.getText().toString() + "6"));


            // Dodaj '7' ra??unu
            seven.setOnClickListener(view -> io.setText(io.getText().toString() + "7"));


            // Dodaj '8' ra??unu
            eight.setOnClickListener(view -> io.setText(io.getText().toString() + "8"));


            // Dodaj '9' ra??unu
            nine.setOnClickListener(view -> io.setText(io.getText().toString() + "9"));


            // Dodaj '-' ra??unu
            minus.setOnClickListener(view -> io.setText(io.getText().toString() + "-"));

            // Dodaj '+' ra??unu
            plus.setOnClickListener(view -> io.setText(io.getText().toString() + "+"));

            // Dodaj '*' ra??unu
            times.setOnClickListener(view -> io.setText(io.getText().toString() + "*"));

            // Dodaj '/' ra??unu
            divided.setOnClickListener(view -> io.setText(io.getText().toString() + "/"));

            // Dodaj '(' ra??unu
            lbracket.setOnClickListener(view -> io.setText(io.getText().toString() + "("));

            // Dodaj ')' ra??unu
            rbracket.setOnClickListener(view -> io.setText(io.getText().toString() + ")"));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkPermission(){

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }

        if(!Environment.isExternalStorageManager()){
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", this.getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private String Expressionize(String expression) {

        expression = expression.replace("???(", "root(2,").replace("\uD835\uDF45", "pi");

        return expression;

    }

    public static String calculate(String expression){
        Expression exp = new Expression(expression);
        return String.valueOf(exp.calculate());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String @NotNull [] permissions, int @NotNull [] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}


