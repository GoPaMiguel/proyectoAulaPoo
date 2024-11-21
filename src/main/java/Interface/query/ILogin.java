package Interface.query;

import java.sql.SQLException;

public interface ILogin<T> {
    T login(T t) throws SQLException;
}
