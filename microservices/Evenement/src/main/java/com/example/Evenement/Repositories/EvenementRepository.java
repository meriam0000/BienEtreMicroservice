package com.example.Evenement.Repositories;

import com.example.Evenement.QueryModels.EvenementView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<EvenementView,String>{
}
