package com.example.Evenement.QuerieHandlers;

import com.example.Evenement.Queries.findcolleaguesWithSameInterets;
import com.example.Evenement.QueryModels.Employe;
import com.example.Evenement.Repositories.EmployeRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class EmployeQueryHandler {
    private final EmployeRepository repository;

    public EmployeQueryHandler(EmployeRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    @Transactional
    public List<Employe> handle(findcolleaguesWithSameInterets query) {
        return repository.findColleaguesWithSameInterests(query.getEmployeId(),query.getInterests());
    }
}
