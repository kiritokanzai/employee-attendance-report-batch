package one.bca.employee_attendance_report_batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
}
