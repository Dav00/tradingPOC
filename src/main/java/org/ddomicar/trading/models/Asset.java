package org.ddomicar.trading.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String shortName;

    public Asset(String name, String shortName){
        this.name = name;
        this.shortName = shortName;
    }
}
