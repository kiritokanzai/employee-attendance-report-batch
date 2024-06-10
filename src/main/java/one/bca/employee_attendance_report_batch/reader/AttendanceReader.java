package one.bca.employee_attendance_report_batch.reader;


import one.bca.employee_attendance_report_batch.mapper.AttendanceFieldMapper;
import one.bca.employee_attendance_report_batch.model.Attendace;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class AttendanceReader {
    public static String[] tokens = new String[]{ "employee_id", "date", "clock_in", "clock_out", "status_attendance", "overtime_status", "overtime_start", "overtime_end" };

    @Autowired
    private AttendanceFieldMapper mapper;

    @Value("classpath:data/employee_attendance_data.csv")
    private Resource resource;


    public ItemReader<Attendace> itemReader() {
        FlatFileItemReader<Attendace> itemReader = new FlatFileItemReader<Attendace>();
        itemReader.setLinesToSkip(1);
        itemReader.setResource(resource);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(tokens);
        tokenizer.setDelimiter(";");

        DefaultLineMapper<Attendace> lineMapper = new DefaultLineMapper<Attendace>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        itemReader.setLineMapper(lineMapper);
        return itemReader;

    }
}
