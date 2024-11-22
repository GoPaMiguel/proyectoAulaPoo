package service.award.command;

import Interface.command.IUpdate;
import database.ConnectionJDBC;
import model.CORE.Award;
import model.DTO.AwardDTO.FindAwardDTO;
import service.award.util.validations.ExistAwardHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAwardHandler implements IUpdate<Award, FindAwardDTO> {
    private static final String UPDATE = "UPDATE awards SET code=?, name=?, points=? WHERE code=?";
    private Connection connection;

    public UpdateAwardHandler(Connection connection) {
        this.connection = connection;
    }

    public UpdateAwardHandler() {
    }

    @Override
    public void Update(Award award, FindAwardDTO dto) throws SQLException {
        Connection cx = null;
        PreparedStatement ps = null;

        try {
            cx = connection != null ? connection : ConnectionJDBC.getConnection();
            if (!award.getCode().equalsIgnoreCase(dto.code())) {
                ExistAwardHandler existAwardHandler = new ExistAwardHandler();
                boolean ok = existAwardHandler.exist(new FindAwardDTO(award.getCode()), cx);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Cannot Update the award because the code already exists");
                    return;
                }
            }
            ps = cx.prepareStatement(UPDATE);
            ps.setString(1, award.getCode());
            ps.setString(2, award.getName());
            ps.setInt(3, award.getPoints());
            ps.setString(4, dto.code());
            ps.executeUpdate();
        } finally {
            if (ps != null) ConnectionJDBC.closeConecction(ps);
        }
    }
}
