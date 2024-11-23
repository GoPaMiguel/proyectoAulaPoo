package controller;

import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import service.auth.command.UpdateProfileHandler;
import service.user.command.UpdateUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ProfileController {

    private static String cedula;


    public static User  GetProfileController(){
        FindUserOnlyByIdDTO dto = new FindUserOnlyByIdDTO(cedula);
        return UserController.GetUserController(new User(dto));
    }

    public static void UpdateProfileController(User user, FindUserOnlyByIdDTO dto){
        Connection connection = null;
        try {
            connection = ConnectionJDBC.getConnection();
            connection.setAutoCommit(false);
            UpdateProfileHandler profileHandler = new UpdateProfileHandler(connection);
            profileHandler.Update(user, dto);
            connection.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot update user, "+e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    ConnectionJDBC.closeConecction(connection);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }


    public static String getCedula() {
        return cedula;
    }

    public static void setCedula(String cedula) {
        ProfileController.cedula = cedula;
    }

}
