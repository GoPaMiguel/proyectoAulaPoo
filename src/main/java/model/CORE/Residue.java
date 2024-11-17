package model.CORE;

import model.DTO.ResiduoDTO.FindResidueDTO;
import model.DTO.ResiduoDTO.ShowResidueDTO;

public class Residue {
    private int id;
    private String code;
    private String type;
    private int points;

    public Residue(FindResidueDTO findResidueDto) {
        this.code = findResidueDto.code();
    }

    public Residue(ShowResidueDTO showResidueDto) {
        this.id = showResidueDto.id();
        this.code = showResidueDto.code();
        this.type = showResidueDto.type();
        this.points = showResidueDto.points();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Residue{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", points=").append(points);
        sb.append('}');
        return sb.toString();
    }
}
