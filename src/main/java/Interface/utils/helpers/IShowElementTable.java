package Interface.utils.helpers;

import javax.swing.*;
import java.util.List;

public interface IShowElementTable<T> {
    void showTable(JTable table, List<T> list);
}
