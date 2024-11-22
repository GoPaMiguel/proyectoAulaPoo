package controller;

import database.ConnectionJDBC;
import model.CORE.Award;
import model.CORE.Residue;
import model.DTO.AwardDTO.CreateAwardDTO;
import model.DTO.AwardDTO.FindAwardDTO;
import service.award.command.DeleteAwardHandler;
import service.award.command.InsertAwardHandler;
import service.award.command.UpdateAwardHandler;
import service.award.query.FindAwardByIdHandler;
import service.award.query.SelectAllAwardsHandler;
import service.award.util.help.SelectAwardTableHandler;
import service.award.util.help.ShowAndCreateAwardTable;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AwardController {

    public static void CreateAwardController(CreateAwardDTO createAwardDTO) {
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            InsertAwardHandler insertAwardHandler = new InsertAwardHandler(con);
            con.setAutoCommit(false);
            insertAwardHandler.insert(new Award(createAwardDTO));
            con.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot insert Award, because: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }


        }

    }

    public static void UpdateAwardController(Award award, FindAwardDTO findAwardDTO) {
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            con.setAutoCommit(false);
            UpdateAwardHandler updateAwardHandler = new UpdateAwardHandler(con);
            updateAwardHandler.Update(award, findAwardDTO);
            con.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot update Award, because: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }

    public static void DeleteAwardController(FindAwardDTO findAwardDTO) {
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            con.setAutoCommit(false);
            DeleteAwardHandler deleteAwardHandler = new DeleteAwardHandler(con);
            deleteAwardHandler.Delete(new Award(findAwardDTO));
            con.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot delete Award, because: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        } finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }

    public static Award GetAwardController(FindAwardDTO findAwardDTO) {
        Connection con = null;
        Award award = null;
        try {
            con = ConnectionJDBC.getConnection();
            con.setAutoCommit(false);
            FindAwardByIdHandler findAwardByIdHandler = new FindAwardByIdHandler(con);
            award =  findAwardByIdHandler.findById(new Award(findAwardDTO));
            con.commit();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
        return award;
    }

    public static List<Award> GetAllAwardController() {
        Connection con = null;
        List<Award> awards = new ArrayList<>();

        try {
            con = ConnectionJDBC.getConnection();
            con.setAutoCommit(false);
            SelectAllAwardsHandler selectAllAwardsHandler = new SelectAllAwardsHandler(con);
            awards = selectAllAwardsHandler.selectAll();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
        return awards;
    }

    public static void ShowAwardController(JTable table) {
        Connection con = null;
        List<Award> awards = GetAllAwardController();
        if (awards.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Awards Found");
        }else {
            ShowAndCreateAwardTable showAndCreateAwardTable = new ShowAndCreateAwardTable();
            showAndCreateAwardTable.showTable(table, awards);
        }
    }

    public static Award SelectAwardController(JTable table) {
        SelectAwardTableHandler selectAwardTableHandler = new SelectAwardTableHandler();

        return selectAwardTableHandler.selectElement(table);
    }
}
