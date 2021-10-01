package by.patsei.springproject1.dto;


import by.patsei.springproject1.validator.CellPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPersonDto {

    private Long personId;
    @NotNull(message="{valid.name.notNull}")
    @Size(min=3, message="{valid.firstname.size.min3}")
    private String firstName;
    @NotBlank(message="{valid.lastname.notBlank}")
    private String lastName;
    @NotBlank(message="{valid.street.notBlank}")
    private String street;
    @NotBlank(message="{valid.city.notBlank}")
    private String city;
    @Digits(integer=6, fraction=0, message="{valid.zip.digits}")
    private String zip;
    @NotBlank(message="{valid.email.notBlank}")
    @Email (message = "{valid.email.email}")
    private String email;

  //  @Pattern(regexp="^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$",message="Must be formatted DD/MM/YYYY")
  //  private String birthday;

    //ISO 8601 date format (yyyy-MM-dd)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "{valid.birthday.past}")
    private Date birthday;


    @CellPhone (message = "{valid.phone.cellphone}")
    private String phone;


}

