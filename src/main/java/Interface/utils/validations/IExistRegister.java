package Interface.utils.validations;

import java.sql.Connection;
import java.sql.SQLException;

public interface IExistRegister<T> {
    public boolean exist(T t, Connection cx) throws SQLException;
}
