package service.award.util.validations;

import Interface.utils.validations.IExistRegister;
import database.ConnectionJDBC;
import model.CORE.Award;
import model.DTO.AwardDTO.FindAwardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistAwardHandler implements IExistRegister<FindAwardDTO> {

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM awards WHERE code=?";


    @Override
    public boolean exist(FindAwardDTO award, Connection cx) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ok = false;
        try {
            ps = cx.prepareStatement(SELECT_COUNT);
            ps.setString(1, award.code());
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
