package service.residue.command;

import Interface.command.IInsert;
import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.FindResidueDTO;
import service.residue.util.validations.ExistResidueHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertResidueHandler implements IInsert<Residue> {

    private static final String INSERT;

    static {
        INSERT = "INSERT INTO residues (code, type, points)  VALUES(?,?,?);";
    }

    private Connection connection;

    @Override
    public void insert(Residue residue) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ExistResidueHandler existHandler = new ExistResidueHandler();
        boolean ok;

        try {
            conn = connection != null ? connection : ConnectionJDBC.getConnection();
            ok = existHandler.exist(new FindResidueDTO(residue.getCode()), conn);
            if (ok){
                JOptionPane.showMessageDialog(null, "Cannot register the residue because the code already exists");
                return;
            }
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, residue.getCode());
            ps.setString(2, residue.getType());
            ps.setInt(3, residue.getPoints());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Residue inserted successfully");
        }finally {
            if(ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
