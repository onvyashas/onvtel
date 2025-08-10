package au.com.telstra.simcardactivator.dto;

public class SimRequest {
    private String iccid;
    private String customerEmail;

    public SimRequest() {}
    public String getIccid() { return iccid; }
    public void setIccid(String iccid) { this.iccid = iccid; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
}
