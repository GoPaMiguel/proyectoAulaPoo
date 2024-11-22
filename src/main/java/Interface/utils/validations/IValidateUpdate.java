package Interface.utils.validations;

import java.sql.Connection;
import java.sql.SQLException;

public interface IValidateUpdate <T, Q>{
    boolean validateUpdate(T t, Q q, Connection cx) throws SQLException;
}
