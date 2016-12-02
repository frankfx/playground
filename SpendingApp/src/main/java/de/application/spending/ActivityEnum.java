package de.application.spending;

public enum ActivityEnum {
	EMPTY, SPORT, FOOD, LEISURE_TIME, HOLIDAY, OTHERS;
	
	public static ActivityEnum getType(Object str){
		ActivityEnum activity = (ActivityEnum) str;
		if (activity == null)
			return ActivityEnum.EMPTY;
		return activity;
	}	
	
	public static ActivityEnum getType(String str){
		if(str == null)
			return ActivityEnum.EMPTY;
		else if (str.equalsIgnoreCase(ActivityEnum.SPORT.toString()))
			return ActivityEnum.SPORT;
		else if (str.equalsIgnoreCase(ActivityEnum.FOOD.toString()))
			return ActivityEnum.FOOD;
		else if (str.equalsIgnoreCase(ActivityEnum.LEISURE_TIME.toString()))
			return ActivityEnum.LEISURE_TIME;
		else if (str.equalsIgnoreCase(ActivityEnum.HOLIDAY.toString()))
			return ActivityEnum.HOLIDAY;
		else if (str.equalsIgnoreCase(ActivityEnum.OTHERS.toString()))
			return ActivityEnum.OTHERS;
		else return ActivityEnum.EMPTY;
	}
	
	@Override
	public String toString(){
		return this.name();
	}
}