package ifsp.travel.model.dto;

import ifsp.travel.model.AdditionalInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDTO implements Serializable {

    private Long id;

    private String name;

    private List<String> images;

    private String location;

    private Double rate;

    private String departureDate;

    private String returnDate;

    private Integer availableRooms;

    private Double dailyPrice;

    private List<AdditionalInfo> additional;
}
