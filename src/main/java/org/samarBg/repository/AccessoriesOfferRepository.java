package org.samarBg.repository;


import org.samarBg.models.AccessoryOfferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoriesOfferRepository extends JpaRepository<AccessoryOfferEntity, Long> {

    Page<AccessoryOfferEntity> findByIsActive(int isActive, Pageable pageable);
}
