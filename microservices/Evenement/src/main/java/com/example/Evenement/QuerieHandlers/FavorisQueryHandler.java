package com.example.Evenement.QuerieHandlers;

import com.example.Evenement.Queries.GetFavorisEventQuery;
import com.example.Evenement.QueryModels.FavorisReadModel;
import com.example.Evenement.Repositories.favorisReadModelRepository;
import jakarta.transaction.Transactional;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavorisQueryHandler {
    private final favorisReadModelRepository repository;
    @Autowired
    public FavorisQueryHandler(favorisReadModelRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<FavorisReadModel> handle(GetFavorisEventQuery query) {
        return repository.findByuserId(query.getUserId());
    }
}
