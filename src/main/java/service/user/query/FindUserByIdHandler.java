package service.user.query;

import Interface.query.IFindById;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.ShowUserDTO;
import service.user.util.validations.ExistUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUserByIdHandler implements IFindById<User> {

    private static final String SELECT_QUERY = "select * from people where cedula = ?";
    private Connection connection;
    private ExistUserHandler existUserHandler;

    public FindUserByIdHandler(Connection connection) {
        this.connection = connection;
    }

    public FindUserByIdHandler() {
    }

    @Override
    public User findById(User userId) throws SQLException {

        Boolean ok = existUserHandler.exist(userId, connection);

        if (!ok){
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            return null;
        }
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connection != null ? connection : ConnectionJDBC.getConnection();
            ps = con.prepareCall(SELECT_QUERY);
            ps.setString(1, userId.getIdUser());
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String career = rs.getString("career");
                String idUser = rs.getString("cedula");
                int points = rs.getInt("points");
                ShowUserDTO showUserDTO = new ShowUserDTO(id, name, lastname, email, career, idUser, points);
                user = new User(showUserDTO);
            }

        } finally {
            ConnectionJDBC.closeConecction(ps);
            ConnectionJDBC.closeConecction(rs);
        }


        return user;
    }
}
