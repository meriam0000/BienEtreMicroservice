package com.example.Evenement.Repositories;

import com.example.Evenement.QueryModels.FavorisReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface favorisReadModelRepository extends JpaRepository<FavorisReadModel, String> {
    List<FavorisReadModel> findByuserId(String userId);

}
