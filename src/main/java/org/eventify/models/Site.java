package org.eventify.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "siteName", nullable = false, length = 150)
    private String siteName;

    @Column(name =  "address", nullable = false, length = 40)
    private String address;

    @Column(name = "city", nullable = false, length = 100)
    private String city;
}
