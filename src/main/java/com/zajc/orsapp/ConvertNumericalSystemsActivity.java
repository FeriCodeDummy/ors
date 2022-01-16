package com.zajc.orsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ConvertNumericalSystemsActivity extends AppCompatActivity {

    // Xml elementi definirani v R.layout.activity_convert_numerical_systems.xml
    ImageButton convert, calc,auto;
    Spinner to, from;
    EditText input;
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_numerical_systems);
        // poveži xml s kodo
        {
            calc = findViewById(R.id.calcButton);
            convert = findViewById(R.id.convertButton);
            to = findViewById(R.id.toSpinner);
            from = findViewById(R.id.fromSpinner);
            input = findViewById(R.id.inputEditText);
            output = findViewById(R.id.resultTextView);
            auto = findViewById(R.id.autoConvertButton);

        }
        try{
            // Dobi informacije iz konteksta aplikacije, ki se nastavi ko se auto calculate zažene.
            ArrayList<String> expressions = getIntent().getStringArrayListExtra("expressions"); // Pridobi račune
            ArrayList<String []> converted = new ArrayList<>();

            for (String expression : expressions){ // Pretvori račune v array
                String[] arr = expression.split(" ");
                converted.add(arr);
            }


            output.setText(autoCalculate(converted)); // Izpiši računa na ekran

        }
        catch (Exception e) {
            // Ker se kliče že na začetku bo vrgel najprej NullPointerException ki ga ignoriramo
            e.printStackTrace();
        }

        convert.setOnClickListener(view -> {
            // Funkcija se izvede ko pritisnemo gumb 'convert'
            String expression = input.getText().toString(); // Pridobimo vnos in ga pretvorimo v niz
            if (correctNumberFormat(expression, from.getSelectedItem().toString())){ // Kličemo funkcijo ki preveri, če je številski sistem in input pravilen
                String result = convertSystem(from.getSelectedItem().toString(), expression, to.getSelectedItem().toString()); // pretvori število arg2 iz arg1 v arg3
                output.setText(result); // Izpis
            }
            else {
                output.setText(R.string.wrong_format); // Izpis neusreznega števil
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ko pritisnemo na gumb auto odpre file explorer (aktivnost FileListsActivity)
                Intent intent = new Intent(getApplicationContext(), FileListsActivity.class);
                String path = Environment.getExternalStorageDirectory().getPath();
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });

        calc.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });

    }

    private String autoCalculate(ArrayList<String[]> sArrays) {

        StringBuilder expressions = new StringBuilder(); // Dobra praksa je, da namesto seštevanja nizov uporabimo StringBuilder.append()
        /*
        array[0] = from
        array[1] = število
        array[2] = to

        */
        for (String[] array : sArrays){
            if (correctNumberFormat(array[1], array[0])){
                String result = convertSystem(array[0], array[1], array[2]);
                expressions.append(String.format("%s:  %s -> %s = %s", array[0],array[1],array[2], result)).append("\n");
                // DEC: 12 -> HEX = C
            }else{
                expressions.append(String.format("%s is not valid format for %s\n",array[1], array[0]));
            }
        }
    return expressions.toString();
    }

    private String convertSystem(String from, String expression, String to) {

        switch (from){
            case "DEC":
                switch (to){
                    case "DEC": // Če sta from in to isti vrni število
                        return expression;

                    case "BIN":
                        return Integer.toBinaryString(Integer.valueOf(expression)); // število v desetiškem sistemu funkcija Integer.toBinaryString pretvori v niz dvojiškega sistem

                    case "HEX":
                        return Integer.toHexString(Integer.valueOf(expression)); // število v desetiškem sistemu funkcija Integer.toHexString pretvori v niz šestnajstiškega sistema

                    case "OCT":
                        return Integer.toOctalString(Integer.valueOf(expression)); // število v desetiškem sistemu funkcija Integer.toOctalString pretvori v niz osmiškega sistem

                }
            case "BIN":  // Če sta from in to isti vrni število
                switch (to){
                    case "BIN":
                        return expression;

                    case "DEC": // parseInt(niz, številskki sistem) spremeni vrednost v desetiški sistem, ki ga nato podamo v zgornje funkcije
                        return String.valueOf(Integer.parseInt(expression, 2));

                    case "HEX":
                        return Integer.toHexString(Integer.parseInt(expression, 2));

                    case "OCT":
                        return Integer.toOctalString(Integer.parseInt(expression, 2));
                }

            case "OCT":  // Če sta from in to isti vrni število
                switch (to){
                    case "OCT":
                        return expression;

                    case "DEC":
                        return String.valueOf(Integer.parseInt(expression, 8));

                    case "HEX":
                        return Integer.toHexString(Integer.parseInt(expression, 8));

                    case "BIN":
                        return Integer.toBinaryString(Integer.parseInt(expression, 8));
                }

            case "HEX":  // Če sta from in to isti vrni število
                switch (to){
                    case "HEX":
                        return expression;

                    case "DEC":
                        return String.valueOf(Integer.parseInt(expression, 16));

                    case "BIN":
                        return Integer.toBinaryString(Integer.parseInt(expression, 16));

                    case "OCT":
                        return Integer.toOctalString(Integer.parseInt(expression, 16));
                }

        default:
            // Prišlo je do neke nove napake, ki se načeloma ne bi smela pojaviti
            return "Unknown error";

        }

    }


    private boolean correctNumberFormat(String expression, String system) {

        switch (system){
            case "DEC":
                try {
                    Integer.valueOf(expression);
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "HEX":
                try {
                    Integer.parseInt(expression, 16);
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "BIN":
                try{
                    Integer.parseInt(expression, 2);
                    return true;
                } catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            case "OCT":
                try{
                    Integer.parseInt(expression, 8);
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
        }

        return true;

    }
}