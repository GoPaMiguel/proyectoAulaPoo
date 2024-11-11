package service.user.query;

import Interface.query.ISelectAll;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.ShowUserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectAllUserHandler implements ISelectAll<User> {

    private static final String SELECT_ALL = "select * from user";
    private Connection connection;

    public SelectAllUserHandler(Connection connection) {
        this.connection = connection;
    }

    public SelectAllUserHandler() {
    }

    @Override
    public List<User> selectAll() throws SQLException {
        Connection cx = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            cx = (connection != null) ? connection : ConnectionJDBC.getConnection();
            ps = cx.prepareCall(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String career = rs.getString("career");
                String idUser = rs.getString("cedula");
                int points = rs.getInt("points");
                ShowUserDTO showUserDTO = new ShowUserDTO(id, username, lastname, email, career, idUser, points);
                users.add(new User(showUserDTO));
            }
        } finally {
            ConnectionJDBC.closeConecction(ps);
            ConnectionJDBC.closeConecction(rs);
        }
            return users;
    }
}
