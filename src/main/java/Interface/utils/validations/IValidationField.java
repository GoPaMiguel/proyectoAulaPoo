package Interface.utils.validations;

import java.sql.SQLException;

public interface IValidationField<T> {
    public Boolean validate(T t) throws SQLException;
}
