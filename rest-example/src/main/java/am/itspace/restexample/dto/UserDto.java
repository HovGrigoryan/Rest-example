package am.itspace.restexample.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;

}
