package test.models;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String id;
    private String description;

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Project(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
