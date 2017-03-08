package lab3;

public abstract class Food {
	private int expirationDay;
	
	public Food(int day){
		setExpirationDay(day);
	}
	
	public void setExpirationDay(int date){
		expirationDay = date;
	}//end setExpirationDay
	
	public void passADay(){
		expirationDay--;
	}//end passADay
	
	public int getExpirationDay(){
		return expirationDay;
	}//end getExpirationDay
	
	public boolean isExpired(){
		return expirationDay <= 0;
	}//end isExpired	
}//end Food
