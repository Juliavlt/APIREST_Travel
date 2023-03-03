package ifsp.travel.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ifsp.travel.model.Image;
import ifsp.travel.model.PackageType;
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
@JsonIdentityInfo(scope = Package.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mainImage", nullable = false)
    private String mainImage;

    @OneToMany(targetEntity = Image.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval=true)
    @JoinColumn(name = "images_fk", referencedColumnName = "id")
    private List<Image> images;

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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price")
    private String price;

    @ManyToOne(targetEntity = Hotel.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "hotel_fk", referencedColumnName = "id")
    private Hotel hotel;

    @ManyToOne(targetEntity = Flight.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "flight_fk", referencedColumnName = "id")
    private Flight flight;

    @OneToMany(targetEntity = PackageType.class, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "packageType_fk", referencedColumnName = "id")
    private List<PackageType> packageType;

}
