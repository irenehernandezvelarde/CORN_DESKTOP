import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private StringProperty firstName;

	private StringProperty lastName;

    private IntegerProperty phone;

    private StringProperty email;

    private DoubleProperty balance;

    public User(String firstName, String lastName,int phone, String email, Double balance) {
		super();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone=new SimpleIntegerProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.balance = new SimpleDoubleProperty(balance);
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

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email){
        this.email.set(email);
    }

    public Double getBalance() {
        return balance.get();
    }

    public void setBalance(Double balance) {
        this.balance.set(balance);
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

    public StringProperty emailProperty() {
        return this.email;
    }

    public DoubleProperty balancProperty() {
        return this.balance;
    }
}
