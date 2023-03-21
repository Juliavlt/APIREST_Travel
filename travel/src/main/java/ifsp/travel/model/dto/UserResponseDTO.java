package ifsp.travel.model.dto;

import ifsp.travel.model.entity.Flight;
import ifsp.travel.model.entity.Hotel;
import ifsp.travel.model.entity.Message;
import ifsp.travel.model.entity.Package;
import jakarta.persistence.Column;
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
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String username;

    private String name;

    private String profileType;

    private String email;

    private String phone;

    private List<Hotel> hotels;

    private List<Flight> flights;

    private List<Package> packages;

    private List<Message> messages;

    private String error;
}

