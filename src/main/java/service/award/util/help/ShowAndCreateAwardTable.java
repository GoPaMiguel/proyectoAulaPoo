package service.award.util.help;

import Interface.utils.helpers.IShowElementAndCreateTable;
import model.CORE.Award;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class ShowAndCreateAwardTable implements IShowElementAndCreateTable<Award> {
    @Override
    public void showTable(JTable table, List<Award> list) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> alphabeticOrder = new TableRowSorter<TableModel>(model);
        table.setRowSorter(alphabeticOrder);
        model.addColumn("ID");
        model.addColumn("code");
        model.addColumn("name");
        model.addColumn("points");
        table.setModel(model);

        String[] dataList = new String[4];

        try {
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cannot get any residues");
                return;
            }
            for (Award award : list) {
                dataList[0] = String.valueOf(award.getId());
                dataList[1] = award.getCode();
                dataList[2] = award.getName();
                dataList[3] = String.valueOf(award.getPoints());
                model.addRow(dataList);
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
