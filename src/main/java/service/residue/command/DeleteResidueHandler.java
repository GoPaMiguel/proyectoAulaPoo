package service.residue.command;

import Interface.command.IDelete;
import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.FindResidueDTO;
import service.residue.util.validations.ExistResidueHandler;
import service.user.util.validations.ExistUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteResidueHandler implements IDelete<Residue> {

    private static final String DELETE = "DELETE FROM residue WHERE code=?";
    private Connection connection;

    public DeleteResidueHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Delete(Residue id) throws SQLException {
        Connection cx = null;
        PreparedStatement ps = null;
        ExistResidueHandler existResidue = new ExistResidueHandler();

        try {
            cx = connection != null ? connection : ConnectionJDBC.getConnection();
            boolean ok = existResidue.exist(new FindResidueDTO(id.getCode()), cx);
            if (!ok) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el residue, porque no existe");
                return;
            }
            ps = cx.prepareStatement(DELETE);
            ps.setString(1, id.getCode());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "delete residue successfully");
        }finally {
            if(ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
