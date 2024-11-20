package service.award.query;

import Interface.query.ISelectAll;
import database.ConnectionJDBC;
import model.CORE.Award;
import model.DTO.AwardDTO.ShowAwardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectAllAwardsHandler implements ISelectAll<Award> {

    private static final String SELECT_ALL_AWARDS_SQL = "SELECT * FROM awards";
    private Connection connection;

    public SelectAllAwardsHandler(Connection connection) { this.connection = connection;}
    public SelectAllAwardsHandler() {}

    @Override
    public List<Award> selectAll() throws SQLException {
        List<Award> awards = new ArrayList<Award>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connection != null ? connection : ConnectionJDBC.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_AWARDS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                int points = rs.getInt("points");
                Award award = new Award(new ShowAwardDTO(id, code, name, points));
                awards.add(award);
            }
        }finally {
            if (rs != null && ps != null) {
                ConnectionJDBC.closeConecction(rs);
                ConnectionJDBC.closeConecction(ps);
            }
        }



        return awards;
    }
}
