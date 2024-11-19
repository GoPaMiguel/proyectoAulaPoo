package controller;

import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.CreateResidueDTO;
import model.DTO.ResiduoDTO.FindResidueDTO;
import service.award.util.help.ShowAndCreateResidueTable;
import service.residue.command.DeleteResidueHandler;
import service.residue.command.InsertResidueHandler;
import service.residue.query.FindResidueByCodeHandler;
import service.residue.query.SelectAllResiduesHandler;
import service.user.util.helpers.ShowUserAndCreateTableHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResidueController {

    public static void CreateResidueController(CreateResidueDTO createResidueDTO) {
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            InsertResidueHandler insertResidueHandler = new InsertResidueHandler(con);
            con.setAutoCommit(false);
            insertResidueHandler.insert(new Residue(createResidueDTO));
            con.commit();
            JOptionPane.showMessageDialog(null, "Residue created");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear residue");
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al rollback");
            }
        } finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar conexion");
                }
            }
        }
    }

    public static void DeleteResidueController(FindResidueDTO findResidueDTO) {
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            DeleteResidueHandler deleteResidueHandler = new DeleteResidueHandler(con);
            con.setAutoCommit(false);
            deleteResidueHandler.Delete(new Residue(findResidueDTO));
            con.commit();
            JOptionPane.showMessageDialog(null, "Residue deleted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar residue");
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al rollback");
            }
            try {
                ConnectionJDBC.closeConecction(con);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexion");
            }
        }
    }

    public static Residue GetResidueController(FindResidueDTO findResidueDTO) {
        Residue residue = null;
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            FindResidueByCodeHandler findResidueByCodeHandler = new FindResidueByCodeHandler(con);
            residue = findResidueByCodeHandler.findById(new Residue(findResidueDTO));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar residue");
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al rollback");
            }

        } finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar conexion");
                }
            }
        }
        return residue;
    }

    public static List<Residue> GetResiduesController() {
        List<Residue> residues = new ArrayList<Residue>();
        Connection con = null;

        try {
            con = ConnectionJDBC.getConnection();
            con.setAutoCommit(false);
            SelectAllResiduesHandler selectAllResiduesHandler = new SelectAllResiduesHandler(con);
            residues = selectAllResiduesHandler.selectAll();
            con.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar residues");
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al rollback");
            }
        }finally {
            if (con != null) {
                try {
                    ConnectionJDBC.closeConecction(con);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar conexion");
                }
            }
        }
        return residues;
    }

    public static void ShowResidueController(JTable table) {
        List<Residue> residues = GetResiduesController();
        ShowAndCreateResidueTable showUserAndResidueTableHandler = new ShowAndCreateResidueTable();
        showUserAndResidueTableHandler.showTable(table, residues);
    }

}
