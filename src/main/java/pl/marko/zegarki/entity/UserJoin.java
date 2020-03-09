package pl.marko.zegarki.entity;

public class UserJoin implements java.io.Serializable {

    private int id;
    private String email;
    private String name;
    private int active;
    private String role;

    public UserJoin(int id, String email, String name, int active, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.active = active;
        this.role = role;
    }

    public UserJoin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
