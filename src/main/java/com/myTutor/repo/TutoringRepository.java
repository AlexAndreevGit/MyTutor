package com.myTutor.repo;

import com.myTutor.model.entity.TutoringOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutoringRepository extends JpaRepository<TutoringOffer,Long> {

    List<TutoringOffer> findAllByCategoryId(int i);

    List<TutoringOffer> findByAddedById(Long id);
}
