package com.example.model;

public abstract class MasterData<T> {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MasterData toObj(String[] row) {
        return null;
    }

}
