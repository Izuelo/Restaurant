package pw.macdollanapp;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ProgressIndicator;

public class Table {
    private int seats;
    private SimpleBooleanProperty isFree= new SimpleBooleanProperty(this, "isFree");
    private int tableID;

    private ProgressIndicator pi;

    public Table(int seats, int tableID,ProgressIndicator pi) {
        this.seats = seats;
        this.tableID = tableID;
        this.isFree.set(true);
        this.pi=pi;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public BooleanProperty isFree() {
        return isFree;
    }

    public boolean getFree(){return isFree.get();}

    public void setFree(boolean free) {
        isFree.set(free);
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public ProgressIndicator getPi() {
        return pi;
    }
}
