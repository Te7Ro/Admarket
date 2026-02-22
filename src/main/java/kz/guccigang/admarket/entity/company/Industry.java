package kz.guccigang.admarket.entity.company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kz.guccigang.admarket.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "industries")
@Getter
@Setter
@NoArgsConstructor
public class Industry extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
}
