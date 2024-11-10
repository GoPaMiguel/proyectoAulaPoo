package model.CORE;

import model.DTO.userDTO.CreateUserDTO;
import model.DTO.userDTO.ShowUserDTO;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String career;
    private String idUser;
    private int points;

    public User() {
    }

    public User(CreateUserDTO createUserDTO) {
        this.name = createUserDTO.name();
        this.lastName = createUserDTO.lastName();
        this.email = createUserDTO.email();
        this.career = createUserDTO.career();
        this.idUser = createUserDTO.idUser();
    }

    public User(ShowUserDTO showUserDTO) {
        this.name = showUserDTO.name();
        this.lastName = showUserDTO.lastName();
        this.email = showUserDTO.email();
        this.career = showUserDTO.career();
        this.idUser = showUserDTO.idUser();
        this.points = showUserDTO.points();
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", career='").append(career).append('\'');
        sb.append(", idUser='").append(idUser).append('\'');
        sb.append(", points=").append(points);
        sb.append('}');
        return sb.toString();
    }
}
