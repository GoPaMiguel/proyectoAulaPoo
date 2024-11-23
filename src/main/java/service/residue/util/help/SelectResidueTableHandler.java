package service.residue.util.help;

import Interface.utils.helpers.ISelectElementTable;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.ShowResidueDTO;

import javax.swing.*;

public class SelectResidueTableHandler implements ISelectElementTable<Residue> {
    @Override
    public Residue selectElement(JTable table) {
        Residue residue = null;
        int selectedRow = table.getSelectedRow();
        try{
            if (selectedRow != -1) {
                String id = (String) table.getValueAt(selectedRow, 0);
                int idSelected = Integer.parseInt(id);
                String code = (String) table.getValueAt(selectedRow, 1);
                String type = (String) table.getValueAt(selectedRow, 2);
                String points = (String) table.getValueAt(selectedRow, 3);
                int pointsSelected = Integer.parseInt(points);
                ShowResidueDTO dto = new ShowResidueDTO(idSelected, code, type, pointsSelected);
                residue = new Residue(dto);
            }else {
                JOptionPane.showMessageDialog(null, "Select user");
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Error, "+e.getMessage());
        }
        return residue;
    }
}
