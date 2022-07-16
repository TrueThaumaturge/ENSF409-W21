import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyOutput {
	public static void main(String args[]) {	
		String[] exampleLog = exampleLog();

		var logfile = new ParseLogfile(exampleLog);
		var line = logfile.getLine(0);
		System.out.println("Log line 0: " + line.getLogLine());

		var ip = line.getIPv4();
		System.out.println("IPv4: "+ip.getIP());
		
		var dt = line.getDateTime();
		System.out.println("Day: "+dt.getDay());
		System.out.println("Month: "+dt.getMonth());
		System.out.println("Month (named): "+dt.monthToString());
		System.out.println("Year: "+dt.getYear());
		System.out.println("Hour: "+dt.getHour());
		System.out.println("Minute: "+dt.getMinute());
		System.out.println("Second: "+dt.getSecond());

		var act = line.getAction();
		System.out.println("Action: "+act.getAction());

		var dev = line.getDevice();
		System.out.println("Device: "+dev.getDevice());

		var loc = line.getLocation();
		System.out.println("Room: "+loc.getRoom());
		System.out.println("Building: "+loc.getBuilding());

		System.out.println();
		line = logfile.getLine(6);
		System.out.println("Log line 6: " + line.getLogLine());
		System.out.println(line.getIPv4().getFormatted());
		System.out.println(line.getDateTime().getFormatted());
		System.out.println(line.getAction().getFormatted());
		System.out.println(line.getDevice().getFormatted());
		System.out.println(line.getLocation().getFormatted());

		System.out.println("\nExample of toLog() output: " + Months.AUG.toLog());
		System.out.println("\nExample regex (for DateTime): "+dt.getRegex());
	}

	// Contains example data 
	public static String[] exampleLog() {
		String[] log = {
"81.220.24.207 - - [2/Mar/2020:10:05:44] \"END sprinkler (Visitor entrance - Building A)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:26] \"ENABLE cooling system (Secured room - Building A)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:39] \"START heating system (Hall - Central)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:52] \"ENABLE door lock (Visitor entrance - Building B)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:21] \"TEST cooling system (Entrance - Building B)\"",
"66.249.73.135 - - [17/May/2020:01:05:17] \"TEST fan (Secured room - Airport location)\"",
"46.105.14.53 - - [17/May/2020:11:05:42] \"TEST cooling system (Secured room - Airport location)\"",
"218.30.103.62 - - [17/May/2020:11:05:11] \"START sprinkler (Secured room - Airport location)\"",
"218.30.103.62 - - [17/May/2020:11:05:46] \"DISABLE fan (Control room - Central)\"",
"218.30.103.62 - - [17/May/2020:11:05:45] \"START door lock (Secured room - Building A)\"",
"66.249.73.135 - - [27/Jun/2020:11:05:31] \"TEST sprinkler (Hall - Building B)\""};
		return log;
	}
}

enum Actions{
	END,
	ENABLE,
	START,
	TEST,
	DISABLE
}

