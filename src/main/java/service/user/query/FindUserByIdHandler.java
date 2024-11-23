package service.user.query;

import Interface.query.IFindById;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.ShowUserDTO;
import model.DTO.userDTO.UserEmailAndIdUserDTO;
import service.user.util.validations.ExistUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUserByIdHandler implements IFindById<User> {

    private static final String SELECT_QUERY = "select * from people where cedula = ?";
    private Connection connection;
    private final ExistUserHandler existUserHandler = new ExistUserHandler();

    public FindUserByIdHandler(Connection connection) {
        this.connection = connection;
    }

    public FindUserByIdHandler() {
    }

    @Override
    public User findById(User userId) throws SQLException {


        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connection != null ? connection : ConnectionJDBC.getConnection();
            boolean ok = existUserHandler.exist(new UserEmailAndIdUserDTO(userId.getIdUser(), userId.getEmail()), con);
            if (!ok) {
                JOptionPane.showMessageDialog(null, "Find User");
                return null;
            }
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
                String pass = rs.getString("password");
                ShowUserDTO showUserDTO = new ShowUserDTO(id, name, lastname, email, career, idUser, points, pass);
                user = new User(showUserDTO);
            }

        } finally {
            if (rs != null && ps != null) {
                ConnectionJDBC.closeConecction(ps);
                ConnectionJDBC.closeConecction(rs);
            }
        }


        return user;
    }
}
