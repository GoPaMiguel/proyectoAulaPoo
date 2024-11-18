package model.CORE;

import model.DTO.AwardDTO.CreateAwardDTO;
import model.DTO.AwardDTO.FindAwardDTO;
import model.DTO.AwardDTO.ShowAwardDTO;

public class Award {
    private int id;
    private String code;
    private String name;
    private int points;


    public Award(CreateAwardDTO createAwardDTO) {
        this.code = createAwardDTO.code();
        this.name = createAwardDTO.name();
        this.points = createAwardDTO.points();
    }

    public Award(ShowAwardDTO showAwardDTO) {
        this.code = showAwardDTO.code();
        this.name = showAwardDTO.name();
        this.points = showAwardDTO.points();
        this.id = showAwardDTO.id();
    }

    public Award(FindAwardDTO findAwardDTO) {
        this.code = findAwardDTO.code();
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Award{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", points=").append(points);
        sb.append('}');
        return sb.toString();
    }
}
