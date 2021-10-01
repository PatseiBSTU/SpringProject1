package by.patsei.springproject1.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public  class PhoneValidator implements ConstraintValidator<CellPhone,String> {

    @Override
    public void initialize(CellPhone paramA) {
    }
    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
        if(phoneNo == null){
            return false;
        }
        //задание номера телефона в формате  "123456789"
        if (phoneNo.matches("\\d{9}")) return true;
            //номер телефона может разделяться -, . или пробелом
        else if(phoneNo.matches("\\d{2}[-\\.\\s]\\d{3}[-\\.\\s]\\d{2}[-\\.\\s]\\d{2}")) return true;
            //может быть код оператора в скобках ()
        else if(phoneNo.matches("\\(\\d{2}\\)\\d{3}\\d{2}\\d{2}")) return true;
            //может быть код страны в скобках ()
        else if(phoneNo.matches("\\(\\d{3}\\)\\d{2}\\d{3}\\d{4}")) return true;
            //return false если ничего не подходит
        else return false;
    }

}