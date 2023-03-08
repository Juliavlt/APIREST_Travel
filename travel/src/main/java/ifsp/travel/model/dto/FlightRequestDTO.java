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
public class FlightRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String departureDate;

    private String returnDate;

    private String origin;

    private String destiny;

    private Long price;

    private Integer availableSeats;

    private String classType;

    private String airline;

    private List<String> images;

    private List<AdditionalInfo> additional;
}
