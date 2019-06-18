package pw.macdollanapp;

import javafx.concurrent.Task;

public class Producer extends Task {
    private Restaurant mag;
    private long id;

    public Producer(Restaurant mag, long id) {
        this.mag = mag;
        this.id=id;
    }

    public long getId() {
        return id;
    }



    @Override
    public Object call(){
        while (true){
            try {
                synchronized (mag) {
                    while (mag.getCurrentFood() + 1 > mag.getMaxFood()) {
                        System.out.println("Magazyn jest pelny");
                        mag.wait();
                    }
                    mag.setCurrentFood(mag.getCurrentFood()+1);
                    System.out.println("Producent #"+this.getId()+": ilosc = "+mag.getCurrentFood());
                    mag.notifyAll();
                }
                Thread.sleep(2750);
            }catch(Exception e){
                System.out.println(e);
            }
        }

    }
}
