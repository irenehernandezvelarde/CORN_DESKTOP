import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private StringProperty origin;

    private StringProperty destiny;

    private IntegerProperty accepted;

    private DoubleProperty quantity;

    private StringProperty acceptDate;

    public Transaction(String origin,String destiny, String acceptDate,int accepted, Double quantity) {
		super();
		this.origin = new SimpleStringProperty(origin);
        this.destiny = new SimpleStringProperty(destiny);
		this.acceptDate = new SimpleStringProperty(acceptDate);
		this.accepted=new SimpleIntegerProperty(accepted);
        this.quantity = new SimpleDoubleProperty(quantity);
	}

    public String getOrigin() {
        return origin.get();
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public String getDestiny(){
        return destiny.get();
    }

    public void setDestiny(String destiny){
        this.destiny.set(destiny);
    }

    public String getAcceptDate() {
        return acceptDate.get();
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate.set(acceptDate);
    }

    public int getAccepted() {
        return accepted.get();
    }

    public void setAccepted(int accepted) {
        this.accepted.set(accepted);
    }

    public Double getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Double quantity) {
        this.quantity.set(quantity);
    }

    public StringProperty originProperty() {
		return this.origin;
	}
    public StringProperty destinyProperty() {
		return this.destiny;
	}
	public StringProperty acceptDateProperty() {
		return this.acceptDate;
	}
    public IntegerProperty acceptedProperty(){
        return this.accepted;
    }

    public DoubleProperty quantityProperty() {
        return this.quantity;
    }
}
