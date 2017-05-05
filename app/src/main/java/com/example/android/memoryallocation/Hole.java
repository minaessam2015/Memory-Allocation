package com.example.android.memoryallocation;

/**
 * Created by mina essam on 04-May-17.
 */

public class Hole {
    private int address,size,currentID,limit;
    private static int id=0;
    private boolean filled=false;
    private Process process;

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
        filled=true;
    }
    public void removeProcess(){
        process=null;
        filled=false;
    }

    public Hole(int address, int size){
        this.address=address;
        this.size=size;
        id++;
        currentID=id;
        limit=address+(1024*size);
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return currentID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public int getLimit() {
        return limit;
    }
}
