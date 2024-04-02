package org.samarBg.repository;


import org.samarBg.model.entities.AccessoryOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoriesOfferRepository extends JpaRepository<AccessoryOfferEntity, Long> {
    Optional<AccessoryOfferEntity> findByAuthorName(String authorName);
}
