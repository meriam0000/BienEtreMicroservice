package com.example.Evenement.Repositories;

import com.example.Evenement.QueryModels.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant,String >{
}
