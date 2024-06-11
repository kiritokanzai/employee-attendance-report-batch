package one.bca.employee_attendance_report_batch.dto;

import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.Employee;

import java.util.List;

public class EmployeeAttendanceDataDto {
    private Employee employee;
    private List<Attendance> attendanceList;

    public EmployeeAttendanceDataDto(Employee employee, List<Attendance> attendanceList) {
        this.employee = employee;
        this.attendanceList = attendanceList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
