package ifsp.travel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponseDTO implements Serializable {

    /*private List<String> images;*/

    private Long id;

    private String name;

    private String departureDate;

    private String returnDate;

    private String origin;

    private String destiny;

    private Long price;

    private Integer vacancies;

    private String classType;

    private String airline;

    /*private List<AdditionalInfo> additional;*/

    private String error;

}