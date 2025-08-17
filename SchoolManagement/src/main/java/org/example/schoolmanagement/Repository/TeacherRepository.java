package org.example.schoolmanagement.Repository;

import org.example.schoolmanagement.Model.Address;
import org.example.schoolmanagement.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherByEmail(String email);

    Teacher findTeacherById(Integer id);

    Teacher findTeacherByAddress(Address address);
}
