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
    public static final String filePath = "C:\\Users\\User\\Downloads\\Flight-Ticket-Management";

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
//    public static void csvUpdater(String fileName, String[] data) {
//        try {
//            List<String[]> allRows = FileUtil.csvReader(fileName);
//            String targetId = data[0];
//            boolean updated = false;
//
//            try (FileWriter writer = new FileWriter(fileName, false)) {
//                for (String[] row : allRows) {
//                    if (row[0].equals(targetId)) {
//                        writer.append(String.join(",", data)).append("\n");
//                        updated = true;
//                    } else {
//                        writer.append(String.join(",", row)).append("\n");
//                    }
//                }
//            }
//
//            if (updated) {
//                System.out.println("Row updated successfully!");
//            } else {
//                System.out.println("No row found with ID: " + targetId);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public static void csvDeleter(String fileName, String[] data) {
//        List<String[]> allRows = FileUtil.csvReader(fileName);
//        boolean deleted = false;
//        String targetId = data[0];
//        try (FileWriter writer = new FileWriter(fileName, false)) {
//            for (String[] row : allRows) {
//                if (row[0].equals(targetId)) {
//                    deleted = true; // Skip writing this row
//                    continue;
//                }
//                writer.append(String.join(",", row)).append("\n");
//            }
//            if (deleted) {
//                System.out.println("Row deleted successfully!");
//            } else {
//                System.out.println("Row not found for deletion.");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }


}
