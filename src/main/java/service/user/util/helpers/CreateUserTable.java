package service.user.util.helpers;

import Interface.utils.helpers.ICreateTable;

import javax.swing.*;
import javax.swing.table.*;

public class CreateUserTable implements ICreateTable {

    @Override
    public void createTable(JTable table) {
        //table structure
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> alphabeticOrder = new TableRowSorter<TableModel>(model);
        table.setRowSorter(alphabeticOrder);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Email");
        model.addColumn("Carrera");
        model.addColumn("Cedula");
        model.addColumn("Puntos");
        table.setModel(model);
    }
}
