package one.bca.employee_attendance_report_batch.model;

import lombok.Data;

@Data
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
}
