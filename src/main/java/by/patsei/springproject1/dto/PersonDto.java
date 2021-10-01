package by.patsei.springproject1.dto;

import by.patsei.springproject1.validator.CellPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

         private Long personId;
        private String firstName;
        private String lastName;
        private String street;
        private String city;
        private String zip;
        private String email;
        private Date birthday;
        private String phone;

}
