package com.qourier.qourier_app.data.dto;

import com.qourier.qourier_app.data.Rider;
import lombok.Data;

@Data
public class RiderDTO {

    private AccountDTO account;
    private String citizenId;
    private Long currentDelivery;

    public static RiderDTO fromEntity(Rider rider) {
        RiderDTO dto = new RiderDTO();
        dto.setAccount(AccountDTO.fromEntity(rider.getAccount()));
        dto.setCitizenId(rider.getCitizenId());
        dto.setCurrentDelivery(rider.getCurrentDelivery());
        return dto;
    }
}
