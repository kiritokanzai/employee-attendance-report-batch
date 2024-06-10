package one.bca.employee_attendance_report_batch.writer;

import one.bca.employee_attendance_report_batch.dto.EmployeeAttendanceReport;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class EmployeeAttdReportFileWriter {
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

    public ItemWriter<EmployeeAttendanceReport> itemWriter() {
        FlatFileItemWriter<EmployeeAttendanceReport> itemWriter = new FlatFileItemWriter<>();

        itemWriter.setResource(new FileSystemResource("data/employee_attendance_monthly_report.csv"));

        DelimitedLineAggregator<EmployeeAttendanceReport> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<EmployeeAttendanceReport> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(names);
        aggregator.setFieldExtractor(fieldExtractor);

        itemWriter.setHeaderCallback(writer -> writer.write(String.join(",", names)));
        itemWriter.setLineAggregator(aggregator);
        return itemWriter;
    }
}
