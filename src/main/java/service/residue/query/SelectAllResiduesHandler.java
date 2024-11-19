package service.residue.query;

import Interface.query.ISelectAll;
import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.ShowResidueDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectAllResiduesHandler implements ISelectAll<Residue> {

    private static final String SELECT_ALL = "SELECT * FROM residue";
    Connection conexion;

    public SelectAllResiduesHandler() {
    }

    public SelectAllResiduesHandler(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Residue> selectAll() throws SQLException {
        List<Residue> residues = new ArrayList<>();
        Residue residue;
        Connection cx = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cx = conexion != null ? conexion : ConnectionJDBC.getConnection();
            ps = cx.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idResidue = rs.getInt("id");
                String code = rs.getString("code");
                String type = rs.getString("type");
                int points = rs.getInt("points");
                residue = new Residue(new ShowResidueDTO(idResidue, code, type, points));
                residues.add(residue);
            }
        }finally {
            if (rs != null && ps != null) {
                ConnectionJDBC.closeConecction(ps);
                ConnectionJDBC.closeConecction(rs);
            }
        }

        return residues;
    }
}
