package ifsp.travel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** Data Transfer Object (DTO) representing AppArsenal requests. */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;
}