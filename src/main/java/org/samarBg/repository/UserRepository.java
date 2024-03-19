package org.samarBg.repository;


import org.samarBg.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUserConfirmationCode(String confirmationCode);
    Optional<UserEntity> findByIsActive(boolean isActive);
    Optional<UserEntity>findUserImageByUsername(String username);



}
