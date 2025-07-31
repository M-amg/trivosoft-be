package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.accessory.AccessoryReview;
import com.zenthrex.core.entites.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccessoryReviewRepository extends JpaRepository<AccessoryReview, Long> {

    Page<AccessoryReview> findByAccessoryOrderByCreatedOnDesc(Accessory accessory, Pageable pageable);

    Optional<AccessoryReview> findByAccessoryAndReviewer(Accessory accessory, User reviewer);

    boolean existsByAccessoryAndReviewer(Accessory accessory, User reviewer);

    Long countByAccessory(Accessory accessory);

    @Query("SELECT AVG(ar.rating) FROM AccessoryReview ar WHERE ar.accessory = :accessory")
    Double calculateAverageRating(@Param("accessory") Accessory accessory);

    @Query("""

            SELECT COUNT(ar) FROM AccessoryReview ar 
        WHERE ar.accessory = :accessory 
        AND ar.rating = :rating
        """)
    Long countByAccessoryAndRating(@Param("accessory") Accessory accessory, @Param("rating") Integer rating);

    Page<AccessoryReview> findByReviewerOrderByCreatedOnDesc(User reviewer, Pageable pageable);

    void deleteByAccessory(Accessory accessory);
}
