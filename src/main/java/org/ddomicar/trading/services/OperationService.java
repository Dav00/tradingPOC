package org.ddomicar.trading.services;

import lombok.extern.slf4j.Slf4j;
import org.ddomicar.trading.models.Asset;
import org.ddomicar.trading.models.Dto.OperationRequestDto;
import org.ddomicar.trading.models.Operation;
import org.ddomicar.trading.repositories.AssetRepository;
import org.ddomicar.trading.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public  OperationService(OperationRepository operationRepository, AssetRepository assetRepository){
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
        operations.add(new Operation(137.87, 3, assetRepository.findById(1L).orElse(null)));
        operations.add(new Operation(135.20, 1, assetRepository.findById(2L).orElse(null)));
        operationRepository.saveAll(operations);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Object saveOperation(OperationRequestDto operationRequestDto) {
        return operationRepository.save(operationDtoToOperation(operationRequestDto));
    }

    private Operation operationDtoToOperation(OperationRequestDto operationRequestDto) {
        var resultado = new Operation();
        resultado.setAsset(assetRepository.findById(operationRequestDto.getAssetId()).orElse(null));
        resultado.setPrice(operationRequestDto.getPrice());
        resultado.setQuantity(operationRequestDto.getQuantity());
        resultado.setTimeStamp(new Date());
        log.info("Operation to be created: "+ resultado);
        return resultado;
    }
}
