package mydiaryweb.entity.behaviour.output;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mydiaryweb.entity.inferences.Action;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "recurring_behaviours")
public class RecurringBehaviour implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "recurring_behaviours_id_seq", sequenceName = "recurring_behaviours_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Action action;
    
    @Basic(optional = false)
    @Column(name = "behaviour_day", length = 1000)
    private String day;
    
    @Basic(optional = false)
    @Column(name = "person_name", length = 1000)
    private String personName;
    
    @Basic(optional = false)
    @Column(name = "location_name", length = 1000)
    private String locationName;
    
    @Basic(optional = false)
    @Column(name = "how_recurring_by_day", length = 1000)
    private String howRecurringByDay;
    
    @Basic(optional = false)
    @Column(name = "how_recurring_by_person", length = 1000)
    private String howRecurringByPerson;
    
    @Basic(optional = false)
    @Column(name = "how_recurring_by_location", length = 1000)
    private String howRecurringByLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getHowRecurringByDay() {
        return howRecurringByDay;
    }

    public void setHowRecurringByDay(String howRecurringByDay) {
        this.howRecurringByDay = howRecurringByDay;
    }

    public String getHowRecurringByPerson() {
        return howRecurringByPerson;
    }

    public void setHowRecurringByPerson(String howRecurringByPerson) {
        this.howRecurringByPerson = howRecurringByPerson;
    }

    public String getHowRecurringByLocation() {
        return howRecurringByLocation;
    }

    public void setHowRecurringByLocation(String howRecurringByLocation) {
        this.howRecurringByLocation = howRecurringByLocation;
    }
}
