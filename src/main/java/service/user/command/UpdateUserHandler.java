package service.user.command;

import Interface.command.IUpdate;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import service.user.util.validations.ValidationUpdateUserHandler;
import service.user.util.validations.ValidationUserFieldHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserHandler implements IUpdate<User, FindUserOnlyByIdDTO> {

    private final static String UPDATE = "UPDATE  people SET name=?, lastname=?, email=?, career=?, cedula=? where cedula=?";
    private Connection connection_transactional;

    public UpdateUserHandler() {
    }

    public UpdateUserHandler(Connection connection_transactional) {
        this.connection_transactional = connection_transactional;
    }

    @Override
    public void Update(User user, FindUserOnlyByIdDTO dto) throws SQLException {

        Connection cx = null;
        PreparedStatement ps = null;
        ValidationUpdateUserHandler validationUpdateUserHandler = new ValidationUpdateUserHandler();
        ValidationUserFieldHandler validationUserFieldHandler = new ValidationUserFieldHandler();
        try {
            cx = connection_transactional != null ? connection_transactional : ConnectionJDBC.getConnection();
            boolean validationFields = validationUserFieldHandler.validate(user);
            boolean exists = validationUpdateUserHandler.validateUpdate(user, dto, cx);
            if (!exists) {
                JOptionPane.showMessageDialog(null, "Cannot update user, because it does exist");
                return;
            }
            if (!validationFields) {
                return;
            }
            ps = cx.prepareCall(UPDATE);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getCareer());
            ps.setString(5, user.getIdUser());
            ps.setString(6, dto.idUser());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "User Update successful");
        }finally {
            if(ps != null) ps.close();
        }

    }
}
