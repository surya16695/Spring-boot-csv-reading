package com.savio.Springbootcsvreading;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.savio.Springbootcsvreading.Model.SavioExam;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "c_name", "c_batch_no", "d_expiry_Date", "n_balance_qty", "c_packaging", "c_unique_code", "c_schemes", "n_mrp", "c_manufacturer", "hsn_code" };

  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<SavioExam> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<SavioExam> savioExamList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  SavioExam savioExam = new SavioExam(
              csvRecord.get("c_unique_code"),
              csvRecord.get("c_name"),
              csvRecord.get("c_batch_no"),
              csvRecord.get("d_expiry_Date"),
              Integer.parseInt(csvRecord.get("n_balance_qty")),
              csvRecord.get("c_packaging"),
              csvRecord.get("c_schemes"),
              Float.parseFloat(csvRecord.get("n_mrp")),
              csvRecord.get("c_manufacturer"),
              Integer.parseInt(csvRecord.get("hsn_code"))
            );

    	  savioExamList.add(savioExam);
      }

      return savioExamList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<SavioExam> SavioExamList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (SavioExam savioExam : SavioExamList) {
        List<String> data = Arrays.asList(
              savioExam.getId(),
              savioExam.getNameOfTab(),
              savioExam.getBatchNum(),
              savioExam.getDateOfExpiry(),
              String.valueOf(savioExam.getBalanceOfTabs()),
              savioExam.getSizeOfPackage(),
              savioExam.getSchemes(),
              String.valueOf(savioExam.getPriceOfMedicine()),
              savioExam.getManufacturedBy(),
              String.valueOf(savioExam.getHsnNum())
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}
