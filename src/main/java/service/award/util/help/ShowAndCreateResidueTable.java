package service.award.util.help;

import Interface.utils.helpers.IShowElementAndCreateTable;
import model.CORE.Residue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class ShowAndCreateResidueTable implements IShowElementAndCreateTable<Residue> {
    @Override
    public void showTable(JTable table, List<Residue> list) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> alphabeticOrder = new TableRowSorter<TableModel>(model);
        table.setRowSorter(alphabeticOrder);
        model.addColumn("ID");
        model.addColumn("codigo");
        model.addColumn("tipo");
        model.addColumn("puntos");
        table.setModel(model);

        String[] dataList = new String[4];

        try {
            if (list != null || list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cannot get any residues");
                return;
            }
            for (Residue residue : list) {
                dataList[0] = String.valueOf(residue.getId());
                dataList[1] = residue.getCode();
                dataList[2] = residue.getType();
                dataList[3] = String.valueOf(residue.getPoints());
                model.addRow(dataList);
            }
            table.setModel(model);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
