package org.samarBg.repository;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.samarBg.model.entities.HorseOfferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoriesOfferRepository extends JpaRepository<AccessoryOfferEntity, Long> {

    Page<AccessoryOfferEntity> findByIsActive(int isActive, Pageable pageable);
}
