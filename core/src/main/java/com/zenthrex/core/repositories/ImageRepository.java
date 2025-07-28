package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("""
            SELECT i FROM Image i  WHERE i.entityId =:entityId AND i.entity = :entity
       """)
    Optional<List<Image>> getImages(String entity, Long entityId);
}