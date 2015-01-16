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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dplecan
 */
@NamedQueries({
    @NamedQuery(name = Measure.FIND_ALL, query = "SELECT m FROM Measure m"),
    @NamedQuery(name = Measure.DELETE_ALL, query = "DELETE FROM Measure m")
})
@Entity
@Table(name = "indoor_measures")
public class Measure implements Serializable {
    public static final String FIND_ALL = "Measure.findAll";
    public static final String DELETE_ALL = "Measure.deleteAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_measures_id_seq", sequenceName = "en_indoor_measures_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "measure_timestamp")
    private Date timestamp;
    
    @OneToMany
    private List<MeasureValue> measures;

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

    public List<MeasureValue> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasureValue> measureValues) {
        this.measures = measureValues;
    }
}
