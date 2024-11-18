package service.award.command;

import Interface.command.IDelete;
import database.ConnectionJDBC;
import model.CORE.Award;
import model.DTO.AwardDTO.FindAwardDTO;
import service.award.util.validations.ExistAwardHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAwardHandler implements IDelete<Award> {

    private static final String DELETE = "DELETE FROM awards WHERE code=?";
    private Connection connection;

    public DeleteAwardHandler(Connection connection) {
        this.connection = connection;
    }

    public DeleteAwardHandler() {}

    @Override
    public void Delete(Award award) throws SQLException {
        ExistAwardHandler existAwardHandler = new ExistAwardHandler();
        Connection conn = null;
        PreparedStatement ps = null;
        boolean exist;

        try {
            conn = connection != null ? connection : ConnectionJDBC.getConnection();
            exist = existAwardHandler.exist(new FindAwardDTO(award.getCode()), conn);
            if (!exist) {
                JOptionPane.showMessageDialog(null, award.getCode() + " no existe en la base de datos");
                return;
            }
            ps = conn.prepareStatement(DELETE);
            ps.setString(1, award.getCode());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Award Eliminado Correctamente");
        }finally {
            if(ps != null) ConnectionJDBC.closeConecction(ps);
        }


    }
}
