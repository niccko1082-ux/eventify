package org.eventify.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "type", length = 50)
    private String type;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site site;
}
