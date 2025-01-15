package com.example.Evenement.Repositories;

import com.example.Evenement.QueryModels.EvenementView;
import com.example.Evenement.QueryModels.RatingAndComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingAndCommentRepository extends JpaRepository<RatingAndComment, Long> {
    List<RatingAndComment> findRatingAndCommentByEvent(EvenementView event);

}
