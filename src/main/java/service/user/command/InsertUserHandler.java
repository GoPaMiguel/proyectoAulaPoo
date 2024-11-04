package service.user.command;

import Interface.command.IInsert;
import database.ConnectionJDBC;
import model.CORE.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserHandler implements IInsert<User> {

    private final static String INSERT = "INSERT INTO people (name, lastName, career, cedula, password, email) VALUES (?,?,?,?,?,?)";
    private Connection connection_transactional;

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
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCareer());
            preparedStatement.setString(4, user.getIdUser());
            preparedStatement.setString(5, user.getIdUser());
            preparedStatement.setString(6, user.getEmail());
            System.out.println("preparedStatement.toString() = " + preparedStatement.toString());
//                preparedStatement.executeUpdate();
        } finally {
            ConnectionJDBC.closeConecction(preparedStatement);
        }
    }
}
