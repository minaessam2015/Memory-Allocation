package com.example.android.memoryallocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   // private List<Hole> holes=new ArrayList<>();
    private EditText holeSize;
    private EditText holeAddress;
    Button add;
    RecyclerView recyclerView;
    Button done;
    HomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holeSize=(EditText)findViewById(R.id.hole_size);
        holeAddress=(EditText)findViewById(R.id.hole_address);
        add=(Button)findViewById(R.id.add_hole);
        recyclerView=(RecyclerView)findViewById(R.id.home_recycler);
        done=(Button)findViewById(R.id.done_button);
        adapter=new HomeAdapter(this);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position=(int)viewHolder.itemView.getTag();
                adapter.removeHole(position);
            }
        }).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addHole(View view){
        String size=holeSize.getText().toString();
        if(size.length()==0){
            Toast.makeText(this,"Please enter the hole size",Toast.LENGTH_LONG).show();
            holeSize.setHint(R.string.holeSize);
            return;
        }
        String address=holeAddress.getText().toString();
        if(address.length()==0){
            Toast.makeText(this,"Please enter the address",Toast.LENGTH_LONG).show();
            holeAddress.setHint(R.string.holeAddress);
            return;
        }
        adapter.addHole(new Hole(Integer.valueOf(address),Integer.valueOf(size)));
        hideKeyboard(recyclerView);
        Log.d("addHole ","Hole Added");

    }
    public void doneEntering(View view ){
        startActivity(new Intent(this,Memory.class));
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
