package enums;

public enum WebDriverType {
	
	CHROME("chrome"), FIREFOX("firefox"), IE("ie");

	WebDriverType(String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}
	
}
