package one.bca.employee_attendance_report_batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.Employee;

import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeAttendanceDataDto {
    private Employee employee;
    private List<Attendance> attendanceList;
}
