package org.ddomicar.trading.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.ddomicar.trading.services.OperationService;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @GeneratedValue
    @Id
    Long id;
    Date timeStamp;
    double quantity;
    double price;
    @ManyToOne
    Asset asset;

    public Operation(double quantity, double price, Asset asset){
        this.timeStamp = new Date();
        this.quantity = quantity;
        this.price = price;
        this.asset = asset;
    }

    double getTotalPrice() {
        var formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(quantity * price));
    }
}
