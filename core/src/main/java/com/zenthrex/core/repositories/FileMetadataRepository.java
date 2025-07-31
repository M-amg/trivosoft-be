package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.file.FileMetadata;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.FileStatus;
import com.zenthrex.core.enums.FileType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {

    Optional<FileMetadata> findByStoredFilename(String storedFilename);

    List<FileMetadata> findByUploadedByAndEntityTypeAndEntityId(User uploadedBy, String entityType, Long entityId);

    Page<FileMetadata> findByUploadedByOrderByCreatedAtDesc(User uploadedBy, Pageable pageable);

    Page<FileMetadata> findByFileTypeOrderByCreatedAtDesc(FileType fileType, Pageable pageable);

    @Query("""

            SELECT fm FROM FileMetadata fm 
        WHERE (:uploadedBy IS NULL OR fm.uploadedBy = :uploadedBy)
        AND (:fileType IS NULL OR fm.fileType = :fileType)
        AND (:status IS NULL OR fm.status = :status)
        AND (:entityType IS NULL OR fm.entityType = :entityType)
        AND (:startDate IS NULL OR fm.createdAt >= :startDate)
        AND (:endDate IS NULL OR fm.createdAt <= :endDate)
        ORDER BY fm.createdAt DESC
        """)
    Page<FileMetadata> findFilesWithFilters(
            @Param("uploadedBy") User uploadedBy,
            @Param("fileType") FileType fileType,
            @Param("status") FileStatus status,
            @Param("entityType") String entityType,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    List<FileMetadata> findByStatusAndExpiresAtBefore(FileStatus status, LocalDateTime expiry);

    Long countByUploadedBy(User uploadedBy);

    @Query("SELECT SUM(fm.fileSize) FROM FileMetadata fm WHERE fm.uploadedBy = :user")
    Long getTotalFileSizeByUser(@Param("user") User user);

    List<FileMetadata> findByChecksumAndUploadedByNot(String checksum, User uploadedBy);

    boolean existsByStoredFilename(String storedFilename);
}
