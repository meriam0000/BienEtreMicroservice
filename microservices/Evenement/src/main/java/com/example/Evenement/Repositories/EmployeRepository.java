package com.example.Evenement.Repositories;

import com.example.Evenement.QueryModels.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, String> {
    @Query("SELECT e FROM Employe e JOIN e.centresInteret ci " +
            "WHERE ci IN :interests AND e.employeId <> :employeId " +
            "GROUP BY e " +
            "HAVING COUNT(ci) > 0")
    List<Employe> findColleaguesWithSameInterests(String employeId, List<String> interests);
}
