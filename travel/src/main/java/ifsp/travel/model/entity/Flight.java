package ifsp.travel.model.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ifsp.travel.model.AdditionalInfo;
import ifsp.travel.model.Image;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(scope = Flight.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Flight")
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = Image.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "images_fk", referencedColumnName = "id")
    private List<Image> images;

    @Column(name = "name")
    private String name;

    @Column(name = "airline")
    private String airline;

    @CreatedDate
    @Column(name = "departureDate")
    private String departureDate;

    @CreatedDate
    @Column(name = "returnDate")
    private String returnDate;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destiny")
    private String destiny;

    @Column(name = "price")
    private Long price;

    @Column(name = "availableSeats")
    private Integer availableSeats;

    @Column(name = "classType")
    private String classType;

    @ManyToMany(targetEntity = AdditionalInfo.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "additionalInfo_fk", referencedColumnName = "id")
    private List<AdditionalInfo> additional;

}
