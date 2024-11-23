package service.user.util.validations;

import Interface.utils.validations.IValidationField;
import model.CORE.User;

import javax.swing.*;
import java.sql.SQLException;

public class ValidationUserFieldHandler implements IValidationField<User> {
    @Override
    public Boolean validate(User user) throws SQLException {
        boolean result = true;
        if (user != null) {
            if (user.getIdUser().isEmpty() || user.getCareer().isEmpty() || user.getEmail().isEmpty() || user.getName().isEmpty() || user.getLastName().isEmpty()) {
                JOptionPane.showMessageDialog(null, "User has fields emtpy");
                result = false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "You dont pass any user");
            result = false;
        }
        return result;
    }
}
