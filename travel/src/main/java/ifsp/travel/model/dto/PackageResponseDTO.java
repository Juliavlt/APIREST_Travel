package ifsp.travel.model.dto;

import ifsp.travel.model.AdditionalInfo;
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
public class PackageResponseDTO implements Serializable {

    private long id;
    private String mainImage;
    private List<Image> images;
    private String departureDate;
    private Integer available;
    private String returnDate;
    private String origin;
    private Boolean favored;
    private String destiny;
    private String title;
    private String price;
    private Hotel hotel;
    private Flight flight;
    private String error;

}