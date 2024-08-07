package uz.pdp.domains.routeandqueue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomAndDoctor {
    private int roomanddoctorid;
    private int authuserdoctorid;
    private LocalTime startwork;
    private LocalTime finishwork;
}