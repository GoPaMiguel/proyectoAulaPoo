package service.residue.command;

import Interface.command.IDelete;
import model.CORE.Residue;

import java.sql.SQLException;

public class DeleteResidueHandler implements IDelete<Residue> {

    private static final String DELETE = "DELETE FROM residue WHERE idresidue=?";

    @Override
    public void Delete(Residue id) throws SQLException {

    }
}
