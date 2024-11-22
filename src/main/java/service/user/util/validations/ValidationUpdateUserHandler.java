package service.user.util.validations;

import Interface.utils.validations.IValidateUpdate;
import model.CORE.User;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import service.user.query.FindUserByIdHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationUpdateUserHandler implements IValidateUpdate<User, FindUserOnlyByIdDTO> {
    @Override
    public boolean validateUpdate(User user, FindUserOnlyByIdDTO dto, Connection cx) throws SQLException {

        FindUserByIdHandler handler = new FindUserByIdHandler();
        User userDB =  handler.findById(new User(dto));
        String email = userDB.getEmail();
        String iduser = userDB.getIdUser();
        String SQL = "";
        if (userDB != null) {
            if (email.equals(userDB.getEmail())) {
                SQL = "SELECT COUNT(*) FROM people WHERE cedula=" + iduser;
            } else if (iduser.equals(userDB.getIdUser())) {

                SQL = "SELECT COUNT(*) FROM people WHERE email=" + email + ";";
            }
            PreparedStatement ps = cx.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            return false;
        }

        return true;
    }
}
