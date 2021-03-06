package gr.demokritos.meetingscheduler.business.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PossibleMeetingDto extends ParentDto {
    private Long id;
    private MeetingDto meetingDto;
    private DayDto dayDto;
    private TimezoneDto timezoneDto;
    private List<PossibleMeetingMemberDto> possibleMeetingMemberDtos;

    @NotForMapping
    private Set<MemberDto> canAttendList = new HashSet<>();
    @NotForMapping
    private Set<MemberDto> cannotAttendList = new HashSet<>();
    @NotForMapping
    private List<PossibleMeetingDto> lessPossibleMeetingsSameDay = new ArrayList<>();

    @NotForMapping
    private Integer canAttend;
    @NotForMapping
    private Integer cannotAttend;

    public PossibleMeetingDto() {

    }

    public PossibleMeetingDto(Long id, MeetingDto meetingDto, DayDto dayDto, TimezoneDto timezoneDto,
                              List<PossibleMeetingMemberDto> possibleMeetingMemberDtos) {
        super();
        this.id = id;
        this.meetingDto = meetingDto;
        this.dayDto = dayDto;
        this.timezoneDto = timezoneDto;
        this.possibleMeetingMemberDtos = possibleMeetingMemberDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingDto getMeetingDto() {
        return meetingDto;
    }

    public void setMeetingDto(MeetingDto meetingDto) {
        if (this.meetingDto != null) {
            this.meetingDto.internalRemovePossibleMeetingDto(this);
        }
        this.meetingDto = meetingDto;
        if (meetingDto != null) {
            meetingDto.internalAddPossibleMeetingDto(this);
        }
    }

    public DayDto getDayDto() {
        return dayDto;
    }

    public void setDayDto(DayDto dayDto) {
        if (this.dayDto != null) {
            this.dayDto.internalRemovePossibleMeetingDto(this);
        }
        this.dayDto = dayDto;
        if (dayDto != null) {
            dayDto.internalAddPossibleMeetingDto(this);
        }
    }

    public TimezoneDto getTimezoneDto() {
        return timezoneDto;
    }

    public void setTimezoneDto(TimezoneDto timezoneDto) {
        if (this.timezoneDto != null) {
            this.timezoneDto.internalRemovePossibleMeetingDto(this);
        }
        this.timezoneDto = timezoneDto;
        if (timezoneDto != null) {
            timezoneDto.internalAddPossibleMeetingDto(this);
        }
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMemberDtos() {
        return possibleMeetingMemberDtos;
    }

    public void setPossibleMeetingMemberDtos(List<PossibleMeetingMemberDto> possibleMeetingMemberDtos) {
        this.possibleMeetingMemberDtos = possibleMeetingMemberDtos;
    }

    public void addPossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        possibleMeetingMemberDto.setPossibleMeetingDto(this);
    }

    public void removePossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        possibleMeetingMemberDto.setPossibleMeetingDto(null);
    }

    public void internalAddPossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        this.possibleMeetingMemberDtos.add(possibleMeetingMemberDto);
    }

    public void internalRemovePossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        this.possibleMeetingMemberDtos.remove(possibleMeetingMemberDto);
    }

    public Integer getCanAttend() {
        return canAttend;
    }

    public void setCanAttend(Integer canAttend) {
        this.canAttend = canAttend;
    }

    public Integer getCannotAttend() {
        return cannotAttend;
    }

    public void setCannotAttend(Integer cannotAttend) {
        this.cannotAttend = cannotAttend;
    }

    public Set<MemberDto> getCanAttendList() {
        return canAttendList;
    }

    public void setCanAttendList(Set<MemberDto> canAttendList) {
        this.canAttendList = canAttendList;
    }

    public Set<MemberDto> getCannotAttendList() {
        return cannotAttendList;
    }

    public void setCannotAttendList(Set<MemberDto> cannotAttendList) {
        this.cannotAttendList = cannotAttendList;
    }

    public List<PossibleMeetingDto> getLessPossibleMeetingsSameDay() {
        return lessPossibleMeetingsSameDay;
    }

    public void setLessPossibleMeetingsSameDay(List<PossibleMeetingDto> lessPossibleMeetingsSameDay) {
        this.lessPossibleMeetingsSameDay = lessPossibleMeetingsSameDay;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PossibleMeetingDto other = (PossibleMeetingDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PossibleMeetingDto [id=" + id + ", meetingDto=" + meetingDto.toString() + ", dayDto=" + dayDto.toString() + ", timezoneDto="
                + timezoneDto.toString() + "]";
    }

}
