package service.user.util.validations;

import Interface.utils.validations.IExistRegister;
import database.ConnectionJDBC;
import model.CORE.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistUserHandler implements IExistRegister<User> {

    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM user WHERE cedula=?";

    @Override
    public boolean exist(User user, Connection cx) throws SQLException {
        Boolean ok = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cx.prepareStatement(SELECT_COUNT);
            ps.setString(1, user.getIdUser());
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) ok = true;
            }
        }finally {
            ConnectionJDBC.closeConecction(ps);
            ConnectionJDBC.closeConecction(rs);
        }
        return ok;
    }
}
