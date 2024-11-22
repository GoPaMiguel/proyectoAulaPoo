package service.user.command;

import Interface.command.IDelete;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import model.DTO.userDTO.UserEmailAndIdUserDTO;
import service.user.util.validations.ExistUserHandler;
import service.user.util.validations.ValidationExistUserById;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserHandler implements IDelete<User> {

    private static final String DELETE = "DELETE FROM people WHERE cedula=?";
    private  Connection connection;
    private ValidationExistUserById validation = new ValidationExistUserById();

    public DeleteUserHandler() {
    }

    public DeleteUserHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Delete(User user) throws SQLException {
        Connection cx = null;
        PreparedStatement ps = null;
        try {
            cx = (connection != null) ? connection : ConnectionJDBC.getConnection();
            boolean ok = validation.exist(new FindUserOnlyByIdDTO(user.getIdUser()), cx);
            if (!ok) {
                JOptionPane.showMessageDialog(null, "can't delete, IdUser: " + user.getIdUser() + " does not exist");
                return;
            }
            ps = cx.prepareCall(DELETE);
            ps.setString(1, user.getIdUser());
            ps.executeUpdate();
        }finally {
            if(ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
