package de.fiducia.langermann.langer_mann.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

import javax.persistence.*;
import java.time.LocalDateTime;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//

@Entity
@Table(name = "tblschweine")
public class SchweinEntity {


    @Version // Optimistic Locking
    private LocalDateTime version;

    @Id
    @Column(nullable = false, length = 36) // Nur bei Create Table
    private String id;


    @Column(nullable = true, length = 30) // Nur bei Create Table
    private String name;

    private int gewicht;

}
