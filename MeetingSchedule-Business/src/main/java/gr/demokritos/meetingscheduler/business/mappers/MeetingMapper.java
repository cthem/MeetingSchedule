package gr.demokritos.meetingscheduler.business.mappers;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingMemberDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingMemberDto;
import gr.demokritos.meetingscheduler.business.dto.TimezoneDto;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Availability;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Day;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Meeting;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.MeetingMember;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Member;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeetingMember;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Timezone;

public class MeetingMapper {

	public MeetingDto convertMeetingToMeetingDto(Meeting meeting) {
		if (meeting == null)
			return null;
		MeetingDto meetingDto = new MeetingDto();
		meetingDto.setId(meeting.getId());
		meetingDto.setCompleted(meeting.getCompleted());
		meetingDto.setDate(meeting.getDate());
		meetingDto.setEndTime(meeting.getEndTime());
		meetingDto.setStartTime(meeting.getStartTime());
		meetingDto.setName(meeting.getName());
		if (!CollectionUtils.isEmpty(meeting.getMembers())) {
			meeting.getMembers().forEach(meetingMember -> meetingDto
					.addMeetingMemberDto(convertMeetingMemberToMeetingMemberDto(meetingMember)));
		}
		if (!CollectionUtils.isEmpty(meeting.getPossibleMeetings())) {
			meeting.getPossibleMeetings().forEach(possibleMeeting -> meetingDto
					.addPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeeting)));
		}
		return meetingDto;
	}

	public Meeting convertMeetingDtoToMeeting(MeetingDto meetingDto) {
		if (meetingDto == null)
			return null;
		Meeting meeting = new Meeting();
		meeting.setId(meetingDto.getId());
		meeting.setCompleted(meetingDto.getCompleted());
		meeting.setDate(meetingDto.getDate());
		meeting.setEndTime(meetingDto.getEndTime());
		meeting.setStartTime(meetingDto.getStartTime());
		meeting.setName(meetingDto.getName());
		if (!CollectionUtils.isEmpty(meetingDto.getMeetingMemberDtos())) {
			meetingDto.getMeetingMemberDtos().forEach(meetingMemberDto -> meeting
					.addMeetingMember(convertMeetingMemberDtoToMeetingMember(meetingMemberDto)));
		}
		if (!CollectionUtils.isEmpty(meetingDto.getPossibleMeetingDtos())) {
			meetingDto.getPossibleMeetingDtos().forEach(possibleMeetingDto -> meeting
					.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto)));
		}
		return meeting;
	}

	private MeetingMemberDto convertMeetingMemberToMeetingMemberDto(MeetingMember meetingMember) {
		if (meetingMember == null)
			return null;
		MeetingMemberDto meetingMemberDto = new MeetingMemberDto();
		meetingMemberDto.setId(meetingMember.getId());
		meetingMemberDto.setAttended(meetingMember.getAttended());
		meetingMemberDto.setMemberDto(convertMemberToMemberDto(meetingMember.getMember()));
		return meetingMemberDto;
	}

	private MeetingMember convertMeetingMemberDtoToMeetingMember(MeetingMemberDto meetingMemberDto) {
		if (meetingMemberDto == null)
			return null;
		MeetingMember meetingMember = new MeetingMember();
		meetingMember.setId(meetingMemberDto.getId());
		meetingMember.setAttended(meetingMemberDto.getAttended());
		meetingMember.setMember(convertMemberDtoToMember(meetingMemberDto.getMemberDto()));
		return meetingMember;
	}

	private PossibleMeetingDto convertPossibleMeetingToPossibleMeetingDto(PossibleMeeting possibleMeeting) {
		if (possibleMeeting == null)
			return null;
		PossibleMeetingDto possibleMeetingDto = new PossibleMeetingDto();
		possibleMeetingDto.setId(possibleMeeting.getId());
		possibleMeetingDto.setDayDto(convertDayToDayDto(possibleMeeting.getDay()));
		possibleMeetingDto.setTimezoneDto(convertTimezoneToTimezoneDto(possibleMeeting.getTimezone()));
		if (!CollectionUtils.isEmpty(possibleMeeting.getPossibleMeetingMembers())) {
			possibleMeeting.getPossibleMeetingMembers()
					.forEach(possibleMeetingMember -> possibleMeetingDto.addPossibleMeetingMemberDto(
							convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember)));
		}
		return possibleMeetingDto;
	}

	private PossibleMeeting convertPossibleMeetingDtoToPossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
		if (possibleMeetingDto == null)
			return null;
		PossibleMeeting possibleMeeting = new PossibleMeeting();
		possibleMeeting.setId(possibleMeetingDto.getId());
		possibleMeeting.setDay(convertDayDtoToDay(possibleMeetingDto.getDayDto()));
		possibleMeeting.setTimezone(convertTimezoneDtoToTimezone(possibleMeetingDto.getTimezoneDto()));
		if (!CollectionUtils.isEmpty(possibleMeetingDto.getPossibleMeetingMemberDtos())) {
			possibleMeetingDto.getPossibleMeetingMemberDtos()
					.forEach(possibleMeetingMemberDto -> possibleMeeting.addPossibleMeetingMember(
							convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
		}
		return possibleMeeting;
	}

	private MemberDto convertMemberToMemberDto(Member member) {
		if (member == null)
			return null;
		MemberDto memberDto = new MemberDto();
		memberDto.setId(member.getId());
		memberDto.setName(member.getName());
		memberDto.setLastName(member.getLastName());
		if (!CollectionUtils.isEmpty(member.getAvailabilities())) {
			member.getAvailabilities().forEach(
					availability -> memberDto.addAvailabilityDto(convertAvailabilityToAvailabilityDto(availability)));
		}
		if (!CollectionUtils.isEmpty(member.getPossibleMeetingMembers())) {
			member.getPossibleMeetingMembers().forEach(possibleMeetingMember -> memberDto.addPossibleMeetingMemberDto(
					convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember)));
		}
		return memberDto;
	}

	private Member convertMemberDtoToMember(MemberDto memberDto) {
		if (memberDto == null)
			return null;
		Member member = new Member();
		member.setId(memberDto.getId());
		member.setName(memberDto.getName());
		member.setLastName(memberDto.getLastName());
		if (!CollectionUtils.isEmpty(memberDto.getAvailabilityDtos())) {
			memberDto.getAvailabilityDtos().forEach(
					availabilityDto -> member.addAvailability(convertAvailabilityDtoToAvailability(availabilityDto)));
		}
		if (!CollectionUtils.isEmpty(memberDto.getPossibleMeetingMemberDtos())) {
			memberDto.getPossibleMeetingMemberDtos()
					.forEach(possibleMeetingMemberDto -> member.addPossibleMeetingMember(
							convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
		}
		return member;
	}

	private DayDto convertDayToDayDto(Day day) {
		if (day == null)
			return null;
		DayDto dayDto = new DayDto();
		dayDto.setId(day.getId());
		dayDto.setDate(day.getDate());
		if(day.getDate()!=null) {
			dayDto.setDayOfWeek(day.getDate().getDayOfWeek());
			dayDto.setName(dayDto.getDayOfWeek().toString());
		}
		if (!CollectionUtils.isEmpty(day.getAvailabilities())) {
			day.getAvailabilities().forEach(
					availability -> dayDto.addAvailabilityDto(convertAvailabilityToAvailabilityDto(availability)));
		}
		if (!CollectionUtils.isEmpty(day.getPossibleMeetings())) {
			day.getPossibleMeetings().forEach(possibleMeeting -> dayDto
					.addPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeeting)));
		}
		return dayDto;
	}

	private Day convertDayDtoToDay(DayDto dayDto) {
		if (dayDto == null)
			return null;
		Day day = new Day();
		day.setId(dayDto.getId());
		day.setDate(dayDto.getDate());
		day.setName(dayDto.getName());
		if (!CollectionUtils.isEmpty(dayDto.getAvailabilityDtos())) {
			dayDto.getAvailabilityDtos().forEach(
					availabilityDto -> day.addAvailability(convertAvailabilityDtoToAvailability(availabilityDto)));
		}
		if (!CollectionUtils.isEmpty(dayDto.getPossibleMeetingsDto())) {
			dayDto.getPossibleMeetingsDto().forEach(possibleMeetingDto -> day
					.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto)));
		}
		return day;
	}

	private TimezoneDto convertTimezoneToTimezoneDto(Timezone timezone) {
		if(timezone ==null) return null;
		TimezoneDto timezoneDto = new TimezoneDto();
		timezoneDto.setId(timezone.getId());
		timezoneDto.setEndTime(timezone.getEndTime());
		timezoneDto.setStartTime(timezone.getStartTime());
		if (!CollectionUtils.isEmpty(timezone.getAvailabilities())) {
			timezone.getAvailabilities().forEach(
					availability -> timezoneDto.addAvailabilityDto(convertAvailabilityToAvailabilityDto(availability)));
		}
		return timezoneDto;
	}

	private Timezone convertTimezoneDtoToTimezone(TimezoneDto timezoneDto) {
		if(timezoneDto == null) return null;
		Timezone timezone = new Timezone();
		timezone.setId(timezoneDto.getId());
		timezone.setEndTime(timezoneDto.getEndTime());
		timezone.setStartTime(timezoneDto.getStartTime());
		if (!CollectionUtils.isEmpty(timezoneDto.getAvailabilityDtos())) {
			timezoneDto.getAvailabilityDtos().forEach(
					availabilityDto -> timezone.addAvailability(convertAvailabilityDtoToAvailability(availabilityDto)));
		}
		return timezone;
	}

	private PossibleMeetingMemberDto convertPossibleMeetingMemberToPossibleMeetingMemberDto(
			PossibleMeetingMember possibleMeetingMember) {
		if(possibleMeetingMember == null) return null;
		PossibleMeetingMemberDto possibleMeetingMemberDto = new PossibleMeetingMemberDto();
		possibleMeetingMemberDto.setId(possibleMeetingMember.getId());
		possibleMeetingMemberDto.setAttending(possibleMeetingMember.getAttending());
		return possibleMeetingMemberDto;
	}

	private PossibleMeetingMember convertPossibleMeetingMemberDtoToPossibleMeetingMember(
			PossibleMeetingMemberDto possibleMeetingMemberDto) {
		if(possibleMeetingMemberDto == null) return null;
		PossibleMeetingMember possibleMeetingMember = new PossibleMeetingMember();
		possibleMeetingMember.setId(possibleMeetingMemberDto.getId());
		possibleMeetingMember.setAttending(possibleMeetingMemberDto.getAttending());
		return possibleMeetingMember;
	}

	private AvailabilityDto convertAvailabilityToAvailabilityDto(Availability availability) {
		if(availability == null) return null;
		AvailabilityDto availabilityDto = new AvailabilityDto();
		availabilityDto.setId(availability.getId());
		availabilityDto.setIsAvailabile(availability.getAvailability());
		return availabilityDto;
	}

	private Availability convertAvailabilityDtoToAvailability(AvailabilityDto availabilityDto) {
		if(availabilityDto == null) return null;
		Availability availability = new Availability();
		availability.setId(availabilityDto.getId());
		availability.setAvailability(availabilityDto.getIsAvailable());
		return availability;
	}

}
