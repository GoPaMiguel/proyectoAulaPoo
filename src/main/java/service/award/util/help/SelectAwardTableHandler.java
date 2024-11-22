package service.award.util.help;

import Interface.utils.helpers.ISelectElementTable;
import model.CORE.Award;
import model.DTO.AwardDTO.ShowAwardDTO;

import javax.swing.*;

public class SelectAwardTableHandler implements ISelectElementTable<Award> {

    @Override
    public Award selectElement(JTable table) {
        Award award = null;
        int row = table.getSelectedRow();
        try{
            if (row!= -1) {
                String id = (String) table.getValueAt(row, 0);
                int idSelected = Integer.parseInt(id);
                String code = table.getValueAt(row, 1).toString();
                String name = table.getValueAt(row, 2).toString();
                String points = table.getValueAt(row, 3).toString();
                int pointsSelected = Integer.parseInt(points);
                award = new Award(new ShowAwardDTO(idSelected, code, name, pointsSelected));
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un elemento");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error, "+e.getMessage());
        }
        return award;
    }
}
