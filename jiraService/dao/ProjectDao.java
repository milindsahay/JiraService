package test.dao;

import test.models.Project;

import java.util.HashMap;

public class ProjectDao implements ProjectDaoInterface{

    private HashMap<String, Project> projects = new HashMap<>();

    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    public Project getProject(String id) {
        return projects.get(id);
    }

    public void deleteProject(String id) {
        projects.remove(id);
    }
}
