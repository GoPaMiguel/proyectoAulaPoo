package service.user.command;

import Interface.command.IDelete;
import database.ConnectionJDBC;
import model.CORE.User;
import service.user.util.validations.ExistUserHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserHandler implements IDelete<User> {

    private static final String DELETE = "DELETE FROM people WHERE id=?";
    private  Connection connection;
    private ExistUserHandler existUserHandler;

    public DeleteUserHandler() {
    }

    public DeleteUserHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Delete(User user) throws SQLException {
        Connection cx = null;
        PreparedStatement ps = null;
        Boolean ok = existUserHandler.exist(user, connection);
        if (!ok) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            return;
        }
        try {
            cx = (connection != null) ? connection : ConnectionJDBC.getConnection();
            ps = cx.prepareCall(DELETE);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        }finally {
            if(ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
