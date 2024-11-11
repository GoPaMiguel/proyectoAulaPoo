package controller;

import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.CreateUserDTO;
import service.user.command.InsertUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class UserController {

    public void CreateUserController(CreateUserDTO createUserDTO){
        Connection connection = null;
        InsertUserHandler insertUserHandler = new InsertUserHandler(connection);
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
            insertUserHandler.insert(new User(createUserDTO));
            connection.commit();
            JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }finally {
            try {
                ConnectionJDBC.closeConecction(connection);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexion");
            }

        }
    }

    public void DeleteUserController(int idUser){
        Connection connection = null;
        InsertUserHandler insertUserHandler = new InsertUserHandler(connection);
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot delete user");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }finally {
                try {
                    ConnectionJDBC.closeConecction(connection);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar conexion");
                }
            }
        }
    }
}
