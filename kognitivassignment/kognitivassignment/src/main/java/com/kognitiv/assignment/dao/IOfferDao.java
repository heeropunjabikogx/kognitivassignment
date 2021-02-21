package com.kognitiv.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kognitiv.assignment.entity.Offer;

public interface IOfferDao extends JpaRepository<Offer, Integer> {
	@Query(value="SELECT o FROM Offer o ORDER BY o.id offset ?1 limit ?2", nativeQuery = true)
    public List<Offer> findAllPaged( int offset, int limit);
}
