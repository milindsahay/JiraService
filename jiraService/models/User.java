package test.models;

public class User {
    private String name;
    private String id;

    private boolean isAdmin;

    public User(String name, String id, boolean isAdmin) {
        this.name = name;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin(){
        return isAdmin;
    }
}
