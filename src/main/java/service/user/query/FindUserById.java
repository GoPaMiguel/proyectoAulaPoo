package service.user.query;

import Interface.query.IFindById;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.ShowUserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUserById implements IFindById<User> {

    private static final String SELECT_QUERY = "select * from people where id = ?";
    private Connection connection;

    public FindUserById(Connection connection) {
        this.connection = connection;
    }

    public FindUserById() {
    }

    @Override
    public User findById(int id) throws SQLException {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connection != null ? connection : ConnectionJDBC.getConnection();
            ps = con.prepareCall(SELECT_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
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
