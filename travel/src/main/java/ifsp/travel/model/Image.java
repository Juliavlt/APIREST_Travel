package ifsp.travel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ifsp.travel.model.entity.Flight;
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
@JsonIdentityInfo(scope = Image.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "image")
@Entity
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String image;
}
