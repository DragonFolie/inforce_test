package com.repository;

import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findUserByEmail(String email);

  boolean existsByEmail(String email);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.firstName = :newValue WHERE u.email = :search")
  public void updateFirstNameField(@Param("newValue") String newValue, @Param("search") String search);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.lastName = :newValue WHERE u.email = :search")
  public void updateLastNameField(@Param("newValue") String newValue, @Param("search") String search);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.country = :newValue WHERE u.email = :search")
  public void updateCountryNameField(@Param("newValue") String newValue, @Param("search") String search);

}
