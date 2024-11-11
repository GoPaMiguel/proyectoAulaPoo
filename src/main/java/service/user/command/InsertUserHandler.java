package service.user.command;

import Interface.command.IInsert;
import database.ConnectionJDBC;
import model.CORE.User;
import service.user.util.validations.ExistUserHandler;
import service.user.util.validations.ValidationUserFieldHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserHandler implements IInsert<User> {

    private final static String INSERT = "INSERT INTO people (name, lastName, career, cedula, password, email) VALUES (?,?,?,?,?,?)";
    private Connection connection_transactional;
    ExistUserHandler existUserHandler;
    ValidationUserFieldHandler validationUserFieldHandler;

    public InsertUserHandler() {
    }

    public InsertUserHandler(Connection connection_transactional) {
        this.connection_transactional = connection_transactional;
    }


    @Override
    public void insert(User user) throws SQLException {
        Connection connection = null;
        boolean UserExists = existUserHandler.exist(user, connection);
        boolean validationField = validationUserFieldHandler.validate(user);
        if (UserExists) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese id");
            return;
        }else if (!validationField) return;
        PreparedStatement preparedStatement = null;
        try {
            connection = connection_transactional != null ? connection_transactional : ConnectionJDBC.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCareer());
            preparedStatement.setString(4, user.getIdUser());
            preparedStatement.setString(5, user.getIdUser());
            preparedStatement.setString(6, user.getEmail());
            System.out.println("preparedStatement.toString() = " + preparedStatement.toString());
            preparedStatement.executeUpdate();
        } finally {
            ConnectionJDBC.closeConecction(preparedStatement);
        }
    }
}
