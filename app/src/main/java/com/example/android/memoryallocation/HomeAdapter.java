package com.example.android.memoryallocation;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.android.memoryallocation.Utils.holes;

/**
 * Created by mina essam on 04-May-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    //private List<Hole> holes=new ArrayList<>();


    public void addHole(Hole hole){
        Utils.holes.add(hole);
        notifyDataSetChanged();
    }
    public void removeHole(int position){
        Utils.holes.remove(position);
        notifyDataSetChanged();
    }

    public HomeAdapter(Context context){
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_row,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(holes.get(position).isFilled()){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent));
            holder.holeName.setText("Process "+Utils.holes.get(position).getProcess().getCurrentID());
            holder.holeAddress.setText("Starting Address  "+Utils.holes.get(position).getAddress());
            holder.holeSize.setText("Size  "+Utils.holes.get(position).getProcess().getSize());
            return;
        }
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
        holder.holeName.setText("Hole "+Utils.holes.get(position).getId());
        holder.holeAddress.setText("Starting Address  "+Utils.holes.get(position).getAddress());
        holder.holeSize.setText("Size  "+Utils.holes.get(position).getSize());

    }


    @Override
    public int getItemCount() {
        Log.d("getItemCount ",""+Utils.holes.size());
        return Utils.holes.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView holeName;
        public final TextView holeAddress;
        public final TextView holeSize;
        public ViewHolder(View itemView) {
            super(itemView);
            holeName=(TextView)itemView.findViewById(R.id.hole_name_row);
            holeSize=(TextView)itemView.findViewById(R.id.hole_size_row);
            holeAddress=(TextView)itemView.findViewById(R.id.hole_address_row);
        }
    }
}
