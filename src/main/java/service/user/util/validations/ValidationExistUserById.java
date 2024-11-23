package service.user.util.validations;

import Interface.utils.validations.IExistRegister;
import database.ConnectionJDBC;
import model.DTO.userDTO.FindUserOnlyByIdDTO;
import service.user.query.FindUserByIdHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationExistUserById implements IExistRegister<FindUserOnlyByIdDTO> {
      private static final  String SELECT_COUNT = "SELECT COUNT(*) FROM people WHERE cedula=?";
    @Override
    public boolean exist(FindUserOnlyByIdDTO dto, Connection cx) throws SQLException {

        boolean ok = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.prepareStatement(SELECT_COUNT);
            ps.setString(1, dto.idUser());
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) ok = true;
            }
        }finally {
            if(ps != null && rs != null) {
                ConnectionJDBC.closeConecction(ps);
                ConnectionJDBC.closeConecction(rs);
            }
        }
        return ok;

    }
}