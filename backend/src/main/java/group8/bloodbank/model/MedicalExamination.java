package group8.bloodbank.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalExamination {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Donor donor;

    @OneToOne
    private BloodBank bloodBank;

    @Column
    private String bloodType;

    @Column
    private int amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
