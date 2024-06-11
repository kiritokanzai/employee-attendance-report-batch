package one.bca.employee_attendance_report_batch.reader;


import one.bca.employee_attendance_report_batch.mapper.AttendanceFieldMapper;
import one.bca.employee_attendance_report_batch.model.Attendance;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class AttendanceReader {
    public static String[] tokens = new String[]{
            "employee_id", "date", "clock_in", "clock_out", "attendance_status", "overtime_status", "overtime_start", "overtime_end" };

    @Value("classpath:data/employee_attendance_data.csv")
    private Resource resource;


    public ItemReader<Attendance> itemReader() {
        FlatFileItemReader<Attendance> itemReader = new FlatFileItemReader<Attendance>();
        itemReader.setLinesToSkip(1);
        itemReader.setResource(resource);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(tokens);
        tokenizer.setDelimiter(",");

        DefaultLineMapper<Attendance> lineMapper = new DefaultLineMapper<Attendance>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new AttendanceFieldMapper());

        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }
}
