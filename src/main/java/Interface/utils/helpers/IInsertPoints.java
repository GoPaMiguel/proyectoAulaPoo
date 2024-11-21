package Interface.utils.helpers;

import java.sql.SQLException;

public interface IInsertPoints<T> {
    void insertPoints(T t) throws SQLException;
}
