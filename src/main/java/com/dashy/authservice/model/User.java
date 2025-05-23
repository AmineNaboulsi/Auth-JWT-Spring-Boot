package com.dashy.authservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "users")
@Data
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true , nullable = false)
    private String userName;

    @Column(unique = true , nullable = false)
    private String phoneNumber;

    @Column(unique = true , nullable = false)
    private String email;

    @Column(unique = true , nullable = false)
    private String password;

    @Column(name = "email_verified")
    private boolean emailVerification;

    @Column(name = "phone_verified")
    private boolean phoneVerification;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
    }

    @PostPersist
    void Registred() {
        logger.info("User {} created : {}" ,this.userName ,this.id);
        System.out.println("User "+ this.userName +" created :" + this.id);
    }

    @PostUpdate
    void profileUpdate() {
        logger.info("User updated with ID {}", id);
        System.out.println("User updated with ID " + id);

    }
}
