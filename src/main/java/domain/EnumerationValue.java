package domain;

public class EnumerationValue extends Entity implements IHaveId
 {

	public int id;
	private int intKey;
	private String stringKey;
	private int value;
	private String enumerationName;
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIntKey() {
		return intKey;
	}
	public void setIntKey(int intKey) {
		this.intKey = intKey;
	}
	public String getStringKey() {
		return stringKey;
	}
	public void setStringKey(String stringKey) {
		this.stringKey = stringKey;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getEnumerationName() {
		return enumerationName;
	}
	public void setEnumerationName(String enumerationName) {
		this.enumerationName = enumerationName;
	}
	
	
}
