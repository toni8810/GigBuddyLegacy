package org.gigbuddy.tshirt.promotion;

public interface TempTshirtPromotion {
	//check if the id is divisible by 7 and if so puts the user into the tshirt table
	public void checkUserId(String email);
}
