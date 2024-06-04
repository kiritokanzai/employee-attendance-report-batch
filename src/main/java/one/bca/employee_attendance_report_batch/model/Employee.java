package one.bca.employee_attendance_report_batch.model;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String category;
    private String division;
    private int paidLeaveLimit;
    private int usedPaidLeave;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getPaidLeaveLimit() {
        return paidLeaveLimit;
    }

    public void setPaidLeaveLimit(int paidLeaveLimit) {
        this.paidLeaveLimit = paidLeaveLimit;
    }

    public int getUsedPaidLeave() {
        return usedPaidLeave;
    }

    public void setUsedPaidLeave(int usedPaidLeave) {
        this.usedPaidLeave = usedPaidLeave;
    }

    @Override
    public String toString() {
        return "Employee[" +
                "employeeId=" + employeeId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", gender=" + gender +
                ", email=" + email +
                ", category=" + category +
                ", division=" + division +
                ", paidLeaveLimit=" + paidLeaveLimit +
                ", usedPaidLeave=" + usedPaidLeave +
                ']';
    }
}
