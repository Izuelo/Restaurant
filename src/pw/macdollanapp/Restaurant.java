package pw.macdollanapp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.ArrayList;
import java.util.Queue;

public class Restaurant {
    private SimpleDoubleProperty currentFood= new SimpleDoubleProperty(this, "currentFood");
    private int maxFood;
    private SimpleIntegerProperty qSize= new SimpleIntegerProperty(this,"qSize");

    Queue<Consumer> q;
    ArrayList<Table> tables=new ArrayList<Table>();

    public Restaurant(int currentFood, int maxFood, Queue<Consumer> q) {
        this.currentFood.set(currentFood);
        this.maxFood = maxFood;
        this.q=q;

    }


    public int getqSize() {
        return qSize.get();
    }

    public SimpleIntegerProperty qSizeProperty() {
        return qSize;
    }

    public void setqSize( Queue q) {
        this.qSize.set(q.size());
    }

    public double getCurrentFood() {
        return currentFood.get();
    }

    public SimpleDoubleProperty currentFoodProperty() {
        return currentFood;
    }

    public void setCurrentFood(double currentFood) {
        this.currentFood.set(currentFood);
    }

    public int getMaxFood() {
        return maxFood;
    }

    public Queue<Consumer> getQ() {
        return q;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public  int findFreeTable(){
        for( int i=0;i<tables.size();i++){
            if(tables.get(i).getFree()){
                return tables.get(i).getTableID();
            }
        }
        return -1;
    }

    public  void enterTable (int tableID, long cID) throws Exception{
        for( int i=0;i<tables.size();i++){
            if(tables.get(i).getTableID()==tableID){
                Thread.sleep(500);
                tables.get(i).setFree(false);
                System.out.println("Konsument #"+cID+" zajal stolik "+tableID);
            }
        }
    }
}
