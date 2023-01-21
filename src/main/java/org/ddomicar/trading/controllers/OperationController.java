package org.ddomicar.trading.controllers;

import org.ddomicar.trading.models.Dto.OperationRequestDto;
import org.ddomicar.trading.services.OperationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/hello")
    public ResponseEntity helloEndpoint(){
        return new ResponseEntity("Hello!",HttpStatusCode.valueOf(200));
    }

    @GetMapping("/all")
    public ResponseEntity getAllOperationsEndpoint(){
        try{
            return new ResponseEntity(operationService.getAllOperations() ,HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/")
    public ResponseEntity createOperationEndpoint(OperationRequestDto operationRequestDto){
        try{
            return new ResponseEntity(operationService.saveOperation(operationRequestDto), HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

}
