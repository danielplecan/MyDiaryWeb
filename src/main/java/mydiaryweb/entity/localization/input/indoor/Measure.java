package mydiaryweb.entity.localization.input.indoor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "indoor_measures")
public class Measure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_measures_id_seq", sequenceName = "en_indoor_measures_id_seq", allocationSize = 1)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "measure_timestamp")
    private Date timestamp;
    
    @OneToMany
    private List<CalibrationValue> measureValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<CalibrationValue> getMeasureValues() {
        return measureValues;
    }

    public void setMeasureValues(List<CalibrationValue> measureValues) {
        this.measureValues = measureValues;
    }
}
