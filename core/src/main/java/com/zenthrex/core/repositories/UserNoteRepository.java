package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.user.UserNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNoteRepository extends JpaRepository<UserNote, Long> {

    Page<UserNote> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<UserNote> findByUserAndNoteTypeOrderByCreatedAtDesc(User user, String noteType, Pageable pageable);

    Long countByUser(User user);
}
