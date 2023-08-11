package DAO.Interfaces;

import Model.Subject;

import java.util.List;

public interface SubjectDAO {

    int addSubject(Subject subject);

    boolean removeSubject(String subjectName);

    Subject getSubjectByName(String subjectName);

    Subject getSubjectById(int id);

    List<Subject> getAllSubjects();
}