package db;

public class Nurse {

    private String payrollId;
    private String username;
    private String email;
    private Role role;

    public Nurse(String payrollId, String username, String email, Role role) {
        this.payrollId = payrollId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
