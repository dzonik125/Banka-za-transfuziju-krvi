package group8.bloodbank.util.validation;

import group8.bloodbank.model.User;
import group8.bloodbank.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CustomConstraintValidator implements ConstraintValidator<CustomConstraint, String> {

    UserService userService;

    @Autowired
    private CustomConstraintValidator(UserService _userService){
        this.userService = _userService;
    }
    @Override
    public void initialize(CustomConstraint string) {

    }

    @Override
    public boolean isValid(String customField, ConstraintValidatorContext ctx) {
        List<User> users = userService.getAll();
        if (customField == null) {
            return false;
        } else {
            for (User user : users) {
                if (user.getEmail() == customField)
                    return false;
            }
        }
        return true;
    }

}
