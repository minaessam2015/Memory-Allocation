package com.example.android.memoryallocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddProcess extends AppCompatActivity {
EditText size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_process);
        size=(EditText)findViewById(R.id.size);
    }
    public void add(View view ){
        String siz=size.getText().toString();
        if(siz.length()==0){
            Toast.makeText(this,"Enter the size",Toast.LENGTH_LONG).show();
            return;
        }
        Utils.processes.add(new Process(Integer.valueOf(siz)));
        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();

    }
    public void done(View view){
        finish();
    }
}
