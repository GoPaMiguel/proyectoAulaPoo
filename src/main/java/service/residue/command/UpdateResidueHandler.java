package service.residue.command;

import Interface.command.IUpdate;
import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.FindResidueDTO;
import service.residue.util.validations.ExistResidueHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateResidueHandler implements IUpdate<Residue, FindResidueDTO> {

    private static final String UPDATE = "UPDATE residues SET code=?, type=?, points=? WHERE code=?";
    private Connection connection;

    public UpdateResidueHandler(Connection connection) {
        this.connection = connection;
    }

    public UpdateResidueHandler() {
    }

    @Override
    public void Update(Residue residue, FindResidueDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connection != null ? connection : ConnectionJDBC.getConnection();
            if (!residue.getCode().equalsIgnoreCase(dto.code())) {
                ExistResidueHandler ex = new ExistResidueHandler();
                boolean ok = ex.exist(new FindResidueDTO(residue.getCode()), con);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Cannot create the residue because it already exists, " + residue.getCode());
                    return;
                }
            }
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, residue.getCode());
            ps.setString(2, residue.getType());
            ps.setInt(3, residue.getPoints());
            ps.setString(4, dto.code());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Residues updated successfully");
        } finally {
            if (ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
