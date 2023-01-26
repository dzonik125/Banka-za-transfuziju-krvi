package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @Column
    @NotNull
    private String answer1;
    @Column
    @NotNull
    private String answer3;
    @Column
    @NotNull
    private String answer2;
    @Column
    @NotNull
    private String answer4;
    @Column
    @NotNull
    private String answer5;
    @Column
    @NotNull
    private String answer6;
    @Column
    @NotNull
    private String answer7;
    @Column
    @NotNull
    private String answer8;
    @Column
    @NotNull
    private String answer9;
    @Column
    @NotNull
    private String answer10;

}