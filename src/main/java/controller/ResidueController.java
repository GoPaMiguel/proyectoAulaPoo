package controller;

import database.ConnectionJDBC;
import model.CORE.Residue;
import model.DTO.ResiduoDTO.CreateResidueDTO;
import model.DTO.ResiduoDTO.FindResidueDTO;
import service.residue.command.UpdateResidueHandler;
import service.residue.util.help.SelectResidueTableHandler;
import service.residue.util.help.ShowAndCreateResidueTable;
import service.residue.command.DeleteResidueHandler;
import service.residue.command.InsertResidueHandler;
import service.residue.query.FindResidueByCodeHandler;
import service.residue.query.SelectAllResiduesHandler;

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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear residue"+e.getMessage());
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

    public static void UpdateResidueController(Residue residue, FindResidueDTO dto){
        Connection con = null;
        try {
            con = ConnectionJDBC.getConnection();
            con.setAutoCommit(false);
            UpdateResidueHandler updateResidueHandler = new UpdateResidueHandler(con);
            updateResidueHandler.Update(residue, dto);
            con.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot update residue because: "+e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al rollback"+ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Error al consultar residues"+e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al rollback"+e.getMessage());
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
        if (!residues.isEmpty() && residues != null) {
            ShowAndCreateResidueTable showUserAndResidueTableHandler = new ShowAndCreateResidueTable();
            showUserAndResidueTableHandler.showTable(table, residues);
        } else {
            JOptionPane.showMessageDialog(null, "No residues found");
            return;
        }
    }

    public static Residue SelectResidueController(JTable table) {
        SelectResidueTableHandler selectResidueTableHandler = new SelectResidueTableHandler();
        return selectResidueTableHandler.selectElement(table);
    }

}
