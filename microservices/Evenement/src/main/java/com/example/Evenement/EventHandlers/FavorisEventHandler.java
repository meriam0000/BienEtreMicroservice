package com.example.Evenement.EventHandlers;

import com.example.Evenement.Events.EventAddedToFavorisEvent;
import com.example.Evenement.QueryModels.EvenementView;
import com.example.Evenement.QueryModels.FavorisReadModel;
import com.example.Evenement.Repositories.EvenementRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Evenement.Repositories.favorisReadModelRepository;

@Component
public class FavorisEventHandler {

    private final favorisReadModelRepository favorisReadModelRepository;
    private final EvenementRepository evenementRepository;

    @Autowired
    public FavorisEventHandler(favorisReadModelRepository favorisReadModelRepository, EvenementRepository evenementRepository) {
        this.favorisReadModelRepository = favorisReadModelRepository;
        this.evenementRepository = evenementRepository;
    }

    @EventHandler
    public void on(EventAddedToFavorisEvent event) {

        FavorisReadModel favorisReadModel = new FavorisReadModel(
                event.getFavorisId(),
                event.getEventId(),
                event.getUserId());

        favorisReadModelRepository.save(favorisReadModel);
    }
}
