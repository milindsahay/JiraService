package test.dao;

import test.models.Project;

public interface ProjectDaoInterface {
    public void addProject(Project project);

    public Project getProject(String id);

    public void deleteProject(String id);

}
