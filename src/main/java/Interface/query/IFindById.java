package Interface.query;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFindById<T> {
    public T findById(int id) throws SQLException;
}
