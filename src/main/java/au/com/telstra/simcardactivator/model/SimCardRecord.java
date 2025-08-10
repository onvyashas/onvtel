package au.com.telstra.simcardactivator.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "sim_card_record")
public class SimCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iccid;
    private String customerEmail;
    private boolean active;

    public SimCardRecord() {}

    public SimCardRecord(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getIccid() { return iccid; }
    public void setIccid(String iccid) { this.iccid = iccid; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
