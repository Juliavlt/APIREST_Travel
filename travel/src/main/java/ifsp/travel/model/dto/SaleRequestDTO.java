package ifsp.travel.model.dto;

import ifsp.travel.model.AdditionalInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO{

    private Long idUser;

    private Long idHotel;

    private Long idFlight;

    private Long idPackage;

    private Integer persons;

}
