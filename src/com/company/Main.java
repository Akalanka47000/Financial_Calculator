package com.company;

import com.mongodb.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Main extends Application {
    //Initializtion of elements
    MenuBar mBar=new MenuBar();
    Menu IFileMenu=new Menu("File");
    MenuItem IDataExport=new MenuItem("Export Data");
    Menu IHelp=new Menu("Help");
    MenuItem IInstructions=new MenuItem("Instructions");
    ImageView homeImage=new ImageView();
    ImageView iImage=new ImageView();
    Button btnInterest=new Button();
    Button btnSavings=new Button();
    Button btnLoan=new Button();
    Button btnMortgage=new Button();
    Label lblCapital=new Label("Capital:");
    Label lblRate=new Label("Rate:");
    Label lblPeriod=new Label("Period:");
    Label lblFV=new Label("FV:");
    Font fHeading=new Font("Arial",40);
    Button btnICalculate =new Button();
    Button btnIBack=new Button();
    Button btnKeyboard=new Button();
    ImageView imgKeyboard=new ImageView();
    TextField tfCapital=new TextField();
    TextField tfrate=new TextField();
    TextField tfYrs=new TextField();
    TextField tfFV=new TextField();
    ComboBox cmbSolve=new ComboBox();
    Stage HomepageStage;
    Stage InterestStage=new Stage();
    Double Answer =0.0;
    Long answer=null;
    //Variables to determine which of the 4 text fields is currently from the main window
    Boolean clickedFD=false;
    Boolean clickedSavings=false;
    Boolean clickedLoan=false;
    Boolean clickedMortgage=false;
    //Variables to determine which of the 4 text fields is currently selected(In the FD window)
    Boolean clickedCapitalTextField=false;
    Boolean clickedRateTextField=false;
    Boolean clickedPeriodTextField=false;
    Boolean clickedFVTextField=false;

    //Initializing the javafx elements for the savings window
    Label lblSCapital=new Label("Capital:");
    Label lblSRate=new Label("Rate:");
    Label lblSPeriod=new Label("Period:");
    Label lblSMA=new Label("Monthly addition:");
    Label lblSFV=new Label("FV:");
    TextField tfSCapital=new TextField();
    TextField tfSrate=new TextField();
    TextField tfSYrs=new TextField();
    TextField tfSMA=new TextField();
    TextField tfSFV=new TextField();
    ComboBox cmbSavingsSolve=new ComboBox();
    Double SavingsAnswer =0.0;
    Long Savingsanswer=null;
    Stage savingsStage=new Stage();
    Button btnSCalculate =new Button();
    Button btnSBack=new Button();
    Button btnSKeyboard=new Button();
    ImageView imgSKeyboard=new ImageView();
    ImageView SImage=new ImageView();
    MenuBar SmBar=new MenuBar();
    Menu SFileMenu=new Menu("File");
    MenuItem SDataExport=new MenuItem("Export Data");
    Menu SHelp=new Menu("Help");
    MenuItem SInstructions=new MenuItem("Instructions");

    //Initializing the javafx elements for the loan window
    Label lblLCapital=new Label("Loan Amount:");
    Label lblLRate=new Label("Rate:");
    Label lblLInstallmentCount =new Label("Installment Count:");
    Label lblLMonthlyPayment=new Label("Monthly Payment:");
    TextField tfLCapital=new TextField();
    TextField tfLrate=new TextField();
    TextField tfLIC =new TextField();
    TextField tfLMP =new TextField();
    ComboBox cmbLoanSolve=new ComboBox();
    Double loanAnswer =0.0;
    Long loananswer=null;
    Stage loanStage=new Stage();
    Button btnLCalculate =new Button();
    Button btnLBack=new Button();
    Button btnLKeyboard=new Button();
    ImageView imgLKeyboard=new ImageView();
    ImageView LImage=new ImageView();
    MenuBar LmBar=new MenuBar();
    Menu LFileMenu=new Menu("File");
    MenuItem LDataExport=new MenuItem("Export Data");
    Menu LHelp=new Menu("Help");
    MenuItem LInstructions=new MenuItem("Instructions");

    //Initializing the javafx elements for the loan window
    Label lblmCapital=new Label("Mortgage Value:");
    Label lblmRate=new Label("Rate:");
    Label lblmInstallmentCount =new Label("Installment Count:");
    Label lblmMonthlyPayment=new Label("Monthly Payment:");
    TextField tfmCapital=new TextField();
    TextField tfmrate=new TextField();
    TextField tfmIC =new TextField();
    TextField tfmMP =new TextField();
    ComboBox cmbMortgageSolve=new ComboBox();
    Double mortgageAnswer =0.0;
    Long mortgageanswer=null;
    Stage mortgageStage=new Stage();
    Button btnmCalculate =new Button();
    Button btnmBack=new Button();
    Button btnmKeyboard=new Button();
    ImageView imgmKeyboard=new ImageView();
    ImageView mImage=new ImageView();
    MenuBar mmBar=new MenuBar();
    Menu mFileMenu=new Menu("File");
    MenuItem mDataExport=new MenuItem("Export Data");
    Menu mHelp=new Menu("Help");
    MenuItem mInstructions=new MenuItem("Instructions");

    //Variables to determine which of the 5 text fields is currently selected(In the Savings window)
    Boolean clickedCapitalTextFieldSavings=false;
    Boolean clickedRateTextFieldSavings=false;
    Boolean clickedPeriodTextFieldSavings=false;
    Boolean clickedMATextFieldSavings=false;
    Boolean clickedFVTextFieldSavings=false;

    //Variables to determine which of the 4 text fields is currently selected(In the Loan window)
    Boolean clickedCapitalTextFieldLoan=false;
    Boolean clickedRateTextFieldLoan=false;
    Boolean clickedICTextFieldLoan =false;
    Boolean clickedMPTextFieldLoan =false;

    //Variables to determine which of the 4 text fields is currently selected(In the mortgage window)
    Boolean clickedCapitalTextFieldMortgage=false;
    Boolean clickedRateTextFieldMortgage=false;
    Boolean clickedICTextFieldMortgage =false;
    Boolean clickedMPTextFieldMortgage =false;

    //Initialzing the database variables
    DB database;
    MongoClient mongoClient = new MongoClient("localhost", 27017);

    //Initializing variables needed for the FD table
     String FDIDNum;
     Boolean FDfirst;
     String FDLastOrNot;

    //Initializing variables needed for the Savings table
    String SavingsIDNum;
    Boolean Savingsfirst;
    String SavingsLastOrNot;

    //Initializing variables needed for the Loan table
    String LoanIDNum;
    Boolean Loanfirst;
    String LoanLastOrNot;


    //Initializing variables needed for the Mortgage table
    String MortgageIDNum;
    Boolean Mortgagefirst;
    String MortgageLastOrNot;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating the database
        database = mongoClient.getDB("FinanceCalculationDetails");
        //Creating the tables in the database
        database.createCollection("FDdetails", null);
        database.createCollection("SavingsDetails", null);
        database.createCollection("LoanDetails", null);
        database.createCollection("MortgageDetails", null);
        //Initializing effects
        Bloom bloom=new Bloom();
        bloom.setThreshold(0.93);
        //Setting positions,display texts,etc..... of the elements of the first window
        btnInterest.setText("Fixed Deposit");
        btnInterest.setLayoutX(333.0);
        btnInterest.setLayoutY(143.0);
        btnInterest.setPrefWidth(116.0);
        btnInterest.setPrefHeight(26.0);
        btnInterest.setStyle("-fx-background-radius: 5");
        btnInterest.setCursor(Cursor.HAND);
        btnInterest.setEffect(bloom);
        btnSavings.setText("Savings");
        btnSavings.setLayoutX(333.0);
        btnSavings.setLayoutY(180.0);
        btnSavings.setPrefWidth(116.0);
        btnSavings.setPrefHeight(26.0);;
        btnSavings.setStyle("-fx-background-radius: 5");
        btnSavings.setCursor(Cursor.HAND);
        btnSavings.setEffect(bloom);
        btnLoan.setText("Loan");
        btnLoan.setLayoutX(333.0);
        btnLoan.setLayoutY(216.0);
        btnLoan.setPrefWidth(116.0);
        btnLoan.setPrefHeight(26.0);
        btnLoan.setStyle("-fx-background-radius: 5");
        btnLoan.setCursor(Cursor.HAND);
        btnLoan.setEffect(bloom);
        btnMortgage.setText("Mortgage");
        btnMortgage.setLayoutX(333.0);
        btnMortgage.setLayoutY(251.0);
        btnMortgage.setPrefWidth(116.0);
        btnMortgage.setPrefHeight(26.0);
        btnMortgage.setStyle("-fx-background-radius: 5");
        btnMortgage.setCursor(Cursor.HAND);
        btnMortgage.setEffect(bloom);
        //Initizlizing and setting the background image for the first window
        Image image = new Image("/footage/finance-money-backgrounds-powerpoint.jpg");
        homeImage.setImage(image);
        homeImage.setFitWidth(538);
        homeImage.setFitHeight(400);
        //Initiazling the anchorpane and setting the elements to the pane
        AnchorPane parent=new AnchorPane();
        parent.setPrefWidth(537.0);
        parent.setPrefHeight(400.0);
        parent.getChildren().add(homeImage);
        parent.getChildren().add(btnInterest);
        parent.getChildren().add(btnSavings);
        parent.getChildren().add(btnLoan);
        parent.getChildren().add(btnMortgage);
        Scene scene=new Scene(parent,537,400);
        HomepageStage=primaryStage;
        HomepageStage.setTitle("Home");
        HomepageStage.setScene(scene);
        HomepageStage.show();
        //Button click calls a method which will open up the window which calculates interest
        btnInterest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    clickInterest();
                    clickedFD=true;
                    clickedSavings=false;
                    clickedLoan=false;
                    clickedMortgage=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnSavings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    clickSavings();
                    clickedFD=false;
                    clickedSavings=true;
                    clickedLoan=false;
                    clickedMortgage=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnLoan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    clickLoan();
                    clickedFD=false;
                    clickedSavings=false;
                    clickedLoan=true;
                    clickedMortgage=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnMortgage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clickMortgage();
                clickedFD=false;
                clickedSavings=false;
                clickedLoan=false;
                clickedMortgage=true;
            }
        });
        //Setting the menu bar for the FD window
        mBar.setPrefWidth(537);
        mBar.setPrefHeight(26);
        IFileMenu.getItems().add(IDataExport);
        mBar.getMenus().add(IFileMenu);
        IHelp.getItems().add(IInstructions);
        mBar.getMenus().add(IHelp);

        //Setting the menu bar for the Savings window
        SmBar.setPrefWidth(537);
        SmBar.setPrefHeight(26);
        SFileMenu.getItems().add(SDataExport);
        SmBar.getMenus().add(SFileMenu);
        SHelp.getItems().add(SInstructions);
        SmBar.getMenus().add(SHelp);

        //Setting the menu bar for the loan window
        LmBar.setPrefWidth(537);
        LmBar.setPrefHeight(26);
        LFileMenu.getItems().add(LDataExport);
        LmBar.getMenus().add(LFileMenu);
        LHelp.getItems().add(LInstructions);
        LmBar.getMenus().add(LHelp);

        //Setting the menu bar for the mortgage window
        mmBar.setPrefWidth(537);
        mmBar.setPrefHeight(26);
        mFileMenu.getItems().add(mDataExport);
        mmBar.getMenus().add(mFileMenu);
        mHelp.getItems().add(mInstructions);
        mmBar.getMenus().add(mHelp);

    }
    public void clickInterest() throws Exception{
        //Initializing effects
        Bloom bloom=new Bloom();
        bloom.setThreshold(0.93);
        btnKeyboard.setLayoutX(110);
        btnKeyboard.setLayoutY(289);
        btnKeyboard.setPrefWidth(53);
        btnKeyboard.setPrefHeight(18);
        btnKeyboard.setEffect(bloom);
        Image imageForKeyboard = new Image("/footage/wireless-keyboard-k360-emea-glossy-black-glamour-image-lg.png");
        imgKeyboard.setImage(imageForKeyboard);
        imgKeyboard.setFitWidth(43);
        imgKeyboard.setFitHeight(18);
        btnKeyboard.setGraphic(imgKeyboard);
        btnKeyboard.setCursor(Cursor.HAND);
       //Setting positions,display texts,etc..... of the elements of the interest calculator window
        lblCapital.setLayoutX(33);
        lblCapital.setLayoutY(86);
        lblCapital.setTextFill(Color.BLACK);
        lblRate.setLayoutX(33);
        lblRate.setLayoutY(120);
        lblRate.setTextFill(Color.BLACK);
        lblPeriod.setLayoutX(33);
        lblPeriod.setLayoutY(152);
        lblPeriod.setTextFill(Color.BLACK);
        lblFV.setLayoutX(33);
        lblFV.setLayoutY(185);
        lblFV.setTextFill(Color.BLACK);
        btnICalculate.setText("Calculate");
        tfCapital.setPrefWidth(120);
        tfCapital.setPrefHeight(26);
        tfCapital.setPromptText("Rs");
        tfCapital.setLayoutX(86);
        tfCapital.setLayoutY(82);
        tfrate.setPrefWidth(120);
        tfrate.setPrefHeight(26);
        tfrate.setPromptText("%");
        tfrate.setLayoutX(86);
        tfrate.setLayoutY(116);
        tfYrs.setPrefWidth(120);
        tfYrs.setPrefHeight(26);
        tfYrs.setPromptText("Years");
        tfYrs.setLayoutX(86);
        tfYrs.setLayoutY(148);
        tfFV.setPrefWidth(120);
        tfFV.setPrefHeight(26);
        tfFV.setPromptText("Rs");
        tfFV.setLayoutX(86);
        tfFV.setLayoutY(181);
        //Setting up the calculate button
        btnICalculate.setPrefWidth(120);
        btnICalculate.setPrefHeight(26);
        btnICalculate.setLayoutX(86);
        btnICalculate.setLayoutY(249);
        btnICalculate.setStyle("-fx-background-radius: 5");
        btnICalculate.setCursor(Cursor.HAND);
        btnICalculate.setEffect(bloom);
        //Setting up the back button
        btnIBack.setText("Back");
        btnIBack.setPrefWidth(116);
        btnIBack.setPrefHeight(26);
        btnIBack.setLayoutX(363);
        btnIBack.setLayoutY(322);
        btnIBack.setStyle("-fx-background-radius: 5");
        btnIBack.setCursor(Cursor.HAND);
        btnIBack.setEffect(bloom);
        //Setting up the dropdown box
        cmbSolve.setLayoutX(86);
        cmbSolve.setLayoutY(216);
        cmbSolve.setPrefWidth(120);
        cmbSolve.setPrefHeight(26);
        cmbSolve.setPromptText("Solve for?");
        //Setting up what needs to be displayed in the dropdown box
        ObservableList array = FXCollections.observableArrayList();
        array.add("Future Value");
        array.add("Present Value");
        array.add("Interest Rate");
        array.add("Period");
        cmbSolve.setItems(array);
        Image image = new Image("/footage/hawkins-cookers-fd-should-you-invest.jpg");
        iImage.setImage(image);
        iImage.setFitWidth(751);
        iImage.setFitHeight(484);
        iImage.setLayoutX(-72);
        iImage.setLayoutY(-2);
        AnchorPane IPane=new AnchorPane();
        IPane.setPrefWidth(537);
        IPane.setPrefHeight(390);
        IPane.getChildren().add(iImage);
        IPane.getChildren().add(mBar);
        IPane.getChildren().add(lblCapital);
        IPane.getChildren().add(lblRate);
        IPane.getChildren().add(lblPeriod);
        IPane.getChildren().add(lblFV);
        IPane.getChildren().add(tfCapital);
        IPane.getChildren().add(tfrate);
        IPane.getChildren().add(tfYrs);
        IPane.getChildren().add(tfFV);
        IPane.getChildren().add(cmbSolve);
        IPane.getChildren().add(btnICalculate);
        IPane.getChildren().add(btnIBack);
        IPane.getChildren().add(btnKeyboard);
        Scene interestScene=new Scene(IPane,537,390);
        InterestStage.setTitle("FD Calculator");
        InterestStage.setScene(interestScene);
        InterestStage.show();
        HomepageStage.close();
        //Reading the data of the last performed calculation
        DBCollection FDTable = database.getCollection("FDdetails");
        DBCursor findIterable = FDTable.find();
        for (DBObject count : findIterable) {
            FDLastOrNot = (String) count.get("Last");
            if (FDLastOrNot.equals("Yes")) {
                tfCapital.setText((String)count.get("Capital"));
                tfrate.setText((String)count.get("Rate"));
                tfYrs.setText((String)count.get("Years"));
                tfFV.setText((String)count.get("FV"));
            }
        }
        btnICalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(!(cmbSolve.getValue() ==null)) {
                        //If the user wishes to find the future value
                        if(cmbSolve.getValue().equals("Future Value")) {
                            //Calculation of the future value
                            Double capital = Double.parseDouble(tfCapital.getText());
                            Double rate = Double.parseDouble(tfrate.getText());
                            Double years = Double.parseDouble(tfYrs.getText());
                            Answer = capital;
                            for(int i=0;i<years;i++){
                                Answer=Answer+((Answer*rate)/100);
                            }
                            BigDecimal bd = new BigDecimal(Answer).setScale(2, RoundingMode.HALF_UP);
                            Answer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Future Value = " + Answer + " rupees");
                            a.showAndWait();
                            tfFV.setText(Answer.toString());

                            //If the user wishes to find the interest rate
                        }else if(cmbSolve.getValue().equals("Interest Rate")){
                            Double capital = Double.parseDouble(tfCapital.getText());
                            Double years = Double.parseDouble(tfYrs.getText());
                            Double futureValue = Double.parseDouble(tfFV.getText());
                            BigDecimal bd = new BigDecimal(((100*(Math.pow((futureValue/capital),(1/years))))-100)).setScale(2, RoundingMode.HALF_UP);
                            Answer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Interest required = " + Answer + "%");
                            a.showAndWait();
                            tfrate.setText(Answer.toString());

                            //If the user wishes to find the period
                        }else if((cmbSolve.getValue().equals("Period"))){
                            Double capital = Double.parseDouble(tfCapital.getText());
                            Double futureValue = Double.parseDouble(tfFV.getText());
                            Double rate = Double.parseDouble(tfrate.getText());
                            Double temp1=futureValue/capital;
                            Double temp2=1+(rate/100);
                            answer=Math.round(Math.log(temp1)/Math.log(temp2));
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Time needed = " + answer + " years");
                            a.showAndWait();
                            tfYrs.setText(answer.toString());

                            //If the user wishes to find the present value
                        }else if((cmbSolve.getValue().equals("Present Value"))){
                            Double futureValue = Double.parseDouble(tfFV.getText());
                            Double rate = Double.parseDouble(tfrate.getText());
                            Double years = Double.parseDouble(tfYrs.getText());
                            Double temp1=1+(rate/100);
                            Answer=futureValue/(Math.pow(temp1,years));
                            BigDecimal bd = new BigDecimal(Answer).setScale(2, RoundingMode.HALF_UP);
                            Answer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Present value = " + Answer + " rupees");
                            a.showAndWait();
                            tfCapital.setText(Answer.toString());
                        }
                        //Writing to the database
                        DBCollection FDTable = database.getCollection("FDdetails");
                        DBCursor findIterable = FDTable.find();
                        FDfirst=true;
                        BasicDBObject basicDBObject = new BasicDBObject();
                        //If the below loop is not entered it means the the table is still empty and the record to be added next is the first record
                        //If the loop is entered, it means that a new record will be added. Therefore the 'last' flag in the last record will be changed to 'No'
                        for (DBObject count : findIterable) {
                            FDfirst=false;
                            FDLastOrNot = (String) count.get("Last");
                            if(FDLastOrNot.equals("Yes")){
                                BasicDBObject query = new BasicDBObject();
                                query.put("Last", FDLastOrNot);
                                BasicDBObject newValue = new BasicDBObject();
                                newValue.put("Last","No");
                                BasicDBObject updateObject = new BasicDBObject();
                                updateObject.put("$set", newValue);
                                database.getCollection("FDdetails").update(query,updateObject);
                            }
                        }
                        //If first record, its ID becomes 1
                        if (FDfirst==true) {
                            basicDBObject.put("ID", "1");
                            //If not the first record the ID of the current record is taken and then incremented and added to the new record below
                        }else{
                            for (DBObject count : findIterable) {
                                FDIDNum = (String) count.get("ID");
                                String newID=Integer.toString(Integer.parseInt(FDIDNum)+1);
                                basicDBObject.put("ID", newID);
                            }

                        }
                        basicDBObject.put("Capital", tfCapital.getText());
                        basicDBObject.put("Rate", tfrate.getText());
                        basicDBObject.put("Years", tfYrs.getText());
                        basicDBObject.put("FV",tfFV.getText());
                        basicDBObject.put("Last","Yes");
                        FDTable.insert(basicDBObject);

                    }else {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("Please select the item for which you need the solution to!");
                        a.showAndWait();
                    }
                }catch (Exception e){
                    //Exception to be handled if invalid data such as a string is entered to the textfield
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("The data entered into the textfields is either invalid or the neccessary textfields have been left empty");
                    a.showAndWait();
                }
            }
        });
        //Methods to set the variables which identify which textfield is selected
        tfCapital.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextField=true;
                clickedRateTextField=false;
                clickedPeriodTextField=false;
                clickedFVTextField=false;
            }
        });
        tfrate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextField=false;
                clickedRateTextField=true;
                clickedPeriodTextField=false;
                clickedFVTextField=false;
            }
        });
        tfYrs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextField=false;
                clickedRateTextField=false;
                clickedPeriodTextField=true;
                clickedFVTextField=false;
            }
        });
        tfFV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextField=false;
                clickedRateTextField=false;
                clickedPeriodTextField=false;
                clickedFVTextField=true;
            }
        });
        //Method to be executed one the export button is clicked from the menu. The code exports the calculated data to a text file
        IDataExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage=new Stage();
                FileChooser fcCategoryExport = new FileChooser();
                fcCategoryExport.setTitle("Export");
                File file = fcCategoryExport.showSaveDialog(stage);
                if (file != null) {
                    try {
                        if (Answer !=0.0||answer!=null) {
                            if(cmbSolve.getValue().equals("Future Value")) {
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Period: " + tfYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + Answer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                Answer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfCapital.setText("");
                                tfrate.setText("");
                                tfYrs.setText("");
                                tfFV.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if(cmbSolve.getValue().equals("Interest Rate")){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Period: " + tfYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Interest rate required: " + Answer + "%" + System.lineSeparator());
                                myWriter.close();
                                Answer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfCapital.setText("");
                                tfrate.setText("");
                                tfYrs.setText("");
                                tfFV.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbSolve.getValue().equals("Period"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Time required: " + answer + " years" + System.lineSeparator());
                                myWriter.close();
                                answer=null;
                                //Clearing the content of the textfields once the data is exported
                                tfCapital.setText("");
                                tfrate.setText("");
                                tfYrs.setText("");
                                tfFV.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbSolve.getValue().equals("Present Value"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Rate: " + tfrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Period: " + tfYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Present Value: " + Answer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                Answer=0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfCapital.setText("");
                                tfrate.setText("");
                                tfYrs.setText("");
                                tfFV.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }
                        }else{
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.WARNING);
                            a.setContentText("Calculation is not completed yet to export");
                            a.showAndWait();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            }
        });
        IInstructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Initializing the font
                Font headingFont=new Font("Book Antiqua",15);
                Font paraFont=new Font("Book Antiqua",12);
                AnchorPane helpPane=new AnchorPane();
                helpPane.setPrefWidth(414);
                helpPane.setPrefHeight(258);
                ImageView helpIV=new ImageView();
                Image HelpImage = new Image("/footage/search-background.png");
                helpIV.setImage(HelpImage);
                helpIV.setFitWidth(490);
                helpIV.setFitHeight(295);
                Label helpHeading=new Label("FD Calculator");
                helpHeading.setFont(headingFont);
                helpHeading.setLayoutX(200);
                helpHeading.setLayoutY(56);
                helpHeading.setPrefWidth(100);
                helpHeading.setPrefHeight(18);
                helpHeading.setTextFill(Color.WHITE);
                Label helpPara1=new Label("Instructions:- Choose what you are solving for and enter the 3 known values from capital, rate, term or future value of the deposit and click calculate in order to find the unknown value. Data of the last performed calculation can be exported to a text file by going into FIle menu>Export option.");
                helpPara1.setFont(paraFont);
                helpPara1.setLayoutX(49);
                helpPara1.setLayoutY(86);
                helpPara1.setPrefWidth(400);
                helpPara1.setPrefHeight(90);
                helpPara1.setTextFill(Color.WHITE);
                helpPara1.setTextAlignment(TextAlignment.CENTER);
                helpPara1.setWrapText(true);
                Label helpPara2=new Label("Note:- Click on the keyboard button below the calculate option in order to launch the on-screen keyboard.");
                helpPara2.setFont(paraFont);
                helpPara2.setLayoutX(46);
                helpPara2.setLayoutY(165);
                helpPara2.setPrefWidth(404);
                helpPara2.setPrefHeight(68);
                helpPara2.setTextFill(Color.WHITE);
                helpPara2.setTextAlignment(TextAlignment.CENTER);
                helpPara2.setWrapText(true);
                helpPane.getChildren().add(helpIV);
                helpPane.getChildren().add(helpHeading);
                helpPane.getChildren().add(helpPara1);
                helpPane.getChildren().add(helpPara2);
                Stage helpStage=new Stage();
                Scene helpScene=new Scene(helpPane,489,295);
                helpStage.setScene(helpScene);
                helpStage.setTitle("Help");
                helpStage.show();
            }
        });
        btnIBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Clearing the content of the textfields once back is clicked
                tfCapital.setText("");
                tfrate.setText("");
                tfYrs.setText("");
                tfFV.setText("");
                InterestStage.close();
                HomepageStage.show();
            }
        });
        btnKeyboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keyboard();
            }
        });
    }
    //Method to be called if the user clicks the savings button from first window
    public void clickSavings() throws Exception {
        //Initialzing the bloom and glow effects
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.93);
        Glow glow = new Glow();
        glow.setLevel(0.3);
        Glow glowLevel2 = new Glow();
        glowLevel2.setLevel(0.7);
        btnSKeyboard.setLayoutX(245);
        btnSKeyboard.setLayoutY(250);
        btnSKeyboard.setPrefWidth(53);
        btnSKeyboard.setPrefHeight(18);
        btnSKeyboard.setEffect(bloom);
        Image imageForKeyboard = new Image("/footage/wireless-keyboard-k360-emea-glossy-black-glamour-image-lg.png");
        imgSKeyboard.setImage(imageForKeyboard);
        imgSKeyboard.setFitWidth(43);
        imgSKeyboard.setFitHeight(18);
        btnSKeyboard.setGraphic(imgSKeyboard);
        btnSKeyboard.setCursor(Cursor.HAND);
        //Setting positions,display texts,etc..... of the elements of the interest calculator window
        lblSCapital.setLayoutX(245);
        lblSCapital.setLayoutY(72);
        lblSCapital.setTextFill(Color.BLACK);
        lblSRate.setLayoutX(245);
        lblSRate.setLayoutY(104);
        lblSRate.setTextFill(Color.BLACK);
        lblSPeriod.setLayoutX(245);
        lblSPeriod.setLayoutY(136);
        lblSPeriod.setTextFill(Color.BLACK);
        lblSMA.setLayoutX(245);
        lblSMA.setLayoutY(170);
        lblSMA.setTextFill(Color.BLACK);
        lblSFV.setLayoutX(245);
        lblSFV.setLayoutY(203);
        lblSFV.setTextFill(Color.BLACK);
        btnSCalculate.setText("Calculate");
        tfSCapital.setPrefWidth(120);
        tfSCapital.setPrefHeight(26);
        tfSCapital.setPromptText("Rs");
        tfSCapital.setLayoutX(373);
        tfSCapital.setLayoutY(66);
        tfSrate.setPrefWidth(120);
        tfSrate.setPrefHeight(26);
        tfSrate.setPromptText("%");
        tfSrate.setLayoutX(373);
        tfSrate.setLayoutY(100);
        tfSYrs.setPrefWidth(120);
        tfSYrs.setPrefHeight(26);
        tfSYrs.setPromptText("Years");
        tfSYrs.setLayoutX(373);
        tfSYrs.setLayoutY(132);
        tfSMA.setPrefWidth(120);
        tfSMA.setPrefHeight(26);
        tfSMA.setPromptText("Rs");
        tfSMA.setLayoutX(373);
        tfSMA.setLayoutY(164);
        tfSFV.setPrefWidth(120);
        tfSFV.setPrefHeight(26);
        tfSFV.setPromptText("Rs");
        tfSFV.setLayoutX(373);
        tfSFV.setLayoutY(196);
        //Setting up the calculate button
        btnSCalculate.setPrefWidth(120);
        btnSCalculate.setPrefHeight(26);
        btnSCalculate.setLayoutX(373);
        btnSCalculate.setLayoutY(266);
        btnSCalculate.setStyle("-fx-background-radius: 5");
        btnSCalculate.setCursor(Cursor.HAND);
        btnSCalculate.setEffect(bloom);
        //Setting up the back button
        btnSBack.setText("Back");
        btnSBack.setPrefWidth(120);
        btnSBack.setPrefHeight(26);
        btnSBack.setLayoutX(372);
        btnSBack.setLayoutY(332);
        btnSBack.setStyle("-fx-background-radius: 5;-fx-background-color: #00fc58");
        btnSBack.setCursor(Cursor.HAND);
        btnSBack.setEffect(glow);
        //Setting up the dropdown box
        cmbSavingsSolve.setLayoutX(373);
        cmbSavingsSolve.setLayoutY(230);
        cmbSavingsSolve.setPrefWidth(120);
        cmbSavingsSolve.setPrefHeight(26);
        cmbSavingsSolve.setPromptText("Solve for?");
        //Setting up what needs to be displayed in the dropdown box
        ObservableList array = FXCollections.observableArrayList();
        array.add("Future Value");
        array.add("Present Value");
        array.add("Interest Rate");
        array.add("Period");
        array.add("Monthly Addition");
        cmbSavingsSolve.setItems(array);
        Image image = new Image("/footage/4246fc9a3bb4294a0b10fdafb56ff152.jpg");
        SImage.setImage(image);
        SImage.setFitWidth(1015);
        SImage.setFitHeight(376);
        SImage.setLayoutX(-76);
        SImage.setLayoutY(23);
        AnchorPane SPane = new AnchorPane();
        SPane.setPrefWidth(537);
        SPane.setPrefHeight(390);
        SPane.getChildren().add(SImage);
        SPane.getChildren().add(SmBar);
        SPane.getChildren().add(lblSCapital);
        SPane.getChildren().add(lblSRate);
        SPane.getChildren().add(lblSPeriod);
        SPane.getChildren().add(lblSFV);
        SPane.getChildren().add(lblSMA);
        SPane.getChildren().add(tfSCapital);
        SPane.getChildren().add(tfSrate);
        SPane.getChildren().add(tfSYrs);
        SPane.getChildren().add(tfSFV);
        SPane.getChildren().add(tfSMA);
        SPane.getChildren().add(btnSKeyboard);
        SPane.getChildren().add(btnSCalculate);
        SPane.getChildren().add(cmbSavingsSolve);
        SPane.getChildren().add(btnSBack);
        Scene savingsScene = new Scene(SPane, 537, 390);
        savingsStage.setScene(savingsScene);
        savingsStage.setTitle("Savings Calculator");
        savingsStage.show();
        HomepageStage.close();
        //Reading the data of the last performed calculation
        DBCollection SavingsTable = database.getCollection("SavingsDetails");
        DBCursor findIterable = SavingsTable.find();
        for (DBObject count : findIterable) {
            SavingsLastOrNot = (String) count.get("Last");
            if (SavingsLastOrNot.equals("Yes")) {
                tfSCapital.setText((String)count.get("Capital"));
                tfSrate.setText((String)count.get("Rate"));
                tfSYrs.setText((String)count.get("Years"));
                tfSMA.setText((String)count.get("Monthly Addition"));
                tfSFV.setText((String)count.get("FV"));
            }
        }
        //Setting up a glow to take effect when the mouse is moved over the back button
        btnSBack.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnSBack.setEffect(glowLevel2);
            }
        });
        btnSBack.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnSBack.setEffect(glow);
            }
        });
        //Navigating back to first window when back is clicked
        btnSBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                savingsStage.close();
                HomepageStage.show();
                tfSCapital.setText("");
                tfSrate.setText("");
                tfSYrs.setText("");
                tfSFV.setText("");
                tfSMA.setText("");
            }
        });
        btnSKeyboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keyboard();
            }
        });
        btnSCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if(!(cmbSavingsSolve.getValue() ==null)) {
                        //If the user wishes to find the future value
                        if(cmbSavingsSolve.getValue().equals("Future Value")) {
                            Double capital = Double.parseDouble(tfSCapital.getText());
                            Double rate = Double.parseDouble(tfSrate.getText());
                            rate=rate/(100);
                            Double years = Double.parseDouble(tfSYrs.getText());
                            Double MA = Double.parseDouble(tfSMA.getText());
                            Double temp1=1+(rate/12);
                            temp1=(Math.pow(temp1,(12*years)))-1;
                            Double temp2=rate/12;
                            Double FVofMA=MA*(temp1/temp2);
                            Double FVCapital = capital;
                            for(int i=0;i<(years*12);i++){
                                FVCapital=FVCapital+(FVCapital*(rate/12));
                            }
                            SavingsAnswer=FVofMA+FVCapital;
                            BigDecimal bd = new BigDecimal(SavingsAnswer).setScale(2, RoundingMode.HALF_UP);
                            SavingsAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Future Value = " + SavingsAnswer + " rupees");
                            a.showAndWait();
                            tfSFV.setText(SavingsAnswer.toString());
                        }else if(cmbSavingsSolve.getValue().equals("Monthly Addition")){
                            Double capital = Double.parseDouble(tfSCapital.getText());
                            Double rate = Double.parseDouble(tfSrate.getText());
                            rate=rate/(100);
                            Double years = Double.parseDouble(tfSYrs.getText());
                            Double FV = Double.parseDouble(tfSFV.getText());
                            Double temp1=1+(rate/12);
                            temp1=(Math.pow(temp1,(12*years)))-1;
                            Double temp2=12/rate;
                            temp2=temp2*temp1;
                            SavingsAnswer=((FV)/temp2)-(capital/temp2);
                            BigDecimal bd = new BigDecimal(SavingsAnswer).setScale(2, RoundingMode.HALF_UP);
                            SavingsAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Monthly Addition = " + SavingsAnswer + " rupees");
                            a.showAndWait();
                            tfSMA.setText(SavingsAnswer.toString());

                        }else if(cmbSavingsSolve.getValue().equals("Period")){
                            Double capital = Double.parseDouble(tfSCapital.getText());
                            Double rate = Double.parseDouble(tfSrate.getText());
                            rate=rate/(100);
                            Double MA = Double.parseDouble(tfSMA.getText());
                            Double FV = Double.parseDouble(tfSFV.getText());
                            Double A=FV;
                            Double temp1=1+((rate*A)/MA);
                            Double temp2=1+rate;
                            temp2=(Math.log(temp2))*12;
                            SavingsAnswer=temp1/temp2;
                            SavingsAnswer=Math.log(SavingsAnswer);
                            Savingsanswer=Math.round(SavingsAnswer);
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Period = " + Savingsanswer + " years");
                            a.showAndWait();
                            tfSYrs.setText(Savingsanswer.toString());
                        }else if(cmbSavingsSolve.getValue().equals("Present Value")){
                            Double rate = Double.parseDouble(tfSrate.getText());
                            rate=rate/(100);
                            Double MA = Double.parseDouble(tfSMA.getText());
                            Double FV = Double.parseDouble(tfSFV.getText());
                            Double years = Double.parseDouble(tfSYrs.getText());
                            //Calculation
                            Double temp1=1+(rate/12);
                            temp1=(Math.pow(temp1,(12*years)))-1;
                            Double temp2=12/rate;
                            temp2=temp2*temp1;
                            SavingsAnswer=FV-(temp2*MA);
                            BigDecimal bd = new BigDecimal(SavingsAnswer).setScale(2, RoundingMode.HALF_UP);
                            SavingsAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Future Value = " + SavingsAnswer + " rupees");
                            a.showAndWait();
                            tfSCapital.setText(SavingsAnswer.toString());
                        }else if(cmbSavingsSolve.getValue().equals("Interest Rate")){
                            Double capital = Double.parseDouble(tfSCapital.getText());
                            Double MA = Double.parseDouble(tfSMA.getText());
                            Double FV = Double.parseDouble(tfSFV.getText());
                            Double years = Double.parseDouble(tfSYrs.getText());
                            //Calculation
                            BigDecimal bd = new BigDecimal(SavingsAnswer).setScale(2, RoundingMode.HALF_UP);
                            SavingsAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Future Value = " + SavingsAnswer + " rupees");
                            a.showAndWait();
                            tfSrate.setText(SavingsAnswer.toString());
                        }
                        //Writing to the database
                        DBCollection SavingsTable = database.getCollection("SavingsDetails");
                        DBCursor findIterable = SavingsTable.find();
                        Savingsfirst=true;
                        BasicDBObject basicDBObject = new BasicDBObject();
                        //If the below loop is not entered it means the the table is still empty and the record to be added next is the first record
                        //If the loop is entered, it means that a new record will be added. Therefore the 'last' flag in the last record will be changed to 'No'
                        for (DBObject count : findIterable) {
                            Savingsfirst=false;
                            SavingsLastOrNot = (String) count.get("Last");
                            if(SavingsLastOrNot.equals("Yes")){
                                BasicDBObject query = new BasicDBObject();
                                query.put("Last", SavingsLastOrNot);
                                BasicDBObject newValue = new BasicDBObject();
                                newValue.put("Last","No");
                                BasicDBObject updateObject = new BasicDBObject();
                                updateObject.put("$set", newValue);
                                database.getCollection("SavingsDetails").update(query,updateObject);
                            }
                        }
                        //If first record, its ID becomes 1
                        if (Savingsfirst==true) {
                            basicDBObject.put("ID", "1");
                            //If not the first record the ID of the current record is taken and then incremented and added to the new record below
                        }else{
                            for (DBObject count : findIterable) {
                                SavingsIDNum = (String) count.get("ID");
                                String newID=Integer.toString(Integer.parseInt(SavingsIDNum)+1);
                                basicDBObject.put("ID", newID);
                            }

                        }
                        basicDBObject.put("Capital", tfSCapital.getText());
                        basicDBObject.put("Rate", tfSrate.getText());
                        basicDBObject.put("Years", tfSYrs.getText());
                        basicDBObject.put("Monthly Addition", tfSMA.getText());
                        basicDBObject.put("FV",tfSFV.getText());
                        basicDBObject.put("Last","Yes");
                        SavingsTable.insert(basicDBObject);
                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("Please select the item for which you need the solution to!");
                        a.showAndWait();
                    }
                }catch (Exception e){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("The data entered into the textfields is either invalid or the neccessary textfields have been left empty");
                    a.showAndWait();
                }
            }
        });
        SDataExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage=new Stage();
                FileChooser fcCategoryExport = new FileChooser();
                fcCategoryExport.setTitle("Export");
                File file = fcCategoryExport.showSaveDialog(stage);
                if (file != null) {
                    try {
                        if (SavingsAnswer !=0.0||Savingsanswer!=null) {
                            if(cmbSavingsSolve.getValue().equals("Future Value")) {
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfSCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfSrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Period: " + tfSYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Monthly Addition: " + tfSMA.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + SavingsAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                SavingsAnswer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfSCapital.setText("");
                                tfSrate.setText("");
                                tfSYrs.setText("");
                                tfSFV.setText("");
                                tfSMA.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if(cmbSavingsSolve.getValue().equals("Interest Rate")){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfSCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Period: " + tfSYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Monthly Addition: " + tfSMA.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfSFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Interest rate required: " + SavingsAnswer + "%" + System.lineSeparator());
                                myWriter.close();
                                SavingsAnswer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfSCapital.setText("");
                                tfSrate.setText("");
                                tfSYrs.setText("");
                                tfSFV.setText("");
                                tfSMA.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbSavingsSolve.getValue().equals("Period"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfSCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfSrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Monthly Addition: " + tfSMA.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfSFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Time required: " + Savingsanswer + " years" + System.lineSeparator());
                                myWriter.close();
                                Savingsanswer=null;
                                //Clearing the content of the textfields once the data is exported
                                tfSCapital.setText("");
                                tfSrate.setText("");
                                tfSYrs.setText("");
                                tfSFV.setText("");
                                tfSMA.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbSavingsSolve.getValue().equals("Present Value"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Rate: " + tfSrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Period: " + tfSYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Monthly Addition: " + tfSMA.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfSFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Present Value/Capital: " + SavingsAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                SavingsAnswer=0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfSCapital.setText("");
                                tfSrate.setText("");
                                tfSYrs.setText("");
                                tfSFV.setText("");
                                tfSMA.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbSavingsSolve.getValue().equals("Monthly Addition"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Capital: " + tfSCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfSrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Period: " + tfSYrs.getText() + " years" + System.lineSeparator());
                                myWriter.append("Future Value: " + tfSFV.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Monthly Addition: " + SavingsAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                SavingsAnswer=0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfSCapital.setText("");
                                tfSrate.setText("");
                                tfSYrs.setText("");
                                tfSFV.setText("");
                                tfSMA.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }
                        }else{
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.WARNING);
                            a.setContentText("Calculation is not completed yet to export");
                            a.showAndWait();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            }
        });
        tfSCapital.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldSavings=true;
                clickedRateTextFieldSavings=false;
                clickedPeriodTextFieldSavings=false;
                clickedFVTextFieldSavings=false;
                clickedMATextFieldSavings=false;
            }
        });
        tfSrate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldSavings=false;
                clickedRateTextFieldSavings=true;
                clickedPeriodTextFieldSavings=false;
                clickedFVTextFieldSavings=false;
                clickedMATextFieldSavings=false;
            }
        });
        tfSYrs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldSavings=false;
                clickedRateTextFieldSavings=false;
                clickedPeriodTextFieldSavings=true;
                clickedFVTextFieldSavings=false;
                clickedMATextFieldSavings=false;
            }
        });
        tfSFV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldSavings=false;
                clickedRateTextFieldSavings=false;
                clickedPeriodTextFieldSavings=false;
                clickedFVTextFieldSavings=true;
                clickedMATextFieldSavings=false;
            }
        });
        tfSMA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldSavings=false;
                clickedRateTextFieldSavings=false;
                clickedPeriodTextFieldSavings=false;
                clickedFVTextFieldSavings=false;
                clickedMATextFieldSavings=true;
            }
        });
        SInstructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Initializing the font
                Font headingFont=new Font("Book Antiqua",15);
                Font paraFont=new Font("Book Antiqua",12);
                AnchorPane helpPane=new AnchorPane();
                helpPane.setPrefWidth(414);
                helpPane.setPrefHeight(258);
                ImageView helpIV=new ImageView();
                Image HelpImage = new Image("/footage/search-background.png");
                helpIV.setImage(HelpImage);
                helpIV.setFitWidth(490);
                helpIV.setFitHeight(295);
                Label helpHeading=new Label("Savings Calculator");
                helpHeading.setFont(headingFont);
                helpHeading.setLayoutX(192);
                helpHeading.setLayoutY(58);
                helpHeading.setPrefWidth(130);
                helpHeading.setPrefHeight(18);
                helpHeading.setTextFill(Color.WHITE);
                Label helpPara1=new Label("Instructions:- Choose what you are solving for and enter the 4 known values from capital, rate, term, monthly addition or future value of the deposit and click calculate in order to find the unknown value. Data of the last performed calculation can be exported to a text file by going into FIle menu>Export option.");
                helpPara1.setFont(paraFont);
                helpPara1.setLayoutX(49);
                helpPara1.setLayoutY(86);
                helpPara1.setPrefWidth(400);
                helpPara1.setPrefHeight(90);
                helpPara1.setTextFill(Color.WHITE);
                helpPara1.setTextAlignment(TextAlignment.CENTER);
                helpPara1.setWrapText(true);
                Label helpPara2=new Label("Note:- Click on the keyboard button onto the left of the calculate option in order to launch the on-screen keyboard.");
                helpPara2.setFont(paraFont);
                helpPara2.setLayoutX(46);
                helpPara2.setLayoutY(165);
                helpPara2.setPrefWidth(404);
                helpPara2.setPrefHeight(68);
                helpPara2.setTextFill(Color.WHITE);
                helpPara2.setTextAlignment(TextAlignment.CENTER);
                helpPara2.setWrapText(true);
                helpPane.getChildren().add(helpIV);
                helpPane.getChildren().add(helpHeading);
                helpPane.getChildren().add(helpPara1);
                helpPane.getChildren().add(helpPara2);
                Stage helpStage=new Stage();
                Scene helpScene=new Scene(helpPane,489,295);
                helpStage.setScene(helpScene);
                helpStage.setTitle("Help");
                helpStage.show();
            }
        });
    }
    public void clickLoan() throws Exception{
        //Initialzing the bloom and glow effects
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.93);
        Glow glow = new Glow();
        glow.setLevel(0.3);
        Glow glowLevel2 = new Glow();
        glowLevel2.setLevel(0.7);
        btnLKeyboard.setLayoutX(401);
        btnLKeyboard.setLayoutY(275);
        btnLKeyboard.setPrefWidth(53);
        btnLKeyboard.setPrefHeight(18);
        btnLKeyboard.setEffect(bloom);
        Image imageForKeyboard = new Image("/footage/wireless-keyboard-k360-emea-glossy-black-glamour-image-lg.png");
        imgLKeyboard.setImage(imageForKeyboard);
        imgLKeyboard.setFitWidth(43);
        imgLKeyboard.setFitHeight(18);
        btnLKeyboard.setGraphic(imgLKeyboard);
        btnLKeyboard.setCursor(Cursor.HAND);
        //Setting positions,display texts,etc..... of the elements of the interest calculator window
        lblLCapital.setLayoutX(235);
        lblLCapital.setLayoutY(70);
        lblLCapital.setTextFill(Color.BLACK);
        lblLRate.setLayoutX(235);
        lblLRate.setLayoutY(104);
        lblLRate.setTextFill(Color.BLACK);
        lblLInstallmentCount.setPrefWidth(130);
        lblLInstallmentCount.setLayoutX(235);
        lblLInstallmentCount.setLayoutY(136);
        lblLInstallmentCount.setTextFill(Color.BLACK);
        lblLMonthlyPayment.setLayoutX(235);
        lblLMonthlyPayment.setLayoutY(170);
        lblLMonthlyPayment.setTextFill(Color.BLACK);
        btnLCalculate.setText("Calculate");
        tfLCapital.setPrefWidth(120);
        tfLCapital.setPrefHeight(26);
        tfLCapital.setPromptText("Rs");
        tfLCapital.setLayoutX(373);
        tfLCapital.setLayoutY(66);
        tfLrate.setPrefWidth(120);
        tfLrate.setPrefHeight(26);
        tfLrate.setPromptText("%");
        tfLrate.setLayoutX(373);
        tfLrate.setLayoutY(100);
        tfLIC.setPrefWidth(120);
        tfLIC.setPrefHeight(26);
        tfLIC.setLayoutX(373);
        tfLIC.setLayoutY(132);
        tfLMP.setPrefWidth(120);
        tfLMP.setPrefHeight(26);
        tfLMP.setPromptText("Rs");
        tfLMP.setLayoutX(373);
        tfLMP.setLayoutY(164);
        //Setting up the calculate button
        btnLCalculate.setPrefWidth(120);
        btnLCalculate.setPrefHeight(26);
        btnLCalculate.setLayoutX(373);
        btnLCalculate.setLayoutY(237);
        btnLCalculate.setStyle("-fx-background-radius: 5");
        btnLCalculate.setCursor(Cursor.HAND);
        btnLCalculate.setEffect(bloom);
        //Setting up the back button
        btnLBack.setText("Back");
        btnLBack.setPrefWidth(120);
        btnLBack.setPrefHeight(26);
        btnLBack.setLayoutX(372);
        btnLBack.setLayoutY(332);
        btnLBack.setStyle("-fx-background-radius: 5;-fx-background-color: #ffbb00");
        btnLBack.setCursor(Cursor.HAND);
        btnLBack.setEffect(glow);
        //Setting up the dropdown box
        cmbLoanSolve.setLayoutX(372);
        cmbLoanSolve.setLayoutY(201);
        cmbLoanSolve.setPrefWidth(120);
        cmbLoanSolve.setPrefHeight(26);
        cmbLoanSolve.setPromptText("Solve for?");
        //Setting up what needs to be displayed in the dropdown box
        ObservableList array = FXCollections.observableArrayList();
        array.add("Monthly Payment");
        array.add("Installments required");
        array.add("Interest Rate");
        array.add("Loan Amount");
        cmbLoanSolve.setItems(array);
        Image image = new Image("/footage/790_ext_01_0.jpg");
        LImage.setImage(image);
        LImage.setFitWidth(556);
        LImage.setFitHeight(372);
        LImage.setLayoutX(-12);
        LImage.setLayoutY(21);
        AnchorPane Pane = new AnchorPane();
        Pane.setPrefWidth(537);
        Pane.setPrefHeight(390);
        Pane.getChildren().add(LImage);
        Pane.getChildren().add(LmBar);
        Pane.getChildren().add(lblLCapital);
        Pane.getChildren().add(lblLRate);
        Pane.getChildren().add(lblLInstallmentCount);
        Pane.getChildren().add(lblLMonthlyPayment);
        Pane.getChildren().add(tfLCapital);
        Pane.getChildren().add(tfLrate);
        Pane.getChildren().add(tfLIC);
        Pane.getChildren().add(tfLMP);
        Pane.getChildren().add(btnLKeyboard);
        Pane.getChildren().add(btnLCalculate);
        Pane.getChildren().add(cmbLoanSolve);
        Pane.getChildren().add(btnLBack);
        Scene LoanScene = new Scene(Pane, 537, 390);
        loanStage.setScene(LoanScene);
        loanStage.setTitle("Loan Calculator");
        loanStage.show();
        HomepageStage.close();
        //Reading the data of the last performed calculation
        DBCollection LoanTable = database.getCollection("LoanDetails");
        DBCursor findIterable = LoanTable.find();
        for (DBObject count : findIterable) {
            LoanLastOrNot = (String) count.get("Last");
            if (LoanLastOrNot.equals("Yes")) {
                tfLCapital.setText((String)count.get("Loan Amount"));
                tfLrate.setText((String)count.get("Rate"));
                tfLIC.setText((String)count.get("Installment Count"));
                tfLMP.setText((String)count.get("Monthly Payment"));
            }
        }
        //Setting up a glow to take effect when the mouse is moved over the back button
        btnLBack.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnLBack.setEffect(glowLevel2);
            }
        });
        btnLBack.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnLBack.setEffect(glow);
            }
        });
        //Navigating back to first window when back is clicked
        btnLBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loanStage.close();
                HomepageStage.show();
                tfLCapital.setText("");
                tfLrate.setText("");
                tfLIC.setText("");
                tfLMP.setText("");
            }
        });
        btnLKeyboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keyboard();
            }
        });
        //Methods to set the variables which identify which textfield is selected
        tfLCapital.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldLoan=true;
                clickedRateTextFieldLoan=false;
                clickedICTextFieldLoan =false;
                clickedMPTextFieldLoan =false;
            }
        });
        tfLrate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldLoan=false;
                clickedRateTextFieldLoan=true;
                clickedICTextFieldLoan=false;
                clickedMPTextFieldLoan =false;
            }
        });
        tfLIC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldLoan=false;
                clickedRateTextFieldLoan=false;
                clickedICTextFieldLoan =true;
                clickedMPTextFieldLoan =false;
            }
        });
        tfLMP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldLoan=false;
                clickedRateTextFieldLoan=false;
                clickedICTextFieldLoan =false;
                clickedMPTextFieldLoan =true;
            }
        });

        //Setting up the help window for the loan calculator
        LInstructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Initializing the font
                Font headingFont=new Font("Book Antiqua",15);
                Font paraFont=new Font("Book Antiqua",12);
                AnchorPane helpPane=new AnchorPane();
                helpPane.setPrefWidth(414);
                helpPane.setPrefHeight(258);
                ImageView helpIV=new ImageView();
                Image HelpImage = new Image("/footage/search-background.png");
                helpIV.setImage(HelpImage);
                helpIV.setFitWidth(490);
                helpIV.setFitHeight(295);
                Label helpHeading=new Label("Loan Calculator");
                helpHeading.setFont(headingFont);
                helpHeading.setLayoutX(196);
                helpHeading.setLayoutY(56);
                helpHeading.setPrefWidth(130);
                helpHeading.setPrefHeight(18);
                helpHeading.setTextFill(Color.WHITE);
                Label helpPara1=new Label("Instructions:- Choose what you are solving for and enter the 3 known values from capital, rate, installment count or monthly payment of the loan and click calculate in order to find the unknown value. Data of the last performed calculation can be exported to a text file by going into FIle menu>Export option.");
                helpPara1.setFont(paraFont);
                helpPara1.setLayoutX(49);
                helpPara1.setLayoutY(86);
                helpPara1.setPrefWidth(400);
                helpPara1.setPrefHeight(90);
                helpPara1.setTextFill(Color.WHITE);
                helpPara1.setTextAlignment(TextAlignment.CENTER);
                helpPara1.setWrapText(true);
                Label helpPara2=new Label("Note:- Click on the keyboard button below the calculate option in order to launch the on-screen keyboard.");
                helpPara2.setFont(paraFont);
                helpPara2.setLayoutX(46);
                helpPara2.setLayoutY(165);
                helpPara2.setPrefWidth(404);
                helpPara2.setPrefHeight(68);
                helpPara2.setTextFill(Color.WHITE);
                helpPara2.setTextAlignment(TextAlignment.CENTER);
                helpPara2.setWrapText(true);
                helpPane.getChildren().add(helpIV);
                helpPane.getChildren().add(helpHeading);
                helpPane.getChildren().add(helpPara1);
                helpPane.getChildren().add(helpPara2);
                Stage helpStage=new Stage();
                Scene helpScene=new Scene(helpPane,489,295);
                helpStage.setScene(helpScene);
                helpStage.setTitle("Help");
                helpStage.show();
            }
        });
        btnLCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if(!(cmbLoanSolve.getValue() ==null)) {
                        //If the user wishes to find the future value
                        if(cmbLoanSolve.getValue().equals("Monthly Payment")) {
                            Double capital = Double.parseDouble(tfLCapital.getText());
                            Double rate = Double.parseDouble(tfLrate.getText());
                            rate=rate/12;
                            Double IC = Double.parseDouble(tfLIC.getText());
                            Double temp1=(rate/100)*capital;
                            Double temp2=1+(rate/100);
                            temp2=Math.pow(temp2,-(IC));
                            temp2=1-temp2;
                            loanAnswer=temp1/temp2;
                            BigDecimal bd = new BigDecimal(loanAnswer).setScale(2, RoundingMode.HALF_UP);
                            loanAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Monthly Payment = " + loanAnswer + " rupees");
                            a.showAndWait();
                            tfLMP.setText(loanAnswer.toString());
                        }else if(cmbLoanSolve.getValue().equals("Installments required")){
                            Double capital = Double.parseDouble(tfLCapital.getText());
                            Double rate = Double.parseDouble(tfLrate.getText());
                            rate=rate/(12*100);
                            Double MP = Double.parseDouble(tfLMP.getText());
                            Double temp1=1/(1-((rate*capital)/MP));
                            temp1=Math.log10(temp1);
                            Double temp2=1+rate;
                            temp2=Math.log10(temp2);
                            loananswer=Math.round(temp1/temp2);
                            System.out.println(loananswer);
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Installments required = " + loananswer + " installments");
                            a.showAndWait();
                            tfLIC.setText(loananswer.toString());
                        }else if(cmbLoanSolve.getValue().equals("Interest Rate")){
                            Double capital = Double.parseDouble(tfLCapital.getText());
                            Double IC = Double.parseDouble(tfLIC.getText());
                            Double MP = Double.parseDouble(tfLMP.getText());
                            Double q=(Math.log(1+(1/IC)))/Math.log(2);
                            Double temp1=1+(MP/capital);
                            Double temp2=1/q;
                            temp1=(Math.pow(temp1,temp2))-1;
                            loanAnswer=(Math.pow(temp1,q))-1;
                            loanAnswer=loanAnswer*12*100;
                            BigDecimal bd = new BigDecimal(loanAnswer).setScale(2, RoundingMode.HALF_UP);
                            loanAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Interest Rate = " + loanAnswer + " %");
                            a.showAndWait();
                            tfLrate.setText(loanAnswer.toString());
                        }else if(cmbLoanSolve.getValue().equals("Loan Amount")){
                            Double rate = Double.parseDouble(tfLrate.getText());
                            rate=rate/(12*100);
                            Double IC = Double.parseDouble(tfLIC.getText());
                            Double MP = Double.parseDouble(tfLMP.getText());
                            Double temp1=1+rate;
                            loanAnswer=((1-(1/(Math.pow(temp1,IC))))*MP)/rate;
                            BigDecimal bd = new BigDecimal(loanAnswer).setScale(2, RoundingMode.HALF_UP);
                            loanAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Loan Amount = " + loanAnswer + " rupees");
                            a.showAndWait();
                            tfLCapital.setText(loanAnswer.toString());
                        }
                        //Writing to the database
                        DBCollection LoanTable = database.getCollection("LoanDetails");
                        DBCursor findIterable = LoanTable.find();
                        Loanfirst=true;
                        BasicDBObject basicDBObject = new BasicDBObject();
                        //If the below loop is not entered it means the the table is still empty and the record to be added next is the first record
                        //If the loop is entered, it means that a new record will be added. Therefore the 'last' flag in the last record will be changed to 'No'
                        for (DBObject count : findIterable) {
                            Loanfirst=false;
                            LoanLastOrNot = (String) count.get("Last");
                            if(LoanLastOrNot.equals("Yes")){
                                BasicDBObject query = new BasicDBObject();
                                query.put("Last", LoanLastOrNot);
                                BasicDBObject newValue = new BasicDBObject();
                                newValue.put("Last","No");
                                BasicDBObject updateObject = new BasicDBObject();
                                updateObject.put("$set", newValue);
                                database.getCollection("LoanDetails").update(query,updateObject);
                            }
                        }
                        //If first record, its ID becomes 1
                        if (Loanfirst==true) {
                            basicDBObject.put("ID", "1");
                            //If not the first record the ID of the current record is taken and then incremented and added to the new record below
                        }else{
                            for (DBObject count : findIterable) {
                                LoanIDNum = (String) count.get("ID");
                                String newID=Integer.toString(Integer.parseInt(LoanIDNum)+1);
                                basicDBObject.put("ID", newID);
                            }

                        }
                        basicDBObject.put("Loan Amount", tfLCapital.getText());
                        basicDBObject.put("Rate", tfLrate.getText());
                        basicDBObject.put("Installment Count", tfLIC.getText());
                        basicDBObject.put("Monthly Payment",tfLMP.getText());
                        basicDBObject.put("Last","Yes");
                        LoanTable.insert(basicDBObject);

                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("Please select the item for which you need the solution to!");
                        a.showAndWait();
                    }
                }catch (Exception e){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("The data entered into the textfields is either invalid or the neccessary textfields have been left empty");
                    a.showAndWait();
                    e.printStackTrace();
                }
            }
        });
        LDataExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage=new Stage();
                FileChooser fcCategoryExport = new FileChooser();
                fcCategoryExport.setTitle("Export");
                File file = fcCategoryExport.showSaveDialog(stage);
                if (file != null) {
                    try {
                        if (loanAnswer !=0.0||loananswer!=null) {
                            if(cmbLoanSolve.getValue().equals("Monthly Payment")) {
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Loan amount: " + tfLCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfLrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Installment count: " + tfLIC.getText() + " installments" + System.lineSeparator());
                                myWriter.append("Monthly payment required: " + loanAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                loanAnswer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfLCapital.setText("");
                                tfLrate.setText("");
                                tfLIC.setText("");
                                tfLMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if(cmbLoanSolve.getValue().equals("Interest Rate")){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Loan amount: " + tfLCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Installment count: " + tfLIC.getText() + " installments" + System.lineSeparator());
                                myWriter.append("Monthly payment: " + tfLMP.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Interest rate required: " + loanAnswer + "%" + System.lineSeparator());
                                myWriter.close();
                                loanAnswer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfLCapital.setText("");
                                tfLrate.setText("");
                                tfLIC.setText("");
                                tfLMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbLoanSolve.getValue().equals("Installments required"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Loan amount: " + tfLCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfLrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Monthly payment: " + tfLMP.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Installment count: " + loananswer + " installments" + System.lineSeparator());
                                myWriter.close();
                                loananswer=null;
                                //Clearing the content of the textfields once the data is exported
                                tfLCapital.setText("");
                                tfLrate.setText("");
                                tfLIC.setText("");
                                tfLMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbLoanSolve.getValue().equals("Loan Amount"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Rate: " + tfLrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Installment count: " + tfLIC.getText() + " installments" + System.lineSeparator());
                                myWriter.append("Monthly payment: " + tfLMP.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Loan amount: " + loanAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                loanAnswer=0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfLCapital.setText("");
                                tfLrate.setText("");
                                tfLIC.setText("");
                                tfLMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }
                        }else{
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.WARNING);
                            a.setContentText("Calculation is not completed yet to export");
                            a.showAndWait();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            }
        });
    }
    public void clickMortgage(){
        //Initialzing the bloom and glow effects
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.93);
        Glow glow = new Glow();
        glow.setLevel(0.3);
        Glow glowLevel2 = new Glow();
        glowLevel2.setLevel(0.7);
        btnmKeyboard.setLayoutX(188);
        btnmKeyboard.setLayoutY(271);
        btnmKeyboard.setPrefWidth(53);
        btnmKeyboard.setPrefHeight(18);
        btnmKeyboard.setEffect(bloom);
        Image imageForKeyboard = new Image("/footage/wireless-keyboard-k360-emea-glossy-black-glamour-image-lg.png");
        imgmKeyboard.setImage(imageForKeyboard);
        imgmKeyboard.setFitWidth(43);
        imgmKeyboard.setFitHeight(18);
        btnmKeyboard.setGraphic(imgmKeyboard);
        btnmKeyboard.setCursor(Cursor.HAND);
        //Setting positions,display texts,etc..... of the elements of the interest calculator window
        lblmCapital.setLayoutX(30);
        lblmCapital.setLayoutY(69);
        lblmCapital.setTextFill(Color.BLACK);
        lblmRate.setLayoutX(30);
        lblmRate.setLayoutY(103);
        lblmRate.setTextFill(Color.BLACK);
        lblmInstallmentCount.setPrefWidth(130);
        lblmInstallmentCount.setLayoutX(30);
        lblmInstallmentCount.setLayoutY(135);
        lblmInstallmentCount.setTextFill(Color.BLACK);
        lblmMonthlyPayment.setLayoutX(30);
        lblmMonthlyPayment.setLayoutY(169);
        lblmMonthlyPayment.setTextFill(Color.BLACK);
        btnmCalculate.setText("Calculate");
        tfmCapital.setPrefWidth(120);
        tfmCapital.setPrefHeight(26);
        tfmCapital.setPromptText("Rs");
        tfmCapital.setLayoutX(160);
        tfmCapital.setLayoutY(65);
        tfmrate.setPrefWidth(120);
        tfmrate.setPrefHeight(26);
        tfmrate.setPromptText("%");
        tfmrate.setLayoutX(160);
        tfmrate.setLayoutY(99);
        tfmIC.setPrefWidth(120);
        tfmIC.setPrefHeight(26);
        tfmIC.setLayoutX(160);
        tfmIC.setLayoutY(131);
        tfmMP.setPrefWidth(120);
        tfmMP.setPrefHeight(26);
        tfmMP.setPromptText("Rs");
        tfmMP.setLayoutX(160);
        tfmMP.setLayoutY(165);
        //Setting up the calculate button
        btnmCalculate.setPrefWidth(120);
        btnmCalculate.setPrefHeight(26);
        btnmCalculate.setLayoutX(160);
        btnmCalculate.setLayoutY(236);
        btnmCalculate.setStyle("-fx-background-radius: 5");
        btnmCalculate.setCursor(Cursor.HAND);
        btnmCalculate.setEffect(bloom);
        //Setting up the back button
        btnmBack.setText("Back");
        btnmBack.setPrefWidth(120);
        btnmBack.setPrefHeight(26);
        btnmBack.setLayoutX(160);
        btnmBack.setLayoutY(331);
        btnmBack.setStyle("-fx-background-radius: 5;-fx-background-color: #6bd7f2");
        btnmBack.setCursor(Cursor.HAND);
        btnmBack.setEffect(glow);
        //Setting up the dropdown box
        cmbMortgageSolve.setLayoutX(160);
        cmbMortgageSolve.setLayoutY(200);
        cmbMortgageSolve.setPrefWidth(120);
        cmbMortgageSolve.setPrefHeight(26);
        cmbMortgageSolve.setPromptText("Solve for?");
        //Setting up what needs to be displayed in the dropdown box
        ObservableList array = FXCollections.observableArrayList();
        array.add("Monthly Payment");
        array.add("Installments required");
        array.add("Interest Rate");
        array.add("Mortgage Value");
        cmbMortgageSolve.setItems(array);
        Image image = new Image("/footage/OIP.jpg");
        mImage.setImage(image);
        mImage.setFitWidth(556);
        mImage.setFitHeight(372);
        mImage.setLayoutX(-12);
        mImage.setLayoutY(21);
        AnchorPane Pane = new AnchorPane();
        Pane.setPrefWidth(537);
        Pane.setPrefHeight(390);
        Pane.getChildren().add(mImage);
        Pane.getChildren().add(mmBar);
        Pane.getChildren().add(lblmCapital);
        Pane.getChildren().add(lblmRate);
        Pane.getChildren().add(lblmInstallmentCount);
        Pane.getChildren().add(lblmMonthlyPayment);
        Pane.getChildren().add(tfmCapital);
        Pane.getChildren().add(tfmrate);
        Pane.getChildren().add(tfmIC);
        Pane.getChildren().add(tfmMP);
        Pane.getChildren().add(btnmKeyboard);
        Pane.getChildren().add(btnmCalculate);
        Pane.getChildren().add( cmbMortgageSolve);
        Pane.getChildren().add(btnmBack);
        Scene LoanScene = new Scene(Pane, 537, 390);
        mortgageStage.setScene(LoanScene);
        mortgageStage.setTitle("Mortgage Calculator");
        mortgageStage.show();
        HomepageStage.close();
        DBCollection MortgageTable = database.getCollection("MortgageDetails");
        DBCursor findIterable = MortgageTable.find();
        for (DBObject count : findIterable) {
            MortgageLastOrNot = (String) count.get("Last");
            if (MortgageLastOrNot.equals("Yes")) {
                tfmCapital.setText((String)count.get("Mortgage Value"));
                tfmrate.setText((String)count.get("Rate"));
                tfmIC.setText((String)count.get("Installment Count"));
                tfmMP.setText((String)count.get("Monthly Payment"));
            }
        }
        //Setting up a glow to take effect when the mouse is moved over the back button
        btnmBack.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnmBack.setEffect(glowLevel2);
            }
        });
        btnmBack.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnmBack.setEffect(glow);
            }
        });
        //Navigating back to first window when back is clicked
        btnmBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mortgageStage.close();
                HomepageStage.show();
                tfmCapital.setText("");
                tfmrate.setText("");
                tfmIC.setText("");
                tfmMP.setText("");
            }
        });
        btnmKeyboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                keyboard();
            }
        });
        //Methods to set the variables which identify which textfield is selected
        tfmCapital.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldMortgage=true;
                clickedRateTextFieldMortgage=false;
                clickedICTextFieldMortgage =false;
                clickedMPTextFieldMortgage =false;
            }
        });
        tfmrate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldMortgage=false;
                clickedRateTextFieldMortgage=true;
                clickedICTextFieldMortgage=false;
                clickedMPTextFieldMortgage =false;
            }
        });
        tfmIC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldMortgage=false;
                clickedRateTextFieldMortgage=false;
                clickedICTextFieldMortgage =true;
                clickedMPTextFieldMortgage =false;
            }
        });
        tfmMP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedCapitalTextFieldMortgage=false;
                clickedRateTextFieldMortgage=false;
                clickedICTextFieldMortgage =false;
                clickedMPTextFieldMortgage =true;
            }
        });

        //Setting up the help window for the mortgage calculator
        mInstructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Initializing the font
                Font headingFont=new Font("Book Antiqua",15);
                Font paraFont=new Font("Book Antiqua",12);
                AnchorPane helpPane=new AnchorPane();
                helpPane.setPrefWidth(414);
                helpPane.setPrefHeight(258);
                ImageView helpIV=new ImageView();
                Image HelpImage = new Image("/footage/search-background.png");
                helpIV.setImage(HelpImage);
                helpIV.setFitWidth(490);
                helpIV.setFitHeight(295);
                Label helpHeading=new Label("Mortgage Calculator");
                helpHeading.setFont(headingFont);
                helpHeading.setLayoutX(180);
                helpHeading.setLayoutY(56);
                helpHeading.setPrefWidth(140);
                helpHeading.setPrefHeight(18);
                helpHeading.setTextFill(Color.WHITE);
                Label helpPara1=new Label("Instructions:- Choose what you are solving for and enter the 3 known values from mortgage value, rate, installment count or monthly payment of the mortgage and click calculate in order to find the unknown value. Data of the last performed calculation can be exported to a text file by going into FIle menu>Export option.");
                helpPara1.setFont(paraFont);
                helpPara1.setLayoutX(49);
                helpPara1.setLayoutY(86);
                helpPara1.setPrefWidth(400);
                helpPara1.setPrefHeight(90);
                helpPara1.setTextFill(Color.WHITE);
                helpPara1.setTextAlignment(TextAlignment.CENTER);
                helpPara1.setWrapText(true);
                Label helpPara2=new Label("Note:- Click on the keyboard button below the calculate option in order to launch the on-screen keyboard.");
                helpPara2.setFont(paraFont);
                helpPara2.setLayoutX(46);
                helpPara2.setLayoutY(165);
                helpPara2.setPrefWidth(404);
                helpPara2.setPrefHeight(68);
                helpPara2.setTextFill(Color.WHITE);
                helpPara2.setTextAlignment(TextAlignment.CENTER);
                helpPara2.setWrapText(true);
                helpPane.getChildren().add(helpIV);
                helpPane.getChildren().add(helpHeading);
                helpPane.getChildren().add(helpPara1);
                helpPane.getChildren().add(helpPara2);
                Stage helpStage=new Stage();
                Scene helpScene=new Scene(helpPane,489,295);
                helpStage.setScene(helpScene);
                helpStage.setTitle("Help");
                helpStage.show();
            }
        });
        btnmCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if(!(cmbMortgageSolve.getValue() ==null)) {
                        //If the user wishes to find the monthly payment
                        if(cmbMortgageSolve.getValue().equals("Monthly Payment")) {
                            Double capital = Double.parseDouble(tfmCapital.getText());
                            Double rate = Double.parseDouble(tfmrate.getText());
                            rate=rate/12;
                            Double IC = Double.parseDouble(tfmIC.getText());
                            Double temp1=(rate/100)*capital;
                            Double temp2=1+(rate/100);
                            temp2=Math.pow(temp2,-(IC));
                            temp2=1-temp2;
                            mortgageAnswer=temp1/temp2;
                            BigDecimal bd = new BigDecimal(mortgageAnswer).setScale(2, RoundingMode.HALF_UP);
                            mortgageAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Monthly Payment = " + mortgageAnswer + " rupees");
                            a.showAndWait();
                            tfmMP.setText(mortgageAnswer.toString());
                            //If the user wishes to find the installments required
                        }else if(cmbMortgageSolve.getValue().equals("Installments required")){
                            Double capital = Double.parseDouble(tfmCapital.getText());
                            Double rate = Double.parseDouble(tfmrate.getText());
                            rate=rate/(12*100);
                            Double MP = Double.parseDouble(tfmMP.getText());
                            Double temp1=1/(1-((rate*capital)/MP));
                            temp1=Math.log10(temp1);
                            Double temp2=1+rate;
                            temp2=Math.log10(temp2);
                            mortgageanswer=Math.round(temp1/temp2);
                            System.out.println(mortgageanswer);
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Installments required = " + mortgageanswer + " installments");
                            a.showAndWait();
                            tfmIC.setText(mortgageanswer.toString());
                            //If the user wishes to find the interest rate
                        }else if(cmbMortgageSolve.getValue().equals("Interest Rate")){
                            Double capital = Double.parseDouble(tfmCapital.getText());
                            Double IC = Double.parseDouble(tfmIC.getText());
                            Double MP = Double.parseDouble(tfmMP.getText());
                            Double q=(Math.log(1+(1/IC)))/Math.log(2);
                            Double temp1=1+(MP/capital);
                            Double temp2=1/q;
                            temp1=(Math.pow(temp1,temp2))-1;
                            mortgageAnswer=(Math.pow(temp1,q))-1;
                            mortgageAnswer=mortgageAnswer*12*100;
                            BigDecimal bd = new BigDecimal(mortgageAnswer).setScale(2, RoundingMode.HALF_UP);
                            mortgageAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Interest Rate = " + loanAnswer + " %");
                            a.showAndWait();
                            tfmrate.setText(mortgageAnswer.toString());
                            //If the user wishes to find the mortgage value
                        }else if(cmbMortgageSolve.getValue().equals("Mortgage Value")){
                            Double rate = Double.parseDouble(tfmrate.getText());
                            rate=rate/(12*100);
                            Double IC = Double.parseDouble(tfmIC.getText());
                            Double MP = Double.parseDouble(tfmMP.getText());
                            Double temp1=1+rate;
                            mortgageAnswer=((1-(1/(Math.pow(temp1,IC))))*MP)/rate;
                            BigDecimal bd = new BigDecimal(mortgageAnswer).setScale(2, RoundingMode.HALF_UP);
                            mortgageAnswer=bd.doubleValue();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Mortgage Value = " + mortgageAnswer + " rupees");
                            a.showAndWait();
                            tfmCapital.setText(mortgageAnswer.toString());
                        }
                        //Writing to the database
                        DBCollection MortgageTable = database.getCollection("MortgageDetails");
                        DBCursor findIterable = MortgageTable.find();
                        Mortgagefirst=true;
                        BasicDBObject basicDBObject = new BasicDBObject();
                        //If the below loop is not entered it means the the table is still empty and the record to be added next is the first record
                        //If the loop is entered, it means that a new record will be added. Therefore the 'last' flag in the last record will be changed to 'No'
                        for (DBObject count : findIterable) {
                            Mortgagefirst=false;
                            MortgageLastOrNot = (String) count.get("Last");
                            if(MortgageLastOrNot.equals("Yes")){
                                BasicDBObject query = new BasicDBObject();
                                query.put("Last", MortgageLastOrNot);
                                BasicDBObject newValue = new BasicDBObject();
                                newValue.put("Last","No");
                                BasicDBObject updateObject = new BasicDBObject();
                                updateObject.put("$set", newValue);
                                database.getCollection("MortgageDetails").update(query,updateObject);
                            }
                        }
                        //If first record, its ID becomes 1
                        if (Mortgagefirst==true) {
                            basicDBObject.put("ID", "1");
                            //If not the first record the ID of the current record is taken and then incremented and added to the new record below
                        }else{
                            for (DBObject count : findIterable) {
                                MortgageIDNum = (String) count.get("ID");
                                String newID=Integer.toString(Integer.parseInt(MortgageIDNum)+1);
                                basicDBObject.put("ID", newID);
                            }

                        }
                        basicDBObject.put("Mortgage Value", tfmCapital.getText());
                        basicDBObject.put("Rate", tfmrate.getText());
                        basicDBObject.put("Installment Count", tfmIC.getText());
                        basicDBObject.put("Monthly Payment",tfmMP.getText());
                        basicDBObject.put("Last","Yes");
                        MortgageTable.insert(basicDBObject);

                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("Please select the item for which you need the solution to!");
                        a.showAndWait();
                    }
                }catch (Exception e){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("The data entered into the textfields is either invalid or the neccessary textfields have been left empty");
                    a.showAndWait();
                    e.printStackTrace();
                }
            }
        });
        mDataExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage=new Stage();
                FileChooser fcCategoryExport = new FileChooser();
                fcCategoryExport.setTitle("Export");
                File file = fcCategoryExport.showSaveDialog(stage);
                if (file != null) {
                    try {
                        if (mortgageAnswer !=0.0||mortgageanswer!=null) {
                            if(cmbMortgageSolve.getValue().equals("Monthly Payment")) {
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Mortgage Value: " + tfmCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfmrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Installment count: " + tfmIC.getText() + " installments" + System.lineSeparator());
                                myWriter.append("Monthly payment required: " + mortgageAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                mortgageAnswer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfmCapital.setText("");
                                tfmrate.setText("");
                                tfmIC.setText("");
                                tfmMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if(cmbMortgageSolve.getValue().equals("Interest Rate")){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Mortgage Value: " + tfmCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Installment count: " + tfmIC.getText() + " installments" + System.lineSeparator());
                                myWriter.append("Monthly payment: " + tfmMP.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Interest rate required: " + mortgageAnswer + "%" + System.lineSeparator());
                                myWriter.close();
                                mortgageAnswer = 0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfmCapital.setText("");
                                tfmrate.setText("");
                                tfmIC.setText("");
                                tfmMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbMortgageSolve.getValue().equals("Installments required"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Mortgage Value: " + tfmCapital.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Rate: " + tfmrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Monthly payment: " + tfmMP.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Installment count: " + mortgageanswer + " installments" + System.lineSeparator());
                                myWriter.close();
                                mortgageanswer=null;
                                //Clearing the content of the textfields once the data is exported
                                tfmCapital.setText("");
                                tfmrate.setText("");
                                tfmIC.setText("");
                                tfmMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }else if((cmbMortgageSolve.getValue().equals("Loan Amount"))){
                                FileWriter myWriter = new FileWriter(file.getAbsolutePath());
                                myWriter.write("Calculation Details" + System.lineSeparator() + System.lineSeparator());
                                myWriter.append("Rate: " + tfmrate.getText() + "%" + System.lineSeparator());
                                myWriter.append("Installment count: " + tfmIC.getText() + " installments" + System.lineSeparator());
                                myWriter.append("Monthly payment: " + tfmMP.getText() + " rupees" + System.lineSeparator());
                                myWriter.append("Mortgage Value: " + mortgageAnswer + " rupees" + System.lineSeparator());
                                myWriter.close();
                                loanAnswer=0.0;
                                //Clearing the content of the textfields once the data is exported
                                tfmCapital.setText("");
                                tfmrate.setText("");
                                tfmIC.setText("");
                                tfmMP.setText("");
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.INFORMATION);
                                a.setContentText("Data exported successfully");
                                a.showAndWait();
                            }
                        }else{
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.WARNING);
                            a.setContentText("Calculation is not completed yet to export");
                            a.showAndWait();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            }
        });
    }
    public void keyboard(){
        //Initializing effects
        Bloom bloom=new Bloom();
        bloom.setThreshold(0.93);
        //Setting Font
        Font keyFont=new Font("System",20);
        //Key 1
        Button key1=new Button();
        key1.setLayoutX(0);
        key1.setLayoutY(0);
        key1.setPrefWidth(60);
        key1.setPrefHeight(60);
        key1.setText("1");
        key1.setFont(keyFont);
        key1.setCursor(Cursor.HAND);
        key1.setEffect(bloom);
        //Key 2
        Button key2=new Button();
        key2.setLayoutX(61);
        key2.setLayoutY(0);
        key2.setPrefWidth(60);
        key2.setPrefHeight(60);
        key2.setText("2");
        key2.setFont(keyFont);
        key2.setCursor(Cursor.HAND);
        key2.setEffect(bloom);
        //Key 3
        Button key3=new Button();
        key3.setLayoutX(121);
        key3.setLayoutY(0);
        key3.setPrefWidth(60);
        key3.setPrefHeight(60);
        key3.setText("3");
        key3.setFont(keyFont);
        key3.setCursor(Cursor.HAND);
        key3.setEffect(bloom);
        //Key 4
        Button key4=new Button();
        key4.setLayoutX(0);
        key4.setLayoutY(60);
        key4.setPrefWidth(60);
        key4.setPrefHeight(60);
        key4.setText("4");
        key4.setFont(keyFont);
        key4.setCursor(Cursor.HAND);
        key4.setEffect(bloom);
        //Key 5
        Button key5=new Button();
        key5.setLayoutX(61);
        key5.setLayoutY(60);
        key5.setPrefWidth(60);
        key5.setPrefHeight(60);
        key5.setText("5");
        key5.setFont(keyFont);
        key5.setCursor(Cursor.HAND);
        key5.setEffect(bloom);
        //Key 6
        Button key6=new Button();
        key6.setLayoutX(121);
        key6.setLayoutY(60);
        key6.setPrefWidth(60);
        key6.setPrefHeight(60);
        key6.setText("6");
        key6.setFont(keyFont);
        key6.setCursor(Cursor.HAND);
        key6.setEffect(bloom);
        //Key 7
        Button key7=new Button();
        key7.setLayoutX(0);
        key7.setLayoutY(120);
        key7.setPrefWidth(60);
        key7.setPrefHeight(60);
        key7.setText("7");
        key7.setFont(keyFont);
        key7.setCursor(Cursor.HAND);
        key7.setEffect(bloom);
        //Key 8
        Button key8=new Button();
        key8.setLayoutX(61);
        key8.setLayoutY(120);
        key8.setPrefWidth(60);
        key8.setPrefHeight(60);
        key8.setText("8");
        key8.setFont(keyFont);
        key8.setCursor(Cursor.HAND);
        key8.setEffect(bloom);
        //key 9
        Button key9=new Button();
        key9.setLayoutX(121);
        key9.setLayoutY(120);
        key9.setPrefWidth(60);
        key9.setPrefHeight(60);
        key9.setText("9");
        key9.setFont(keyFont);
        key9.setCursor(Cursor.HAND);
        key9.setEffect(bloom);
        //Key 0
        Button key0=new Button();
        key0.setLayoutX(0);
        key0.setLayoutY(180);
        key0.setPrefWidth(60);
        key0.setPrefHeight(60);
        key0.setText("0");
        key0.setFont(keyFont);
        key0.setCursor(Cursor.HAND);
        key0.setEffect(bloom);
        // Decimal point key
        Button keyDot=new Button();
        keyDot.setLayoutX(61);
        keyDot.setLayoutY(180);
        keyDot.setPrefWidth(60);
        keyDot.setPrefHeight(60);
        keyDot.setText(".");
        keyDot.setFont(keyFont);
        keyDot.setCursor(Cursor.HAND);
        keyDot.setEffect(bloom);
        //Erase Key
        Button keyErase=new Button();
        keyErase.setLayoutX(121);
        keyErase.setLayoutY(180);
        keyErase.setPrefWidth(60);
        keyErase.setPrefHeight(60);
        keyErase.setText("E");
        keyErase.setFont(keyFont);
        keyErase.setCursor(Cursor.HAND);
        keyErase.setEffect(bloom);
        //Go Key
        Button keyGO=new Button();
        keyGO.setLayoutX(181);
        keyGO.setLayoutY(1);
        keyGO.setPrefWidth(60);
        keyGO.setPrefHeight(286);
        keyGO.setText("GO");
        keyGO.setFont(keyFont);
        keyGO.setCursor(Cursor.HAND);
        keyGO.setEffect(bloom);
        //Data collection Textfield
        TextField txtDataEntry=new TextField();
        txtDataEntry.setLayoutX(0);
        txtDataEntry.setLayoutY(240);
        txtDataEntry.setPrefWidth(183);
        txtDataEntry.setPrefHeight(48);

        AnchorPane keyboardPane=new AnchorPane();
        keyboardPane.setPrefWidth(241);
        keyboardPane.setPrefHeight(286);
        keyboardPane.getChildren().add(key1);
        keyboardPane.getChildren().add(key2);
        keyboardPane.getChildren().add(key3);
        keyboardPane.getChildren().add(key4);
        keyboardPane.getChildren().add(key5);
        keyboardPane.getChildren().add(key6);
        keyboardPane.getChildren().add(key7);
        keyboardPane.getChildren().add(key8);
        keyboardPane.getChildren().add(key9);
        keyboardPane.getChildren().add(key0);
        keyboardPane.getChildren().add(keyDot);
        keyboardPane.getChildren().add(keyErase);
        keyboardPane.getChildren().add(keyGO);
        keyboardPane.getChildren().add(txtDataEntry);
        Scene keyboardScene=new Scene(keyboardPane,241,286);
        Stage keyboardStage=new Stage();
        keyboardStage.setScene(keyboardScene);
        keyboardStage.setTitle("On Screen Keyboard");
        keyboardStage.show();
        btnKeyboard.setDisable(true);
        //Method to enable the button back is the keyboard window is closed
        keyboardStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                btnKeyboard.setDisable(false);
            }
        });
        //Method to write 1 when key 1 is pressed
        key1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"1";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 2 when key 2 is pressed
        key2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"2";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 3 when key 3 is pressed
        key3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"3";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 4 when key 4 is pressed
        key4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"4";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 5 when key 5 is pressed
        key5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"5";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 6 when key 6 is pressed
        key6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"6";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 7 when key 7 is pressed
        key7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"7";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 8 when key 8 is pressed
        key8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"8";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 9 when key 9 is pressed
        key9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"9";
                txtDataEntry.setText(number);
            }
        });
        //Method to write 0 when key 0 is pressed
        key0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+"0";
                txtDataEntry.setText(number);
            }
        });
        //Method to write decimal point when decimal point key is pressed
        keyDot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                number=number+".";
                txtDataEntry.setText(number);
            }
        });
        //Method to erase last number when erase key is pressed
        keyErase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                if (!(number.length()==0)) {
                    number = number.substring(0, (number.length() - 1));
                    txtDataEntry.setText(number);
                }
            }
        });
        //Method to write contents back to the original textfield in the calculator window
        keyGO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number=txtDataEntry.getText();
                //If user selected the FD calculator the clicked textfield in it is searched for and the number put there
                if(clickedFD==true) {
                    if (clickedCapitalTextField == false && clickedRateTextField == false && clickedPeriodTextField == false && clickedFVTextField == false) {
                        tfCapital.setText(number);
                    } else if (clickedCapitalTextField == true) {
                        tfCapital.setText(number);
                    } else if (clickedRateTextField == true) {
                        tfrate.setText(number);
                    } else if (clickedPeriodTextField == true) {
                        tfYrs.setText(number);
                    } else if (clickedFVTextField == true) {
                        tfFV.setText(number);
                    }
                    //If user selected the Savings calculator the clicked textfield in it is searched for and the number put there
                }else if(clickedSavings==true){
                    if (clickedCapitalTextFieldSavings == false && clickedRateTextFieldSavings == false && clickedPeriodTextFieldSavings== false && clickedFVTextFieldSavings == false &&clickedMATextFieldSavings==false) {
                        tfSCapital.setText(number);
                    } else if (clickedCapitalTextFieldSavings == true) {
                        tfSCapital.setText(number);
                    } else if (clickedRateTextFieldSavings == true) {
                        tfSrate.setText(number);
                    } else if (clickedPeriodTextFieldSavings == true) {
                        tfSYrs.setText(number);
                    } else if (clickedFVTextFieldSavings == true) {
                        tfSFV.setText(number);
                    }else if (clickedMATextFieldSavings==true){
                        tfSMA.setText(number);
                    }
                    //If user selected the Loan calculator the clicked textfield in it is searched for and the number put there
                }else if(clickedLoan==true){
                    if (clickedCapitalTextFieldLoan == false && clickedRateTextFieldLoan == false && clickedICTextFieldLoan== false && clickedMPTextFieldLoan == false) {
                        tfLCapital.setText(number);
                    } else if (clickedCapitalTextFieldLoan == true) {
                        tfLCapital.setText(number);
                    } else if (clickedRateTextFieldLoan == true) {
                        tfLrate.setText(number);
                    } else if (clickedICTextFieldLoan == true) {
                        tfLIC.setText(number);
                    }else if (clickedMPTextFieldLoan==true){
                        tfLMP.setText(number);
                    }
                    //If user selected the Mortgage calculator the clicked textfield in it is searched for and the number put there
                }else if(clickedMortgage==true){
                    if (clickedCapitalTextFieldMortgage == false && clickedRateTextFieldMortgage == false && clickedICTextFieldMortgage== false && clickedMPTextFieldMortgage == false) {
                        tfmCapital.setText(number);
                    } else if (clickedCapitalTextFieldMortgage == true) {
                        tfmCapital.setText(number);
                    } else if (clickedRateTextFieldMortgage == true) {
                        tfmrate.setText(number);
                    } else if (clickedICTextFieldMortgage == true) {
                        tfmIC.setText(number);
                    }else if (clickedMPTextFieldMortgage==true){
                        tfmMP.setText(number);
                    }
                }
                keyboardStage.close();
                btnKeyboard.setDisable(false);
            }
        });
    }
}
