package service.user.util.helpers;

import Interface.utils.helpers.IShowElementTable;
import model.CORE.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShowUserTable implements IShowElementTable<User> {
    @Override
    public void showTable(JTable table, List<User> list) {
        String[] dataList = new String[7];
        DefaultTableModel model = new DefaultTableModel();

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
