package ifsp.travel.model.dto;

import ifsp.travel.model.AdditionalInfo;
import ifsp.travel.model.Image;
import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.entity.Hotel;
import jakarta.persistence.*;
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
public class PackageRequestDTO implements Serializable {
    private Long id;
    private String mainImage;
    private List<String> images;
    private String departureDate;
    private String returnDate;
    private String origin;
    private String destiny;
    private String title;
    private String price;
    private Long idHotel;
    private Long idFlight;
    private Boolean favored;

}
