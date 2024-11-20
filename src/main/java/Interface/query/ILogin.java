package Interface.query;

import model.CORE.User;
import model.DTO.userDTO.FindUserDto;
import model.DTO.userDTO.LoginUserDTO;

import java.sql.SQLException;

public interface ILogin<T> {
    T login(T t) throws SQLException;
}
