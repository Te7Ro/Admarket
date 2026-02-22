package kz.guccigang.admarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
public class Country extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @Column(length = 2, unique = true, nullable = false)
    private String code;
}
