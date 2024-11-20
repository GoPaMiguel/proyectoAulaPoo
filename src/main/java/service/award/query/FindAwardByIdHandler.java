package service.award.query;

import Interface.query.IFindById;
import database.ConnectionJDBC;
import model.CORE.Award;
import model.DTO.AwardDTO.FindAwardDTO;
import model.DTO.AwardDTO.ShowAwardDTO;
import service.award.util.validations.ExistAwardHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAwardByIdHandler implements IFindById<Award> {

    private static final String SELECT_AWARD_BY_ID = "SELECT * FROM awards WHERE code = ?";
    private Connection connection;
    public FindAwardByIdHandler(Connection connection) {}
    public FindAwardByIdHandler(){};

    @Override
    public Award findById(Award awardCode) throws SQLException {
       Award award = null;
        ExistAwardHandler existHandler = new ExistAwardHandler();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exist;

        try {
            conn = connection != null ? connection : ConnectionJDBC.getConnection();
            ps = conn.prepareStatement(SELECT_AWARD_BY_ID);
            ps.setString(1, awardCode.getCode());
            rs = ps.executeQuery();
            exist = existHandler.exist(new FindAwardDTO(awardCode.getCode()), conn);
            if (exist) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                int points = rs.getInt("points");
                award = new Award(new ShowAwardDTO(id, code, name, points));
            }else{
                JOptionPane.showMessageDialog(null, "No existe el Award");
                return null;
            }
        }finally {
            if (rs != null && ps != null) {
                ConnectionJDBC.closeConecction(ps);
                ConnectionJDBC.closeConecction(rs);
            }
        }



       return award;
    }
}
