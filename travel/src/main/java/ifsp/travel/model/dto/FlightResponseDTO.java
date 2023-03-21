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
public class FlightResponseDTO implements Serializable {

    private List<String> images;

    private Long id;

    private String name;

    private String departureDate;

    private Boolean favored;

    private String returnDate;

    private String origin;

    private String destiny;

    private Long price;

    private Integer availableSeats;

    private String classType;

    private String airline;

    private List<AdditionalInfo> additional;

    private String error;

}