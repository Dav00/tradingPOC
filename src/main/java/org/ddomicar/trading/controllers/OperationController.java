package org.ddomicar.trading.controllers;

import jakarta.validation.Valid;
import org.ddomicar.trading.models.Dto.OperationRequestDto;
import org.ddomicar.trading.models.Operation;
import org.ddomicar.trading.services.OperationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloEndpoint(){
        return new ResponseEntity<String>("Hello!",HttpStatusCode.valueOf(200));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Operation>> getAllOperationsEndpoint(){
            return new ResponseEntity<List<Operation>>(operationService.getAllOperations() ,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/buy/all")
    public ResponseEntity<List<Operation>> getAllBuyOperationsEndpoint(){
            return new ResponseEntity<List<Operation>>(operationService.getBuyOperations() ,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/sell/all")
    public ResponseEntity<List<Operation>> getAllSellOperationsEndpoint(){
            return new ResponseEntity<List<Operation>>(operationService.getSellOperations() ,HttpStatusCode.valueOf(200));
    }
    @PostMapping("/")
    public ResponseEntity<Operation> createOperationEndpoint(@Valid OperationRequestDto operationRequestDto){
            return new ResponseEntity<Operation>((Operation) operationService.saveOperation(operationRequestDto), HttpStatusCode.valueOf(200));
    }

}
