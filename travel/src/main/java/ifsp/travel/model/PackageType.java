package ifsp.travel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(scope = PackageType.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "_packageType")
@Entity
public class PackageType {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private Double value;
}
