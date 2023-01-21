package org.ddomicar.trading.models.Dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequestDto {
    Long assetId;
    double quantity;
    double price;
    @Pattern(regexp = "buy|sell",message = "Type field must follow the format 'buy' or 'sell'.")
    String type;
}
