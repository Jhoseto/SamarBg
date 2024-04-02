package org.samarBg.model.entities;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
@Entity
@Table(name = "offer_images")
public class OfferImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( nullable = false)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "horse_offer_id")
    private HorseOfferEntity horseOffer;

    @ManyToOne
    @JoinColumn(name = "accessory_offer_id")
    private AccessoryOfferEntity accessoryOffer;


    public Long getId() {
        return id;
    }

    public OfferImageEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public OfferImageEntity setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public HorseOfferEntity getHorseOffer() {
        return horseOffer;
    }

    public OfferImageEntity setHorseOffer(HorseOfferEntity horseOffer) {
        this.horseOffer = horseOffer;
        return this;
    }

    public AccessoryOfferEntity getAccessoryOffer() {
        return accessoryOffer;
    }

    public OfferImageEntity setAccessoryOffer(AccessoryOfferEntity accessoryOffer) {
        this.accessoryOffer = accessoryOffer;
        return this;
    }
}
