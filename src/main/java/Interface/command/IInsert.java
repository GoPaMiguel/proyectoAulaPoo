package Interface.command;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface IInsert<T> {
    public void insert(T t) throws SQLException;
}
