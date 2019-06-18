package pw.macdollanapp;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.util.Queue;

public class Consumer extends Task {
    private Restaurant restaurant;
    private long id;
    private boolean quit;
    Queue<Consumer> q;
    int tid=-1;
    ProgressBar pi=null;

    public Consumer(Restaurant mag, long id, Queue<Consumer> q) {
        this.restaurant = mag;
        this.id=id;
        this.quit=false;
        this.q=q;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        String name ="Consumer #"+this.getId();
        return name;
    }

    @Override
    public Object call(){
        q.add(this);
        restaurant.setqSize(q);
        System.out.println(restaurant.getQ());

        try {
            while (!quit) {
                synchronized (restaurant) {
                    if (restaurant.getQ().peek().getId() == this.getId()) {

                        while ((this.tid = restaurant.findFreeTable()) == -1) {
                            System.out.println("Nie ma stolikow");
                            restaurant.wait();
                        }

                        this.pi=restaurant.getTables().get(tid-1).getPi();

                        restaurant.getQ().poll();
                        restaurant.setqSize(q);
                        restaurant.enterTable(this.tid, this.getId());

                        while (restaurant.getCurrentFood() < 1) {
                            System.out.println("Nie ma jedzenia");
                            restaurant.wait();
                        }
                        restaurant.setCurrentFood(restaurant.getCurrentFood() - 1);
                        System.out.println("Konsument #" + this.getId() + ": ilosc = " + restaurant.getCurrentFood());
                        this.quit = true;

                        restaurant.notifyAll();
                    }
                }
                if (quit) {
                    for (int i = 1; i <= 300; i++) {

                            this.pi.setProgress(i / 300.0);
                            Thread.sleep(10);

                    }

                    synchronized (restaurant) {
                        System.out.println("XXXXX "+this.tid);
                        this.pi.setProgress(0/300.0);
                        restaurant.tables.get(this.tid - 1).setFree(true);
                        restaurant.notifyAll();
                        System.out.println("Konsument #" + this.getId() + " wychodzi" + tid);
                    }

                }
            }
        }catch(Exception e){ System.out.println(e); }
        return null;
    }
}
