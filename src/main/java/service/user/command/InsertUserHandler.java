package service.user.command;

import Interface.command.IInsert;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import model.DTO.userDTO.UserEmailAndIdUserDTO;
import service.user.util.validations.ExistUserHandler;
import service.user.util.validations.ValidationUserFieldHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserHandler implements IInsert<User> {

    private final  String INSERT = "INSERT INTO people (name, lastName, career, cedula, password, email) VALUES (?,?,?,?,?,?)";
    private Connection connection_transactional;
    ExistUserHandler existUserHandler = new ExistUserHandler();
    ValidationUserFieldHandler validationUserFieldHandler = new ValidationUserFieldHandler();

    public InsertUserHandler() {
    }

    public InsertUserHandler(Connection connection_transactional) {
        this.connection_transactional = connection_transactional;
    }


    @Override
    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connection_transactional != null ? connection_transactional : ConnectionJDBC.getConnection();
            boolean UserExists = existUserHandler.exist(new UserEmailAndIdUserDTO(user.getIdUser(), user.getEmail()), ConnectionJDBC.getConnection());
            boolean validationField = validationUserFieldHandler.validate(user);
        if (UserExists) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese id o email");
            return;
        }else if (!validationField) return;
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCareer());
            preparedStatement.setString(4, user.getIdUser());
            preparedStatement.setString(5, user.getIdUser());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
        } finally {
            if (preparedStatement != null) ConnectionJDBC.closeConecction(preparedStatement);

        }
    }
}
