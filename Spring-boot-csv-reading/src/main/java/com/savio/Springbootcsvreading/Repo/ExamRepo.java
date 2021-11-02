package com.savio.Springbootcsvreading.Repo;

import java.util.List;

import com.savio.Springbootcsvreading.Model.SavioExam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamRepo extends JpaRepository<SavioExam, 	String >{
    @Query("select nameOfTab from SavioExam s where s.nameOfTab like %:name%")
    List<String> findTabNames(@Param("name") String name);
}