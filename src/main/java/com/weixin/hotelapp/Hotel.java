package com.weixin.hotelapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;

public class Hotel extends Application {

    public static int WIDTH = 800;
    public static int HEIGHT = 500;
    private TenantList tList;
    private int numOfRooms;

    private Label heading = new Label("Hotel Management");

    private Label roomLabel1 = new Label("Room");
    private TextField roomField1 = new TextField();
    private Label nameLabel = new Label("Name");
    private TextField nameField = new TextField();

    private Button addTenant = new Button("Add Tenant");
    private Button displayTenants = new Button("Display Tenants");
    private Button removeTenant = new Button("Remove Tenant");
    private Button saveAndQuit = new Button("Save and Quit");

    private TextArea textArea1 = new TextArea();

    private Label roomLabel2 = new Label("Room");
    private TextField roomField2 = new TextField();
    private Label monthLabel = new Label("Month");
    private TextField monthField = new TextField();
    private Label amountLabel = new Label("Amount");
    private TextField amountField = new TextField();

    private Button makePayment = new Button("Make Payment");
    private Button displayPayments = new Button("Display Payments");

    private TextArea textArea2 = new TextArea();
    @Override
    public void start(Stage stage) {

        numOfRooms = getNumOfRooms();
        tList = new TenantList(numOfRooms);

        heading.setFont(Font.font("Carib", 40));
        roomField1.setMinWidth(50);
        roomField1.setMaxWidth(50);

        nameField.setMinWidth(160);
        nameField.setMaxWidth(160);
        addTenant.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,
                new CornerRadii(10), Insets.EMPTY)));
        displayTenants.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
        removeTenant.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
        saveAndQuit.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));


        textArea1.setMaxSize(650, 110);
        textArea1.setMinSize(650, 110);

        roomField2.setMinWidth(50);
        roomField2.setMaxWidth(50);
        textArea2.setMaxSize(650, 110);
        textArea2.setMinSize(650, 110);

        makePayment.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));
        displayPayments.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(10), Insets.EMPTY)));

        HBox tenantInfo = new HBox(10);
        tenantInfo.setAlignment(Pos.CENTER);
        tenantInfo.getChildren().addAll(roomLabel1, roomField1, nameLabel, nameField);

        HBox tenantButtons = new HBox(10);
        tenantButtons.setAlignment(Pos.CENTER);
        tenantButtons.getChildren().addAll(addTenant, displayTenants, removeTenant, saveAndQuit);

        HBox paymentInfo = new HBox(10);
        paymentInfo.setAlignment(Pos.CENTER);
        paymentInfo.getChildren().addAll(roomLabel2, roomField2, monthLabel, monthField, amountLabel, amountField);

        HBox paymentButtons = new HBox(10);
        paymentButtons.setAlignment(Pos.CENTER);
        paymentButtons.getChildren().addAll(makePayment, displayPayments);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(Background.EMPTY);
        root.getChildren().addAll(heading, tenantInfo, tenantButtons, textArea1, paymentInfo, paymentButtons, textArea2);


        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.LIGHTBLUE);
        stage.setScene(scene);
        stage.setTitle("Hotel");
        stage.show();

        root.setMinSize(WIDTH, HEIGHT);
        root.setMaxSize(WIDTH, HEIGHT);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);

        addTenant.setOnAction(e -> addHandler());
        displayTenants.setOnAction(e -> displayTenants());
        removeTenant.setOnAction(e -> removeTenant());
        makePayment.setOnAction(e -> paymentHandler());
        displayPayments.setOnAction(e -> displayPayments());
        saveAndQuit.setOnAction(e -> saveAndQuit());

    }
    private int getNumOfRooms(){
        TextInputDialog tDialog = new TextInputDialog();
        tDialog.setTitle("Room Info");
        tDialog.setHeaderText("How many rooms");
        String response = tDialog.showAndWait().get();
        return Integer.parseInt(response);
    }
    private void addHandler(){
        String roomNum = roomField1.getText();
        String name = nameField.getText();

        if (!isNumeric(roomNum)){
            textArea1.setText("Enter a number");
        } else if(roomNum.length() == 0 || name.length() == 0){
            textArea1.setText("Enter room number and user name");
        }else if(Integer.parseInt(roomNum) < 1 || Integer.parseInt( roomNum) > numOfRooms ){
            textArea1.setText("Invalid room number");
        }else if(tList.searchTenant(Integer.parseInt(roomNum)) != null){
            textArea1.setText("The room " + roomNum + " is occupied");
        }else {
            Tenant tenant = new Tenant(name, Integer.parseInt(roomNum));
            tList.addTenant(tenant);
            roomField1.setText("");
            nameField.setText("");
            textArea1.setText("Tenant " + tenant.getName() + " has been successfully added");
        }
    }

    private void displayTenants(){
        if(tList.isEmpty()){
            textArea1.setText("There is no tenant to display");
        }else {
            textArea1.setText("Room" +"\t\t" + "Name" +"\n");
            for (Tenant tenant: tList.getTenantList()) {
                textArea1.appendText(tenant.getRoomNumber() + "\t\t\t" + tenant.getName() + "\n");
            }
        }
    }

    private void removeTenant(){
        String room = roomField1.getText();
        if(!isNumeric(room)){
            textArea1.setText("Enter a room number");
        }
        else if(room.length() == 0){
            textArea1.setText("Enter room number");
        }else if(Integer.parseInt(room) < 1 || Integer.parseInt(room) > 12){
            textArea1.setText("Room number beyond scope");
        }else {
            tList.removeTenant(Integer.parseInt(room));
            roomField1.setText("");
            textArea1.setText("Tenant has been removed" );
        }
    }

    private void paymentHandler(){
        String roomIn = roomField2.getText().trim();
        String monthIn = monthField.getText().trim();
        String amountIn = amountField.getText().trim();

        if(!isNumeric(roomIn) || !isNumeric(amountIn)){
            textArea2.setText("Enter a valid number for room or amount");
        }
        else if(roomIn.length()==0 || monthIn.length()==0 || amountIn.length()==0){
            textArea2.setText("Enter room number, month and amount");
        }else if(Integer.parseInt(roomIn) < 1 || Integer.parseInt(roomIn) > numOfRooms){
            textArea2.setText("Room number is beyond scope");
        }else if(tList.searchTenant(Integer.parseInt(roomIn)) == null){
            textArea2.setText("There is no tenant in room " + Integer.parseInt(roomIn));
        }else if(tList.searchTenant(Integer.parseInt(roomIn)).getPayments().isFull()){
            textArea2.setText("The payment list is full, no payment can be made");
            roomField2.setText("");
            monthField.setText("");
            amountField.setText("");
        }
        else {
            Payment payment = new Payment(monthIn,Integer.parseInt(amountIn));
            tList.searchTenant(Integer.parseInt(roomIn)).getPayments().addPayment(payment);
            textArea2.setText("Payment in room " + roomIn +  " has been made");
            roomField2.setText("");
            monthField.setText("");
            amountField.setText("");
        }
    }

    private void displayPayments(){
        String roomIn = roomField2.getText();
        if(!isNumeric(roomIn)){
            textArea2.setText("Enter a number");
        }
        else if(roomIn.length() == 0){
            textArea2.setText("Enter a room number");
        }else if(Integer.parseInt(roomIn) < 1 || Integer.parseInt(roomIn) > 12) {
            textArea2.setText("Room number is beyond scope");
        }else if(tList.searchTenant(Integer.parseInt(roomIn)) == null){
            textArea2.setText("There is no tenant in room " + roomIn);
        }else {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            textArea2.setText("Room" + "\t\t\t" + "Month" + "\t\t\t" + "Amount" + "\n");
            for (Payment payment:tList.searchTenant(Integer.parseInt(roomIn)).getPayments().getPaymentList()) {
                textArea2.appendText(roomIn.trim() + "\t\t\t\t" + payment.getMonth() + "\t\t\t\t" + nf.format(payment.getAmount()) + "\n");
            }
            roomField2.setText("");
        }
    }
    private void saveAndQuit(){
        Platform.exit();
    }

    private boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try{
            double d = Double.parseDouble(strNum);
        }catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

