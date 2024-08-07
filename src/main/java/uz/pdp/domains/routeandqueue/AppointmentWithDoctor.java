package uz.pdp.domains.routeandqueue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentWithDoctor {
    private int appointmentwithdoctorid;
    private int roomanddoctorid;
    private int authuserpatientid;
    private int sequencenumber;
    private LocalDateTime estimatedqueuetime;
}