package gr.demokritos.meetingscheduler.business.dto;

import java.time.LocalDate;

public class WeekDto {
    private LocalDate startDate;
    private LocalDate endDate;

    public WeekDto() {

    }

    public WeekDto(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
