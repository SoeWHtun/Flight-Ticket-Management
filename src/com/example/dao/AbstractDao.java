package com.example.dao;

import com.example.expection.ResourceNotFountException;
import com.example.model.MasterData;
import com.example.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public interface AbstractDao<T extends MasterData> {
    public T toObj(String[] row);
    public String getFileName();

    default int getCSVId(){
        List<T> objectList = getAll();
        if(!objectList.isEmpty()){
            objectList.sort((c1, c2)-> Integer.compare(c1.getId(), c2.getId()));
            return objectList.getLast().getId() + 1;}
        else{
            return 1;
        }
    }

    default void create(T obj) {
        obj.setId(getCSVId());
        FileUtil.csvWriter(getFileName(), obj.toArray());
    }

    default T findById(int id) {
        for (T obj : getAll()) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        throw new ResourceNotFountException("Not Fount Id at "+getFileName()+" for Id: "+ id);
    }

    default void update(int id, T updateObj) {
        findById(id);
        FileUtil.updateRecordById(getFileName(),id+"",updateObj.toArray());
    }

    default void delete(int id) {
        findById(id);
        FileUtil.deleteRecordById(getFileName(),id+"");
    }

    default List<T> getAll() {
        List<String[]> objectDatas = FileUtil.csvReader(getFileName());
        List<T> objList = toObjects(objectDatas);
        return objList;
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
            T masterObj = toObj(dataRow);
            objectList.add(masterObj);
        }
        return objectList;
    }
}
