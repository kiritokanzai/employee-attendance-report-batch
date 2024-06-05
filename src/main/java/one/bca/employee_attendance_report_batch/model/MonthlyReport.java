package one.bca.employee_attendance_report_batch.model;

public class MonthlyReport {
    private String employeeId;
    private String name;
    private String division;
    private int totalAttendance;
    private int totalOvertimeHours;
    private int totalPaidLeave;
    private int paidLeaveRemaining;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(int totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public int getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(int totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public int getTotalPaidLeave() {
        return totalPaidLeave;
    }

    public void setTotalPaidLeave(int totalPaidLeave) {
        this.totalPaidLeave = totalPaidLeave;
    }

    public int getPaidLeaveRemaining() {
        return paidLeaveRemaining;
    }

    public void setPaidLeaveRemaining(int paidLeaveRemaining) {
        this.paidLeaveRemaining = paidLeaveRemaining;
    }
}
