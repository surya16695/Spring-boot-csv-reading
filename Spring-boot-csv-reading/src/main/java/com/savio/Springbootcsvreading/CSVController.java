package com.savio.Springbootcsvreading;

import java.util.List;
import java.util.Optional;

import com.savio.Springbootcsvreading.Model.Order;
import com.savio.Springbootcsvreading.Model.OrderItem;
import com.savio.Springbootcsvreading.Model.SavioExam;
import com.savio.Springbootcsvreading.Repo.ExamRepo;
import com.savio.Springbootcsvreading.Service.CSVService;
import com.savio.Springbootcsvreading.Service.OrderItemService;
import com.savio.Springbootcsvreading.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

  @Autowired
  CSVService fileService;

  @Autowired
  ExamRepo examRepo;

  @Autowired
  OrderService orderService;

  @Autowired
  OrderItemService orderItemService;

  @PostMapping("/upload")
  public ResponseEntity<CSVResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/csv/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new CSVResponseMessage(message,fileDownloadUri));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CSVResponseMessage(message,""));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CSVResponseMessage(message,""));
  }

  @GetMapping("/getMedicineDetails")
  public ResponseEntity<CSVResponseMessage>getAllDetails(@RequestParam String id){
    try {
        Optional<SavioExam> savioExam = examRepo.findById(id);
        String msg = savioExam.toString();
      if(!msg.isEmpty()){
        String message = "This is the medicine you are searching for";
        return ResponseEntity.status(HttpStatus.OK).body(new CSVResponseMessage(message,msg));
      }
      else{
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CSVResponseMessage("",""));
      }
    } catch (Exception e) {
        String message = "Internal server issue";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CSVResponseMessage(message,""));
    }
  }

  @GetMapping("/getSearchingList")
  public ResponseEntity<CSVResponseMessage>getAllMedicines(@RequestParam String name){
    try {
        List<String> names = examRepo.findTabNames(name);
        
      if(names.size()!=0){
          String msg = "";
        for (int i = 0; i < names.size()-1; i++) {
            msg+= names.get(i)+", ";
        }
        msg+= names.get(names.size()-1);
        String message = "These are the medicines you are searching for";
        return ResponseEntity.status(HttpStatus.OK).body(new CSVResponseMessage(message,msg));
      }
      else{
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CSVResponseMessage("",""));
      }
    } catch (Exception e) {
        String message = "Internal server issue";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CSVResponseMessage(message,""));
    }
  }

  @PostMapping("/placeOrder")
  public ResponseEntity<CSVResponseMessage> placeOrder(@RequestParam List<OrderItem>orderItems){
    if(orderItems.size()!=0){
        Order order = new Order(true);  
        orderService.saveOrder(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderID(order.getId());
            orderItemService.addOrderedProducts(orderItem);
        }
        String message = "Your order has been placed";
        return ResponseEntity.status(HttpStatus.OK).body(new CSVResponseMessage(message, ""));
    }
    String message = "Please enter a non empty list to place order";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CSVResponseMessage(message, ""));
  }

  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }

}