enum Months{
	JAN {
		public String toString() {
			return "January";
		}
		
		public int toInt() {
			return 1;
		}
		
		public String toLog() {
			return "Jan";
		}
	},
	FEB {
		public String toString() {
			return "February";
		}
		
		public int toInt() {
			return 2;
		}
		
		public String toLog() {
			return "Feb";
		}
	},
	MAR {
		public String toString() {
			return "March";
		}
		
		public int toInt() {
			return 3;
		}
		
		public String toLog() {
			return "Mar";
		}
	},
	APR {
		public String toString() {
			return "April";
		}
		
		public int toInt() {
			return 4;
		}
		
		public String toLog() {
			return "Apr";
		}
	},
	MAY {
		public String toString() {
			return "May";
		}
		
		public int toInt() {
			return 5;
		}
		
		public String toLog() {
			return "May";
		}
	},
	JUN {
		public String toString() {
			return "June";
		}
		
		public int toInt() {
			return 6;
		}
		
		public String toLog() {
			return "Jun";
		}
	},
	JUL {
		public String toString() {
			return "July";
		}
		
		public int toInt() {
			return 7;
		}
		
		public String toLog() {
			return "Jul";
		}
	},
	AUG {
		public String toString() {
			return "August";
		}
		
		public int toInt() {
			return 8;
		}
		
		public String toLog() {
			return "Aug";
		}
	},
	SEP {
		public String toString() {
			return "September";
		}
		
		public int toInt() {
			return 9;
		}
		
		public String toLog() {
			return "Sep";
		}
	},
	OCT {
		public String toString() {
			return "October";
		}
		
		public int toInt() {
			return 10;
		}
		
		public String toLog() {
			return "Oct";
		}
	},
	NOV {
		public String toString() {
			return "November";
		}
		
		public int toInt() {
			return 11;
		}
		
		public String toLog() {
			return "Nov";
		}
	},
	DEC {
		public String toString() {
			return "December";
		}
		
		public int toInt() {
			return 12;
		}
		
		public String toLog() {
			return "Dec";
		}
	};
	
	public abstract String toString();
	public abstract int toInt();
	public abstract String toLog();
}

interface FormattedOutput {
	String getFormatted();
}

class Device implements FormattedOutput {
	private final String DEVICE;
	private static final String REGEX = "[A-Z]{3,7}\\s([a-zA-Z\\s]{1,})\\s\\(";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	public Device(String device) {
		DEVICE = device.trim();
	}
	
	public String getFormatted() {
		return "Device: " + DEVICE;
	}
	public String getDevice() {
		return DEVICE;
	}
	public static String getRegex() {
		return REGEX;
	}
	public static Pattern getPattern() {
		return PATTERN;
	}
}

class IPv4 implements FormattedOutput {
	private final String IP;
	private static final String REGEX = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	public IPv4(String ip) {
		IP = ip.trim();
	}
	
	public String getFormatted() {
		return "IPv4: " + IP;
	}
	public String getIP() {
		return IP;
	}
	public static String getRegex() {
		return REGEX;
	}
	public static Pattern getPattern() {
		return PATTERN;
	}
}

class Action implements FormattedOutput {
	private final String ACTION;
	private static final String REGEX = "\\\"([A-Z]{3,7})\\s";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	public Action(String action) {
		ACTION = action.trim();
	}
	
	public String getFormatted() {
		return "Action: " + ACTION;
	}
	public String getAction() {
		return ACTION;
	}
	public static String getRegex() {
		return REGEX;
	}
	public static Pattern getPattern() {
		return PATTERN;
	}
}

class Location implements FormattedOutput {
	private final String ROOM;
	private final String BUILDING;
	private static final String REGEX = "\\(([a-zA-Z\\s]{1,})\\s-\\s([a-zA-Z\\s]{1,})\\)";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	public Location(String location) {
		Matcher locationMatcher = PATTERN.matcher(location);
		locationMatcher.find();
		ROOM = locationMatcher.group(1).trim();
		BUILDING = locationMatcher.group(2).trim();
	}
	
	public String getFormatted() {
		return "Room: " + ROOM + ", Building: " + BUILDING;
	}
	public String getRoom() {
		return ROOM;
	}
	public String getBuilding() {
		return BUILDING;
	}
	public static String getRegex() {
		return REGEX;
	}
	public static Pattern getPattern() {
		return PATTERN;
	}
}

