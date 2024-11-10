package Interface.query;

import java.sql.SQLException;
import java.util.List;

public interface ISelectAll<T> {
    public List<T> selectAll() throws SQLException;
}
