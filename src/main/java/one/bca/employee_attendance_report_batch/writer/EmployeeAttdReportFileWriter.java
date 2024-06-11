package one.bca.employee_attendance_report_batch.writer;

import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceDataDto;
import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceReportDto;
import one.bca.employee_attendance_report_batch.model.Attendance;
import one.bca.employee_attendance_report_batch.model.EmployeeAttendance;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAttdReportFileWriter implements ItemWriter<EmployeeAttendance> {
    private static String[] names = new String[] {
            "employee_id",
            "first_name",
            "last_name",
            "email",
            "category",
            "division",
            "total_attend_days",
            "total_late_days",
            "total_overtime_hours",
            "total_paid_leave_days",
            "paid_leave_limit_remaining"
    };

//    private void createReportFile() {
//        FlatFileItemWriter<EmployeeAttendanceReportDto> itemWriter = new FlatFileItemWriter<>();
//
//        itemWriter.setResource(new FileSystemResource("data/employee_attendance_monthly_report.csv"));
//
//        DelimitedLineAggregator<EmployeeAttendanceReportDto> aggregator = new DelimitedLineAggregator<>();
//        aggregator.setDelimiter(",");
//
//        BeanWrapperFieldExtractor<EmployeeAttendanceReportDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
//        fieldExtractor.setNames(names);
//        aggregator.setFieldExtractor(fieldExtractor);
//
//        itemWriter.setHeaderCallback(writer -> writer.write(String.join(",", names)));
//        itemWriter.setLineAggregator(aggregator);
//    }

    @Override
    public void write(Chunk<? extends EmployeeAttendance> chunk) throws Exception {
        List<EmployeeAttendanceDataDto> employeeAttendanceDataDtoList = new ArrayList<>();
        List<EmployeeAttendance> items = (List<EmployeeAttendance>) chunk.getItems();

        // map to list of EmployeeAttendanceDataDto
        items.forEach (x -> {
            if (employeeAttendanceDataDtoList.isEmpty()) {
                List<Attendance> attendanceList = new ArrayList<>();
                attendanceList.add(x.getAttendance());

                employeeAttendanceDataDtoList.add(new EmployeeAttendanceDataDto(x.getEmployee(), attendanceList));
            } else if (employeeAttendanceDataDtoList.getLast().getEmployee().getEmployeeId() == x.getEmployee().getEmployeeId()) {
                EmployeeAttendanceDataDto last = employeeAttendanceDataDtoList.getLast();
                last.getAttendanceList().add(x.getAttendance());
            } else {
                List<Attendance> attendanceList = new ArrayList<>();
                attendanceList.add(x.getAttendance());

                employeeAttendanceDataDtoList.add(new EmployeeAttendanceDataDto(x.getEmployee(), attendanceList));
            }
        });

        // create report

    }
}