//"81.220.24.207 - - [2/Mar/2020:10:05:44] \"END sprinkler (Visitor entrance - Building A)\""
class DateTime implements FormattedOutput {
	private final int DAY;
	private final int MONTH;
	private final int YEAR;
	private final int HOUR;
	private final int MINUTE;
	private final int SECOND;
	private static final String REGEX = "\\[([0-9]{1,2})/([a-zA-Z]{3})/([0-9]{4}):([0-9]{1,2}):([0-9]{2}):([0-9]{2})\\]";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	
	public DateTime(String datetime) {
		Matcher dateTimeMatcher = PATTERN.matcher(datetime);
		dateTimeMatcher.find();
		DAY = Integer.valueOf(dateTimeMatcher.group(1));
		int monthGuard = 1;
		for(Months month : Months.values()) {
			if(dateTimeMatcher.group(2).equals(month.toLog())) {
				monthGuard = month.toInt();
				break;
			}
		}
		MONTH = monthGuard;
		YEAR = Integer.valueOf(dateTimeMatcher.group(3));
		HOUR = Integer.valueOf(dateTimeMatcher.group(4));
		MINUTE = Integer.valueOf(dateTimeMatcher.group(5));
		SECOND = Integer.valueOf(dateTimeMatcher.group(6));
	}
	
	public String getFormatted() {
		String printedMonth = new String();
		for(Months month : Months.values()) {
			if(MONTH == month.ordinal() + 1) {
				printedMonth = month.toString();
			}
		}
		return "Day: " + DAY + ", Month: " + printedMonth + ", Year: " + YEAR + ", Hour: "
				+ HOUR + ", Minute: " + MINUTE + ", Second: " + SECOND;
	}
	public String monthToString() {
		for(Months month : Months.values()) {
			if(MONTH == month.ordinal() + 1) {
				return month.toString();
			}
		}
		return "";
	}
	
	public int getDay() {
		return DAY;
	}
	public int getMonth() {
		return MONTH;
	}
	public int getYear() {
		return YEAR;
	}
	public int getHour() {
		return HOUR;
	}
	public int getMinute() {
		return MINUTE;
	}
	public int getSecond() {
		return SECOND;
	}
	public static String getRegex() {
		return REGEX;
	}
	public static Pattern getPattern() {
		return PATTERN;
	}
}

//"81.220.24.207 - - [2/Mar/2020:10:05:44] \"END sprinkler (Visitor entrance - Building A)\""
class ParseLine {
	private final String LOGLINE;
	private final Location LOCATION;
	private final Device DEVICE;
	private final Action ACTION;
	private final DateTime DATETIME;
	private final IPv4 IPV4;
	
	public ParseLine(String line) {
		LOGLINE = line;
		
		Matcher locationMatcher = Location.getPattern().matcher(line);
		locationMatcher.find();
		LOCATION = new Location(locationMatcher.group(0));
		
		Matcher deviceMatcher = Device.getPattern().matcher(line);
		deviceMatcher.find();
		DEVICE = new Device(deviceMatcher.group(1));
		
		Matcher actionMatcher = Action.getPattern().matcher(line);
		actionMatcher.find();
		ACTION = new Action(actionMatcher.group(1));
		
		Matcher dateTimeMatcher = DateTime.getPattern().matcher(line);
		dateTimeMatcher.find();
		DATETIME = new DateTime(dateTimeMatcher.group(0));
		
		Matcher IPv4Matcher = IPv4.getPattern().matcher(line);
		IPv4Matcher.find();
		IPV4 = new IPv4(IPv4Matcher.group(0));
	}
	
	public IPv4 getIPv4() {
		return IPV4;
	}
	public String getLogLine() { 
		return LOGLINE;
	}
	public Location getLocation() {
		return LOCATION;
	}
	public Device getDevice() {
		return DEVICE;
	}
	public Action getAction() {
		return ACTION;
	}
	public DateTime getDateTime() {
		return DATETIME;
	}
}

class ParseLogfile {
	private ArrayList<ParseLine> log = new ArrayList<>();
	
	public ParseLogfile(String[] array) {
		for(String line_i : array) {
			ParseLine oneLine = new ParseLine(line_i);
			log.add(oneLine);
		}
	}
	
	public ParseLine getLine(int index) {
		return log.get(index);
	}
	public ArrayList<ParseLine> getLog() {
		return log;
	}
}