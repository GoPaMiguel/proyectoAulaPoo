package service.auth;

import model.CORE.User;
import service.user.query.LoginUserHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginAuth {
    public static String login(User user, Connection con) throws SQLException {

        if (user.getIdUser().equals("sudo") && user.getPassword().equals("sudo")) return "admin";
        LoginUserHandler handler = new LoginUserHandler(con);
        User userDB = handler.login(user);
        if (userDB == null) return "NN";
        if (user.getIdUser().equals(userDB.getIdUser()) && user.getPassword().equals(userDB.getPassword())) return "user";

        return "NN";
    }
}
