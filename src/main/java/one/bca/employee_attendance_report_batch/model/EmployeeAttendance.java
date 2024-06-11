package one.bca.employee_attendance_report_batch.model;

public class EmployeeAttendance {
    private Employee employee;
    private Attendance attendance;

    public EmployeeAttendance(Employee employee, Attendance attendance) {
        this.employee = employee;
        this.attendance = attendance;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
