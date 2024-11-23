package service.award.command;

import Interface.command.IInsert;
import database.ConnectionJDBC;
import model.CORE.Award;
import model.DTO.AwardDTO.FindAwardDTO;
import service.award.util.validations.ExistAwardHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertAwardHandler implements IInsert<Award> {
    private static final String INSERT = "INSERT INTO awards (code, name, points) VALUES (?, ?, ?);";
    private Connection connection;

    public InsertAwardHandler(Connection connection) {
        this.connection = connection;
    }

    public InsertAwardHandler() {
    }

    @Override
    public void insert(Award award) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ExistAwardHandler existAwardHandler = new ExistAwardHandler();
        boolean exist;

        try {
            conn = connection != null ? connection : ConnectionJDBC.getConnection();
            exist = existAwardHandler.exist(new FindAwardDTO(award.getCode()), conn);
            if (exist) {
                JOptionPane.showMessageDialog(null, award.getCode() + " ready exist award");
                return;
            }
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, award.getCode());
            ps.setString(2, award.getName());
            ps.setInt(3, award.getPoints());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Award Created");
        } finally {
            if (ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
