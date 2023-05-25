package fr.hesias.gabblerapi.infrastructure.persister.persistence.entity;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.DurationTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscription_offer")
public class SubscriptionOffer
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "duration")
    @Enumerated(EnumType.STRING)
    private DurationTypeEnum duration;

    public SubscriptionOffer()
    {

    }

    public SubscriptionOffer(int id, String name, String description, float price, DurationTypeEnum duration)
    {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public SubscriptionOffer(int id)
    {

        this.id = id;
    }

}
