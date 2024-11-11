package controller;

import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.CreateUserDTO;
import service.user.command.DeleteUserHandler;
import service.user.command.InsertUserHandler;
import service.user.query.FindUserByIdHandler;
import service.user.query.SelectAllUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public void DeleteUserController(User user) {
        Connection connection = null;
        DeleteUserHandler deleteUserHandler = new DeleteUserHandler(connection);
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
            deleteUserHandler.Delete(user);
            connection.commit();
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
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

    public User GetUserController(User idUser) {
        Connection connection = null;
        FindUserByIdHandler findUserById = new FindUserByIdHandler();
        User user = null;
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
             user = findUserById.findById(idUser);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al hacer el rollback");
            }
        } finally {

            try {
                ConnectionJDBC.closeConecction(connection);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexion");
            }
        }
        return user;
    }

    public List<User> GetAllUserController() {
        Connection connection = null;
        SelectAllUserHandler selectAllUserHandler = new SelectAllUserHandler(connection);
        List<User> users = null;

        try{
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
            users = selectAllUserHandler.selectAll();
            connection.commit();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se logro traer todos los usuarios");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al hacer el rollback");
            }finally {
                try {
                    ConnectionJDBC.closeConecction(connection);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexion");
                }
            }
        }
        return users;
    }
}
