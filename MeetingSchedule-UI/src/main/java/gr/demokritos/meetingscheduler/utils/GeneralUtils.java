package gr.demokritos.meetingscheduler.utils;

import com.vaadin.server.VaadinSession;
import gr.demokritos.meetingscheduler.business.dto.TimezoneDto;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralUtils {

    public static final String TIME24HFORMAT                    = "HH:mm";
    public static final String SESSION_USER 					= "user";

    /** Logo and sublogo **/
    public static final String APP_LOGO						    = "Meeting Scheduler";
    public static final String APP_SUB_LOGO					    = "Easy way to handle meetings";

    /** Login page**/
    public static final String SIGN_IN 						    = "Sign in";
    public static final String USERNAME 						= "Username";
    public static final String PASSWORD 						= "Password";

    /** Menu bars **/
    public static final String LOGOUT							= "Sign out";
    public static final String MEMBERS						    = "Members";
    public static final String USERS							= "Users";
    public static final String AVAILABILITIES                   = "Availabilities";
    public static final String MEETINGS                         = "Meetings";
    public static final String PROFILE						    = "Profile";
    public static final String HELP							    = "Help";

    /** Images & Icon paths **/
    public static final String LOGO_ICON_PATH					= "images/calendar.png";
    public static final String ERROR_ICON_PATH				    = "icons/error_icon.svg";
    public static final String INFO_ICON_PATH					= "icons/information_icon.svg";
    public static final String WARN_ICON_PATH					= "icons/warning_icon.svg";
    public static final String SUCCESS_ICON_PATH				= "icons/confirmation_icon.svg";
    public static final String CLOSE_ICON_PATH				    = "icons/close_exit_icon.svg";

    public static UserDto getUserFromSession() {
        return (UserDto) VaadinSession.getCurrent().getAttribute(SESSION_USER);
    }

    public static Pair<List<String>, List<String>> getTimezonesForComboboxes(List<TimezoneDto> timezones) {
        if (!CollectionUtils.isEmpty(timezones)) {
            List<String> startTimeList = new ArrayList<>();
            List<String> endTimeList = new ArrayList<>();
            for (TimezoneDto timezone : timezones) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT);
                if (timezone.getStartTime() != null) {
                    startTimeList.add(timezone.getStartTime().format(formatter));
                }
                if (timezone.getEndTime() != null) {
                    endTimeList.add(timezone.getEndTime().format(formatter));
                }
            }
            startTimeList = startTimeList.stream().distinct().collect(Collectors.toList());
            endTimeList = endTimeList.stream().distinct().collect(Collectors.toList());
            startTimeList.sort((st1, st2) -> {
                LocalTime stlt1 = LocalTime.parse(st1);
                LocalTime stlt2 = LocalTime.parse(st2);
                return stlt1.compareTo(stlt2);
            });

            endTimeList.sort((st1, st2) -> {
                LocalTime stlt1 = LocalTime.parse(st1);
                LocalTime stlt2 = LocalTime.parse(st2);
                return stlt1.compareTo(stlt2);
            });
            return Pair.of(startTimeList, endTimeList);
        }
        return null;
    }

}
