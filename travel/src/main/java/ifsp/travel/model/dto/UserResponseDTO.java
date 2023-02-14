package ifsp.travel.model.dto;

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

    private String error;
}

