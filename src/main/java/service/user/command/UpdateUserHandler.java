package service.user.command;

import Interface.command.IUpdate;
import database.ConnectionJDBC;
import model.CORE.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserHandler implements IUpdate<User> {

    private final static String UPDATE = "";
    private Connection connection_transactional;

    public UpdateUserHandler() {
    }

    public UpdateUserHandler(Connection connection_transactional) {
        this.connection_transactional = connection_transactional;
    }

    @Override
    public void Update(User user) throws SQLException {

        Connection cx = null;
        PreparedStatement ps = null;

        try {
            cx = connection_transactional != null ? connection_transactional : ConnectionJDBC.getConnection();
            ps = cx.prepareCall(UPDATE);
            ps.executeUpdate();
        }finally {
            ConnectionJDBC.closeConecction(ps);
        }

    }
}
