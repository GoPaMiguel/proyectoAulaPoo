package Interface.utils.helpers;

import javax.swing.*;
import java.util.List;

public interface IShowElementAndCreateTable<T> {
    void showTable(JTable table, List<T> list);
}
