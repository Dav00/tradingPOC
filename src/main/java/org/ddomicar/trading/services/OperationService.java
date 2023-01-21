package org.ddomicar.trading.services;

import lombok.extern.slf4j.Slf4j;
import org.ddomicar.trading.models.Asset;
import org.ddomicar.trading.models.Dto.OperationRequestDto;
import org.ddomicar.trading.models.Operation;
import org.ddomicar.trading.repositories.AssetRepository;
import org.ddomicar.trading.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OperationService {
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    AssetRepository assetRepository;

    public OperationService(OperationRepository operationRepository, AssetRepository assetRepository) {
        this.operationRepository = operationRepository;
        this.assetRepository = assetRepository;
        startTestData();
    }

    private void startTestData() {
        var assets = new ArrayList<Asset>();
        var operations = new ArrayList<Operation>();

        assets.add(new Asset("Apple", "AAPL"));
        assets.add(new Asset("Microsoft", "MSFT"));
        assetRepository.saveAll(assets);
        operations.add(new Operation(137.87, 3, assetRepository.findById(1L).orElse(null), "buy"));
        operations.add(new Operation(135.20, 1, assetRepository.findById(2L).orElse(null), "buy") );
        operationRepository.saveAll(operations);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public List<Operation> getBuyOperations(){
        return operationRepository.findByType("buy");
    }

    public List<Operation> getSellOperations(){
        return operationRepository.findByType("sell");
    }

    public Object saveOperation(OperationRequestDto operationRequestDto) {
        return operationRepository.save(operationDtoToOperation(operationRequestDto));
    }

    private Operation operationDtoToOperation(OperationRequestDto operationRequestDto) {
        var result = new Operation();
        result.setAsset(assetRepository.findById(operationRequestDto.getAssetId()).orElse(null));
        result.setPrice(operationRequestDto.getPrice());
        result.setQuantity(operationRequestDto.getQuantity());
        result.setTimeStamp(new Date());
        result.setType(operationRequestDto.getType());
        log.info("Operation to be created: " + result);
        return result;
    }

    public Operation getOperationsById(Long id) {
        return operationRepository.findById(id).orElse(null);
    }
}
