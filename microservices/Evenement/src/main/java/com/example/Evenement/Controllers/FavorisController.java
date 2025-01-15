package com.example.Evenement.Controllers;

import com.example.Evenement.Queries.GetFavorisEventQuery;
import com.example.Evenement.QueryModels.FavorisReadModel;
import com.example.Evenement.Services.FavorisService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoris")
public class FavorisController {
    private final FavorisService favorisService;
    private final QueryGateway queryGateway;

    @Autowired
    public FavorisController(FavorisService favorisService , QueryGateway queryGateway) {
        this.favorisService = favorisService;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/add")
    public void addEventToFavoris(@RequestParam(value = "userId") String userId, @RequestParam(value = "eventId") String eventId) {
        favorisService.addEventToFavoris(userId, eventId);
    }
    @GetMapping("/")
    public List<FavorisReadModel> getFavorisEvents(@RequestParam(value="userId") String userId) {
        return queryGateway.query(new GetFavorisEventQuery(userId), ResponseTypes.multipleInstancesOf(FavorisReadModel.class)).join();
    }


}
