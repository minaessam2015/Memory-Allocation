package com.example.android.memoryallocation;

/**
 * Created by mina essam on 04-May-17.
 */

public class Process {
    private int size;
    private static int id=0;
    private int currentID;
    public Process(int size){
        this.size=size;
        id++;
        currentID=id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }
}
