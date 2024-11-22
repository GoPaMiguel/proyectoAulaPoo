package Interface.command;

import java.sql.SQLException;

public interface IUpdate<T, Q> {
    public void Update(T t, Q q) throws SQLException;
}
