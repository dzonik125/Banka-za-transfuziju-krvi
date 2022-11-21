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
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String url;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public BloodBank bloodBank;

    public Report(String url, BloodBank b) {
        this.url = url;
        this.bloodBank = b;
    }
}
