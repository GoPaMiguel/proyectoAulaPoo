package service.user.util.validations;

import Interface.utils.validations.IExistRegister;
import database.ConnectionJDBC;
import model.CORE.User;
import model.DTO.userDTO.FindUserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistUserHandler implements IExistRegister<FindUserDto> {

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM people WHERE cedula=?";

    @Override
    public boolean exist(FindUserDto user, Connection cx) throws SQLException {
        Boolean ok = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.prepareStatement(SELECT_COUNT);
            ps.setString(1, user.idUser());
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
