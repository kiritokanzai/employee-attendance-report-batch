package one.bca.employee_attendance_report_batch.writer;

import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceDataDto;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class EmployeeAttdReportFileWriter implements ItemWriter<EmployeeAttendanceDataDto> {
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
    public void write(Chunk<? extends EmployeeAttendanceDataDto> chunk) throws Exception {

    }
}
