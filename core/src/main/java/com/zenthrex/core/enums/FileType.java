package com.zenthrex.core.enums;

import lombok.Getter;

@Getter
public enum FileType {
    IMAGE("Image", "jpg,jpeg,png,gif,webp", 5 * 1024 * 1024), // 5MB
    DOCUMENT("Document", "pdf,doc,docx,txt", 10 * 1024 * 1024), // 10MB
    AVATAR("Avatar", "jpg,jpeg,png", 2 * 1024 * 1024), // 2MB
    SPREADSHEET("Spreadsheet", "xls,xlsx,csv", 5 * 1024 * 1024), // 5MB
    ARCHIVE("Archive", "zip,rar,7z", 50 * 1024 * 1024), // 50MB
    VIDEO("Video", "mp4,avi,mov,wmv", 100 * 1024 * 1024), // 100MB
    AUDIO("Audio", "mp3,wav,ogg,m4a", 10 * 1024 * 1024); // 10MB

    private final String displayName;
    private final String allowedExtensions;
    private final long maxSize;

    FileType(String displayName, String allowedExtensions, long maxSize) {
        this.displayName = displayName;
        this.allowedExtensions = allowedExtensions;
        this.maxSize = maxSize;
    }

    public boolean isExtensionAllowed(String extension) {
        return allowedExtensions.toLowerCase().contains(extension.toLowerCase());
    }
}
