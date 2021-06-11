package de.fiducia.langermann.langer_mann.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//

@Entity
@Table(name = "tblpersonen")
public class PersonEntity {

    @Id
    @Column(nullable = false, length = 36) // Nur bei Create Table
    private String id;

   // @Version // Optimistic Locking
   // private LocalDateTime version;

    @Column(nullable = true, length = 30) // Nur bei Create Table
    private String vorname;

    @Column(nullable = false, length = 30) // Nur bei Create Table
    private String nachname;


}
