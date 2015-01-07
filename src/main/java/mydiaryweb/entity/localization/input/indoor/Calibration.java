package mydiaryweb.entity.localization.input.indoor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.codehaus.jackson.annotate.JsonValue;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "indoor_calibrations")
public class Calibration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_calibrations_id_seq", sequenceName = "en_indoor_calibrations_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @OneToMany
//    @JsonUnwrapped
    private List<CalibrationValue> calibrationValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CalibrationValue> getCalibrationValues() {
        return calibrationValues;
    }

    public void setCalibrationValues(List<CalibrationValue> calibrationValues) {
        this.calibrationValues = calibrationValues;
    }
}
