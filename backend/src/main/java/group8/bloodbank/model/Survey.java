package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

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

    //@ManyToOne
    //@JoinColumn(name = "donor_id")
    //@Transient
    //private Donor donor;
    @Column
    private String answer1;
    @Column
    private String answer3;
    @Column
    private String answer2;
    @Column
    private String answer4;
    @Column
    private String answer5;
    @Column
    private String answer6;
    @Column
    private String answer7;
    @Column
    private String answer8;
    @Column
    private String answer9;
    @Column
    private String answer10;

}