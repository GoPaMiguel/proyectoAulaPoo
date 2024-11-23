package service.user.util.helpers;

import Interface.utils.helpers.ISelectElementTable;
import model.CORE.User;
import model.DTO.userDTO.CreateUserDTO;
import model.DTO.userDTO.ShowUserDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SelectUserTableHandler implements ISelectElementTable<User> {
    @Override
    public User selectElement(JTable table) {
        DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Evita la edición de las celdas
        }
    };
        User selectedUser = null;

            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String id = (String) table.getValueAt(selectedRow, 0);
                int idSelected = Integer.parseInt(id);
                String name = table.getValueAt(selectedRow, 1).toString();
                String lastName = table.getValueAt(selectedRow, 2).toString();
                String email = table.getValueAt(selectedRow, 3).toString();
                String career = table.getValueAt(selectedRow, 4).toString();
                String idUser = table.getValueAt(selectedRow, 5).toString();
                int points = Integer.parseInt(table.getValueAt(selectedRow, 6).toString());
                ShowUserDTO dto = new ShowUserDTO(idSelected, name, lastName, email, career, idUser, points, "");
                selectedUser = new User(dto);
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario");
            }


        return selectedUser;
    }
}
