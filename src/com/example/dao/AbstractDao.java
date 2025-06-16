package com.example.dao;

import com.example.model.MasterData;
import java.util.ArrayList;
import java.util.List;

public interface AbstractDao<T extends MasterData> {
    public void create(T object);
    public T findById(int id);
    public void update(int id, T object);
    public void delete(int id);
    public List<T> getAll();
    public T toObjects(String[] dataRow);

    default int getCSVId(){
        List<T> objectList = getAll();
        if(objectList.size()>0){
            objectList.sort((c1, c2)-> Integer.compare(c1.getId(), c2.getId()));
            return objectList.getLast().getId() + 1;}
        else{
            return 1;
        }
    }

    default int getCount() {
        int count = 0;
        for (T object : getAll()) {
            count++;
        }
        return count;
    }

    default List<T> toObjects(List<String[]> objectData) {
        List<T> objectList = new ArrayList<>();
        for (String[] dataRow : objectData) {
            T masterObj = toObjects(dataRow);
            objectList.add(masterObj);
        }
        return objectList;
    }
}
