package service.residue.query;

import Interface.query.IFindById;
import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.FindResidueDTO;
import model.DTO.ResiduoDTO.ShowResidueDTO;
import service.residue.util.validations.ExistResidueHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindResidueByCodeHandler implements IFindById<Residue> {

    private static final String SELECT_ONE = "SELECT * FROM Residues where code=?";
    private Connection con;

    public FindResidueByCodeHandler(Connection con) {
        this.con = con;
    }

    @Override
    public Residue findById(Residue id) throws SQLException {
        Residue residue = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ExistResidueHandler handler = new ExistResidueHandler();

        try {
            con = this.con != null ? con : ConnectionJDBC.getConnection();

            boolean ok = handler.exist(new FindResidueDTO(id.getCode()), con);

            if (!ok){
                JOptionPane.showMessageDialog(null, "No se encontro el residue");
                return null;
            }

            ps = con.prepareStatement(SELECT_ONE);
            ps.setString(1, id.getCode());
            rs = ps.executeQuery();
            if (rs.next()) {
                int idResidue = rs.getInt("id");
                String code = rs.getString("code");
                String type = rs.getString("type");
                int points = rs.getInt("points");
                residue = new Residue(new ShowResidueDTO(idResidue, code, type, points));
            }
        }finally {
            if(rs != null && ps != null){
                ConnectionJDBC.closeConecction(ps);
                ConnectionJDBC.closeConecction(rs);
            }
        }
        return residue;
    }
}
