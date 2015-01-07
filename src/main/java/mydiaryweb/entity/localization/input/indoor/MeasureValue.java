package mydiaryweb.entity.localization.input.indoor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "indoor_measure_values")
public class MeasureValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_measure_values_id_seq", sequenceName = "en_indoor_measure_values_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "measure_mac", length = 1000)
    private String mac;
    
    @Basic(optional = false)
    @Column(name = "measure_value")
    private int value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
