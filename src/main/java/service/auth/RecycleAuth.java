package service.auth;

import model.CORE.Residue;
import model.CORE.User;

public class RecycleAuth {
    public static User Recycle(User user, Residue residue, double weight) {

        int pointsCurrent = user.getPoints();
        int pointsResidue = residue.getPoints();
        pointsResidue = Math.toIntExact(Math.round(pointsResidue * weight));

        user.setPoints(pointsCurrent+pointsResidue);

        return user;
    }

}
