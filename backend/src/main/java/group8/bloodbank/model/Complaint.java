package group8.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Complaint {

    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String description;

    @Column
    private String answer;


    @ManyToOne
    @JoinColumn(name = "donor_id")
    public Donor donor;

    
    public Complaint(Long id, String description) {
        this.description = description;
        this.id = id;
    }

    public Complaint(Long id, String description, String answer) {
        this.description = description;
        this.id = id;
        this.answer = answer;
    }


    public Complaint() {

    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}