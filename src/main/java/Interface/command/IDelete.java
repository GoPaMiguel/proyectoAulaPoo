package Interface.command;

import java.sql.SQLException;

public interface IDelete<I> {
    public void Delete(I id) throws SQLException;
}
