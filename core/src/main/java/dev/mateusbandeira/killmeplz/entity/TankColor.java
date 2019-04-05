package dev.mateusbandeira.killmeplz.entity;

public enum TankColor {
	YELLOW("tanks_tankDesert2.png"), GREEN("tanks_tankGreen2.png"), GREY("tanks_tankGrey2.png");
	private TankColor(String sprite) {
		this.sprite = sprite;
	}
	
	public final String sprite;
}
