package service.user.util.helpers;

import Interface.utils.helpers.ISelectElementTable;
import model.CORE.User;
import model.DTO.userDTO.CreateUserDTO;
import model.DTO.userDTO.ShowUserDTO;

import javax.swing.*;

public class SelectUserTableHandler implements ISelectElementTable<User> {
    @Override
    public User selectElement(JTable table) {
        User selectedUser = null;

        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int selectId = (int) table.getValueAt(selectedRow, 0);
                String name = table.getValueAt(selectedRow, 1).toString();
                String lastName = table.getValueAt(selectedRow, 2).toString();
                String email = table.getValueAt(selectedRow, 3).toString();
                String career = table.getValueAt(selectedRow, 4).toString();
                String idUser = table.getValueAt(selectedRow, 5).toString();
                int points = Integer.parseInt(table.getValueAt(selectedRow, 6).toString());
                selectedUser = new User(new ShowUserDTO( selectId, name, lastName, email, career, idUser, points));
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return selectedUser;
    }
}
