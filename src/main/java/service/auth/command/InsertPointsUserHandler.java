package service.auth.command;

import Interface.command.IInsertPoints;
import database.ConnectionJDBC;
import model.CORE.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPointsUserHandler implements IInsertPoints<User> {

    private static  final String UPDATE_POINTS;

    static {
        UPDATE_POINTS = "UPDATE people SET points=? WHERE cedula=?";
    }

    private Connection connection;

    public InsertPointsUserHandler(Connection connection) {
        this.connection = connection;
    }

    public InsertPointsUserHandler() {}

    @Override
    public void insertPoints(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connection != null ? connection : ConnectionJDBC.getConnection();
            ps = con.prepareStatement(UPDATE_POINTS);
            ps.setInt(1, user.getPoints());
            ps.setString(2, user.getIdUser());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Points inserted successfully");
        }finally {
            if(ps != null) ps.close();
        }
    }
}
