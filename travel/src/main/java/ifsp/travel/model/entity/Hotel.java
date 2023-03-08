package ifsp.travel.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ifsp.travel.model.AdditionalInfo;
import ifsp.travel.model.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(scope = Hotel.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = Image.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "images_fk", referencedColumnName = "id")
    private List<Image> images;

    @Column(name = "location")
    private String location;

    @Column(name = "rate")
    private Double rate;

    @CreatedDate
    @Column(name = "departureDate")
    private String departureDate;

    @CreatedDate
    @Column(name = "returnDate")
    private String returnDate;

    @Column(name = "dailyPrice")
    private Double dailyPrice;

    @Column(name = "availableRooms")
    private Integer availableRooms;

    @OneToMany(targetEntity = AdditionalInfo.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval=true)
    @JoinColumn(name = "additionalInfo_fk", referencedColumnName = "id")
    private List<AdditionalInfo> additional;

}
