package com.example.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static final String filePath = "/Users/minmaunghein/Flight-Ticket-Management";

    public static void csvCreater(String fileName) {
        File file = new File(filePath + "/" + fileName);

        if (file.exists()) {
            System.out.println("CSV file already exists: " + file.getAbsolutePath());
            return;
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"Id", "Name", "Phone", "Email"};
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

}
