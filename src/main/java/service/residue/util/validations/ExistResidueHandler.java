package service.residue.util.validations;

import Interface.utils.validations.IExistRegister;
import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.FindResidueDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistResidueHandler implements IExistRegister<FindResidueDTO> {

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM residue WHERE code=?";

    @Override
    public boolean exist(FindResidueDTO residue, Connection cx) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ok = false;
       try {
            ps = cx.prepareStatement(SELECT_COUNT);
           ps.setString(1, residue.code());
            rs = ps.executeQuery();
           if (rs.next()) {
               int count = rs.getInt(1);
               if (count > 0) ok = true;
           }
       }finally {
           if (ps != null && rs != null) {
               ConnectionJDBC.closeConecction(ps);
               ConnectionJDBC.closeConecction(rs);
           }
       }
        return ok;
    }
}
