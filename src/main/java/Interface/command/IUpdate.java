package Interface.command;

import java.sql.SQLException;

public interface IUpdate<T> {
    public void Update(T tclass) throws SQLException;
}
