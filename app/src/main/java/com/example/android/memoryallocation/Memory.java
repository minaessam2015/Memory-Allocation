package com.example.android.memoryallocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Memory extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addProcess;
    HomeAdapter adapter;
    boolean firstFit=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        recyclerView=(RecyclerView)findViewById(R.id.memory_recycler);
        addProcess=(Button)findViewById(R.id.add_process);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        adapter=new HomeAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position=(int)viewHolder.itemView.getTag();
                Hole hole=Utils.holes.get(position);
                if(hole.isFilled()){
                    Utils.holes.get(position).removeProcess();
                }
                else {Toast.makeText(getApplicationContext(),"You cannot remove a hole",Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_firs_fir:
                if(Utils.processes.size()==0){
                    Toast.makeText(this,"Add processes first",Toast.LENGTH_LONG).show();
                    return true;
                }
                firstFit=true;
                if(Utils.firstFit()){
                adapter.notifyDataSetChanged();
                Utils.processes.clear();
                }
                else Toast.makeText(this,"Could not add Process",Toast.LENGTH_LONG).show();
                return true;
            case  R.id.action_best_fit:
                if(Utils.processes.size()==0){
                    Toast.makeText(this,"Add processes first",Toast.LENGTH_LONG).show();
                    return true;
                }
                firstFit=false;
                if(Utils.bestFit()){
                    adapter.notifyDataSetChanged();
                    Utils.processes.clear();
                }
                else {
                    Toast.makeText(this,"Could not add Process",Toast.LENGTH_LONG).show();
                }
                return true;
            default: return true;
        }

    }

    public void addProcess(View view){
        Utils.processes.clear();
        startActivity(new Intent(this,AddProcess.class));
    }
}
