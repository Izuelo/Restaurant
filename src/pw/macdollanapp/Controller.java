package pw.macdollanapp;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.Queue;


public class Controller {



    @FXML
    Pane pane= new Pane();


    Queue<Consumer> q= new LinkedList<Consumer>();
    Restaurant restaurant =new Restaurant(0,20,q);
    long pID=1;
    long cID=1;

    @FXML
    private ProgressBar currentFood;

    @FXML
    private Text textFood;

    @FXML
    private Text qLenght;


    @FXML
    private ProgressIndicator PI1;

    @FXML
    private ProgressIndicator PI2;

    @FXML
    private ProgressIndicator PI3;

    @FXML
    private ProgressIndicator PI4;

    //Random rand= new Random();
    @FXML
    public void initialize() {

        restaurant.currentFoodProperty().addListener((obs, oldStatus, newStatus) -> {
         currentFood.setProgress(restaurant.getCurrentFood()/restaurant.getMaxFood());
            textFood.setText("Poziom jedzenia: " + (int)restaurant.getCurrentFood() + "/" + restaurant.getMaxFood());
        });

        restaurant.qSizeProperty().addListener((obs, oldStatus, newStatus) -> {
            qLenght.setText("Długość kolejki: "+ restaurant.getqSize());
        });



        double coordinates[][]={{191,363},{305,426},{362,381},{262,317}};

        ProgressIndicator tablePI[]={PI1,PI2,PI3,PI4};

        for(int i =1;i<5;i++){
            Table table= new Table(4,i,tablePI[i-1]);
            ImageView tableIM= new ImageView(new Image ("pw/macdollanapp/tablesFree1.png"));
            tableIM.setX(coordinates[i-1][0]);
            tableIM.setY(coordinates[i-1][1]);
            pane.getChildren().add(tableIM);
            restaurant.getTables().add(table);
            table.isFree().addListener((obs, oldStatus, newStatus) -> {
                if( newStatus==false) {

                    tableIM.setImage(new Image ("pw/macdollanapp/tablesTaken1.png"));
                }
                else {

                    tableIM.setImage(new Image ("pw/macdollanapp/tablesFree1.png"));
                }
            });
        }



    }

    @FXML
    void DodajKlienta(javafx.event.ActionEvent event) {
        Consumer c = new Consumer(restaurant, cID,q);
        new Thread(c).start();
        cID++;
    }

    @FXML
    void DodajKucharza(javafx.event.ActionEvent event) {
        Producer p=new Producer(restaurant,pID);
        new Thread (p).start();
        pID++;
    }



}
