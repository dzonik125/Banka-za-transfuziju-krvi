package group8.bloodbank.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String number;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    public Address(String country, String city, String street, String number) {
    }
}