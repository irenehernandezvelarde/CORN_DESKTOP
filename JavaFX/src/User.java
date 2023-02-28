import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private StringProperty firstName;

	private StringProperty lastName;

    private IntegerProperty phone;

    public User(String firstName, String lastName,int phone) {
		super();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone=new SimpleIntegerProperty(phone);
	}

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public int getPhone() {
        return phone.get();
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public StringProperty firstNameProperty() {
		return this.firstName;
	}
	public StringProperty lastNameProperty() {
		return this.lastName;
	}
    public IntegerProperty phoneProperty(){
        return this.phone;
    }

    
}
