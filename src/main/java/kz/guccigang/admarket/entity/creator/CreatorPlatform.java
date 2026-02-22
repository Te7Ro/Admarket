package kz.guccigang.admarket.entity.creator;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.enums.Platform;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "creator_platforms")
@Getter
@Setter
@NoArgsConstructor
public class CreatorPlatform extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorProfile creator;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String profileUrl;

    private Integer followersCount;

    private Integer avgViews;
}
