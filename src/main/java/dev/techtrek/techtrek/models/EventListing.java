package dev.techtrek.techtrek.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "event_listings")
public class EventListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "rsvp_url", length = 2000, unique = true)
    private String rsvpUrl;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "listing_date", nullable = false)
    private Date listingDate;

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived;
  
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
}