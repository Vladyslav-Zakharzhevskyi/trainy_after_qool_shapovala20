package com.investigation.develop.circle.dto;

import java.util.Date;
import java.util.UUID;

public class OfferDto {

    private Long id;
    private String message;
    private String author;
    private UUID authorId;
    private Date dateCreated;
    private Date dateUpdated;
    private Boolean anonymousPost;

    public OfferDto(Long id, String message, Date dateCreated, Date dateUpdated, String author, UUID authorId, Boolean anonymousPost) {
        this.id = id;
        this.message = message;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.author = author;
        this.authorId = authorId;
        this.anonymousPost = anonymousPost;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public Boolean getAnonymousPost() {
        return anonymousPost;
    }
}
