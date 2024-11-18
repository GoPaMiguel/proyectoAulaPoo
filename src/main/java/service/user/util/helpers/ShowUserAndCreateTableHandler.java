package service.user.util.helpers;

import Interface.utils.helpers.IShowElementAndCreateTable;
import model.CORE.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class ShowUserAndCreateTableHandler implements IShowElementAndCreateTable<User> {
    @Override
    public void showTable(JTable table, List<User> list) {

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

        //Almacena datos
        String[] dataList = new String[7];

        try {
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningun usuario");
                return;
            }else{
               for (User user : list) {
                dataList[0] = String.valueOf(user.getId());
                dataList[1] = String.valueOf(user.getName());
                dataList[2] = String.valueOf(user.getLastName());
                dataList[3] = String.valueOf(user.getEmail());
                dataList[4] = String.valueOf(user.getCareer());
                dataList[5] = String.valueOf(user.getIdUser());
                dataList[6] = String.valueOf(user.getPoints());
                model.addRow(dataList);
               }
               table.setModel(model);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
