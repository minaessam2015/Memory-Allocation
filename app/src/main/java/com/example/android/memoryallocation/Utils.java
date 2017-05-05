package com.example.android.memoryallocation;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mina essam on 04-May-17.
 */

public final class Utils {
    public static List<Hole>holes=new ArrayList<>();
    public static List<Process>processes=new ArrayList<>();
    public static List<Integer> sizes=new ArrayList<>();

    public static boolean firstFit(){

        cleanFirst();
        boolean b=false;
        for(int j=0;j<processes.size();++j){
        for (int i=0;i<holes.size();++i){
            if(!holes.get(i).isFilled()) {
                if (holes.get(i).getSize() >= processes.get(j).getSize()) {
                    holes.get(i).setProcess(processes.get(j));
                    b= true;
                    break;
                }
            }
        }
        }
        return b;
    }

    private static void cleanFirst(){
        for(int i=0;i<holes.size();++i){
            if(holes.get(i).isFilled()){
                holes.get(i).removeProcess();
            }
        }
    }
    public static boolean bestFit(){
        boolean state=false;
        cleanFirst();
        if(sizes.size()==0){
            sort(holes);
        }
        for(int i=0;i<processes.size();++i){

            for(int j=0;j<sizes.size();++j){

                if(sizes.get(j)>=processes.get(i).getSize()){
                    //holes.get(j).setProcess(processes.get(i));
                    for(int k=0;k<holes.size();++k){
                        if(holes.get(k).getSize()==sizes.get(j)){
                            holes.get(k).setProcess(processes.get(i));
                            state=true;
                        }
                    }

                    break;
                }
            }
        }

        return state;
    }

    private static void  sort(List<Hole> holes){

        int siz=holes.size();
        for(int i=0;i<siz;++i){
            sizes.add(holes.get(i).getSize());
            //Log.d("sort",""+sizes.get(i));
        }
        Collections.sort(sizes);

        for(int i=0;i<siz;++i){
            Log.d("sort",""+sizes.get(i));
        }

    }

    public static Hole checkAddressValidity(int start){
        for (int i=0;i<holes.size();++i){
            Hole hole=holes.get(i);
            if(start>=hole.getAddress()&&start<hole.getLimit()){
                return hole;
            }
        }
        return null;
    }
}
