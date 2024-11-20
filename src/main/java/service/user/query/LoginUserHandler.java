package service.user.query;

import Interface.query.ILogin;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.LoginUserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserHandler implements ILogin<User> {

    private static final String SELECT_USER = "SELECT * FROM people WHERE cedula = ?";
    private Connection conn;

    public LoginUserHandler(Connection conn) {
        this.conn = conn;
    }

    public LoginUserHandler() {
    }

    @Override
    public User login(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            con = conn != null ? conn : ConnectionJDBC.getConnection();
            ps = con.prepareStatement(SELECT_USER);
            ps.setString(1, user.getIdUser());
            rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String idUser = rs.getString("cedula");
                u = new User(new LoginUserDTO(idUser, password));
            }
        } finally {
            if (rs != null && ps != null) {
                ConnectionJDBC.closeConecction(ps);
                ConnectionJDBC.closeConecction(rs);
            }
        }
        return u;
    }
}
