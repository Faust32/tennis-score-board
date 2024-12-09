package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString   
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players", indexes = {@Index(name = "player_name", columnList = "name")})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

}




