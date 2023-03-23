package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "auto_renewal")
    private boolean autoRenewal;

    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "id_subscription_offer", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private SubscriptionOffer subscriptionOffer;

    @OneToMany()
    private List<User> users;

}
