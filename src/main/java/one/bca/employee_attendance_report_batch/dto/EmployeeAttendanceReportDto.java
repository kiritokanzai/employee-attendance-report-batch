package one.bca.employee_attendance_report_batch.dto;

public class EmployeeAttendanceReportDto {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String category;
    private String division;
    private Integer total_attend_days;
    private Integer total_late_days;
    private Integer total_overtime_hours;
    private Integer total_paid_leave_days;
    private Integer paid_leave_limit_remaining;

    public EmployeeAttendanceReportDto(String employeeId, String firstName, String lastName, String email, String category, String division, Integer total_attend_days, Integer total_late_days, Integer total_overtime_hours, Integer total_paid_leave_days, Integer paid_leave_limit_remaining) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
        this.division = division;
        this.total_attend_days = total_attend_days;
        this.total_late_days = total_late_days;
        this.total_overtime_hours = total_overtime_hours;
        this.total_paid_leave_days = total_paid_leave_days;
        this.paid_leave_limit_remaining = paid_leave_limit_remaining;
    }

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

    public Integer getTotal_attend_days() {
        return total_attend_days;
    }

    public void setTotal_attend_days(Integer total_attend_days) {
        this.total_attend_days = total_attend_days;
    }

    public Integer getTotal_late_days() {
        return total_late_days;
    }

    public void setTotal_late_days(Integer total_late_days) {
        this.total_late_days = total_late_days;
    }

    public Integer getTotal_overtime_hours() {
        return total_overtime_hours;
    }

    public void setTotal_overtime_hours(Integer total_overtime_hours) {
        this.total_overtime_hours = total_overtime_hours;
    }

    public Integer getTotal_paid_leave_days() {
        return total_paid_leave_days;
    }

    public void setTotal_paid_leave_days(Integer total_paid_leave_days) {
        this.total_paid_leave_days = total_paid_leave_days;
    }

    public Integer getPaid_leave_limit_remaining() {
        return paid_leave_limit_remaining;
    }

    public void setPaid_leave_limit_remaining(Integer paid_leave_limit_remaining) {
        this.paid_leave_limit_remaining = paid_leave_limit_remaining;
    }
}
