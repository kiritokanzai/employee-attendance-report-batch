package one.bca.employee_attendance_report_batch.configuration;

import one.bca.employee_attendance_report_batch.model.Employee;
import one.bca.employee_attendance_report_batch.reader.EmployeeReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EmployeeDataStep {
    private final JobRepository jobRepository;
    private final DataSourceTransactionManager transactionManager;
    private final EmployeeReader reader;

    public EmployeeDataStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, EmployeeReader reader) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
    }

    public Step getStep() {
        return new StepBuilder("employeeDataStep", jobRepository)
                .<Employee, Employee>chunk(50, transactionManager)
                .reader(reader.itemReader())
                .processor(new ItemProcessor<Employee, Employee>() {
                    @Override
                    public Employee process(Employee item) throws Exception {
                        ExecutionContext stepContext = StepSynchronizationManager.getContext()
                                .getStepExecution().getJobExecution().getExecutionContext();
                        List<Employee> processedEmployee = (List<Employee>) stepContext.get("employeeList");
                        if(processedEmployee == null) {
                            processedEmployee = new ArrayList<>();
                            processedEmployee.add(item);
                        }
                        processedEmployee.add(item);

                        stepContext.put("employeeList", processedEmployee);
                        return item;
                    }
                })
                .build();
    }
}
