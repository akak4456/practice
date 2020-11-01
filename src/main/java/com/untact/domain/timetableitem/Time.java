package com.untact.domain.timetableitem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Time {
	private int hour;
	private int minute;
	public boolean isLessThan(Time target){
        if(this.hour < target.hour){
            return true;
        }else if(this.hour == target.hour){
            return this.minute < target.minute;
        }else{
            return false;
        }
    }
}
