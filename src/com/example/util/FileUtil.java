package com.example.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
//    public static final String filePath = "C:\\Users\\User\\Downloads\\Flight-Ticket-Management";

    public static final String filePath = "/Users/minmaunghein/Flight-Ticket-Management";

    public static void csvCreater(String fileName, String[] header) {
        File file = new File(filePath + "/" + fileName);

        if (file.exists()) {
            System.out.println("CSV file already exists: " + file.getAbsolutePath());
            return;
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {

            writer.writeNext(header);
            System.out.println("CSV file created successfully: " + file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void csvWriter(String fileName, String[] data){
        try(FileWriter writer = new FileWriter(fileName, true)){
            String row = String.join(",", data);
            writer.append(row).append("\n");
            System.out.println("Row Inserted successfully!!!");
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static List<String[]> csvReader(String fileName){
        List<String[]> rows = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(filePath + "/" + fileName))){
            reader.readNext();
            String[] line;
            while((line = reader.readNext()) != null){
                rows.add(line);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }


    public static void updateRecordById(String fileName, String tergetId, String[] updateRow) {
        List<String[]> allRows = List.of();
        try(CSVReader reader = new CSVReader(new FileReader(filePath +"/"+fileName))){
            allRows = reader.readAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        for(int i = 1; i< allRows.size(); i++){
            String[] row = allRows.get(i);
            if(row[0].equalsIgnoreCase(tergetId)){
                allRows.set(i,updateRow);
            }
        }

        try(CSVWriter writer = new CSVWriter(new FileWriter(filePath+"/"+fileName))){
            writer.writeAll(allRows);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.print("Updated record with ID:"+ tergetId);
    }


    public static void deleteRecordById(String fileName, String tergetId) {
        List<String[]> allRows = List.of();
        try(CSVReader reader = new CSVReader(new FileReader(filePath +"/"+fileName))){
            allRows = reader.readAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        String[] header = allRows.get(0);

        List<String[]> updatedRows = new ArrayList<>();
        updatedRows.add(header);

        for(int i = 1; i< allRows.size(); i++){
            String[] row = allRows.get(i);
            if(!row[0].equalsIgnoreCase(tergetId)){
                updatedRows.add(row);
            }
        }

       try(CSVWriter writer = new CSVWriter(new FileWriter(filePath+"/"+fileName))){
           writer.writeAll(updatedRows);
       }catch (Exception ex){
           ex.printStackTrace();
       }

       System.out.print("Deleted record with ID:"+ tergetId);

    }
}
