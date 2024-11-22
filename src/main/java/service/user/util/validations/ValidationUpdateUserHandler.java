package service.user.util.validations;

import Interface.utils.validations.IValidateUpdate;
import model.CORE.User;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import model.DTO.userDTO.UserEmailAndIdUserDTO;
import service.user.query.FindUserByIdHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationUpdateUserHandler implements IValidateUpdate<User, FindUserOnlyByIdDTO> {
    @Override
    public boolean validateUpdate(User user, FindUserOnlyByIdDTO dto, Connection cx) throws SQLException {


        FindUserByIdHandler handler = new FindUserByIdHandler();
        User userDB =  handler.findById(new User(dto));
        String SQL = null;
        if (user.getIdUser().equalsIgnoreCase(dto.idUser()) && user.getEmail().equals(userDB.getEmail())) {
           return true;
        } else if (user.getIdUser().equalsIgnoreCase(dto.idUser())) {
            SQL = "SELECT COUNT(*) FROM people WHERE email="+user.getEmail();
        } else if (user.getEmail().equalsIgnoreCase(userDB.getEmail())) {
            SQL = "SELECT COUNT(*) FROM people WHERE ceduala="+user.getEmail();
        } else {
            ExistUserHandler existUserHandler = new ExistUserHandler();
            return existUserHandler.exist(new UserEmailAndIdUserDTO(user.getIdUser(), user.getEmail()), cx);
        }
        PreparedStatement ps = cx.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        int count = rs.getInt(1);
        return count > 0;
    }
}
