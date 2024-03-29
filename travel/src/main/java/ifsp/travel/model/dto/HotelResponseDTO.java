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
public class HotelResponseDTO implements Serializable {

    private Long id;

    private String name;

    private List<String> images;

    private String location;

    private Integer rate;

    private String departureDate;

    private String returnDate;

    private Integer availableRooms;

    private Double dailyPrice;

    private Boolean favored;

    private List<AdditionalInfo> additional;

    private String error;

}