package ifsp.travel.model.dto;

import ifsp.travel.model.Image;
import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.entity.Hotel;
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
public class SaleResponseDTO implements Serializable {

    private long id;
    private String error;
    private String response;

}