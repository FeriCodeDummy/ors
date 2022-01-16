package com.zajc.orsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class FileListsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_lists);

        RecyclerView recyclerView = findViewById(R.id.recyclers_view);
        TextView noFilesText = findViewById(R.id.nofiles_textview);

        String path = getIntent().getStringExtra("path");
        //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        try {
            ArrayList<String> expressions = getIntent().getStringArrayListExtra("expressions");
            Intent intent = new Intent(getApplicationContext(), ConvertNumericalSystemsActivity.class);
            Toast.makeText(getApplicationContext(), expressions.size(), Toast.LENGTH_SHORT);
            intent.putStringArrayListExtra("expressions", expressions);
            intent.putExtra("auto_on", true);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }

        File root = new File(path);
        File[] filesAndFolders = root.listFiles();

        if(filesAndFolders==null || filesAndFolders.length ==0){

            noFilesText.setVisibility(View.VISIBLE);
            return;
        }

        noFilesText.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MysAdapter(getApplicationContext(),filesAndFolders));

    }
}