package Interface.query;

import java.util.ArrayList;

public interface IFindById<T> {
    public ArrayList<T> findById(int id);
}
