package controller;

import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.CreateUserDTO;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import model.DTO.userDTO.LoginUserDTO;
import service.auth.LoginAuth;
import service.user.command.DeleteUserHandler;
import service.user.command.InsertUserHandler;
import service.user.query.FindUserByIdHandler;
import service.user.query.SelectAllUserHandler;
import service.user.util.helpers.SelectUserTableHandler;
import service.user.util.helpers.ShowUserAndCreateTableHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserController {

    public static void CreateUserController(CreateUserDTO createUserDTO) {
        Connection connection = null;
        try {
            connection = ConnectionJDBC.getConnection();
            InsertUserHandler insertUserHandler = new InsertUserHandler(connection);
            connection.setAutoCommit(false);
            insertUserHandler.insert(new User(createUserDTO));
            connection.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot create the user because:" + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot make rollback" + ex.getMessage());
            }
        }finally {
            try {
                ConnectionJDBC.closeConecction(connection);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexion" + e.getMessage());
            }

        }
    }

    public static void DeleteUserController(FindUserOnlyByIdDTO userCode) {
        Connection connection = null;
        DeleteUserHandler deleteUserHandler = new DeleteUserHandler(connection);
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
            deleteUserHandler.Delete(new User(userCode));
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

    public static User GetUserController(User idUser) {
        Connection connection = null;
        User user = null;
        try {
            connection = ConnectionJDBC.getConnection();
            FindUserByIdHandler findUserById = new FindUserByIdHandler(connection);
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

    public static List<User> GetAllUserController() {
        Connection connection = null;
        List<User> users = null;

        try {
            connection = ConnectionJDBC.getConnection();
        SelectAllUserHandler selectAllUserHandler = new SelectAllUserHandler(connection);
            connection.setAutoCommit(false);
            users = selectAllUserHandler.selectAll();
            connection.commit();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se logro traer todos los usuarios" + e.getMessage());
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

    public static void ShowUserController(JTable table) {
        List<User> users = GetAllUserController();
        ShowUserAndCreateTableHandler showUserTableHandler = new ShowUserAndCreateTableHandler();
        showUserTableHandler.showTable(table, users);
    }

    public static String LoginController(LoginUserDTO dto) {

        Connection connection = null;
        User user = new User(dto);
        String login = null;
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
            login = LoginAuth.login(user, connection);
            connection.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar los usuarios, " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al hacer el rollback, " + ex.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    ConnectionJDBC.closeConecction(connection);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexion, " + e.getMessage());
                }
            }
        }
        return login;

    }

    public static User SelectUserController(JTable table) {
        SelectUserTableHandler selectUserTableHandler = new SelectUserTableHandler();
        User user = selectUserTableHandler.selectElement(table);
        return user;
    }
}
