package com.akareset.tictactoe.tictactoe_minmax2.View;

import com.akareset.tictactoe.tictactoe_minmax2.Controller.Logic;
import com.akareset.tictactoe.tictactoe_minmax2.Controller.MinMaxLogic;
import com.akareset.tictactoe.tictactoe_minmax2.Model.CellState;
import com.akareset.tictactoe.tictactoe_minmax2.Model.PlayField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    PlayField playField = new PlayField();
    CellState userSign = CellState.X; // by default
    MinMaxLogic logic = new MinMaxLogic(userSign);
    Button[][] buttons = new Button[3][3];
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.centerOnScreen();
        primaryStage.setTitle("TicTacToe");
        primaryStage.setHeight(420);
        primaryStage.setWidth(320);
        BorderPane root = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));

        for(int y = 0; y<3; y++){
            for(int x = 0; x<3; x++){
                Button button = new Button();
                button.setPrefSize(80,80);
                button.setStyle("-fx-font-size: 36px;");
                int finalX = x;
                int finalY = y;
                button.setOnAction(event -> {
                    if (!logic.terminal(playField) && playField.getState(finalX, finalY) == CellState.NP) {
                        playField.setState(userSign, finalX, finalY);
                        updateButtons();
                        new Thread(() -> {
                            System.out.println("nextStep called");
                            PlayField newField = logic.nextStep(playField);
                            Platform.runLater(() -> {
                                playField = newField;
                                updateButtons();
                            });
                        }).start();
                    }
                });
                buttons[y][x] = button;
                gridPane.add(buttons[y][x], x,y);
            }
        }
        CheckBox checkBox = new CheckBox("Computer goes first");
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                logic.setTurn(false);
                newGame();
            } else {
                logic.setTurn(true);
                newGame();
            }
        });
        HBox top = new HBox(20);
        Button newButton = new Button("New Game");
        newButton.setPrefSize(80,40);
        newButton.setOnAction(event -> {
            newGame();
        });

        top.getChildren().addAll(newButton, checkBox);
        top.setPadding(new Insets(10, 10, 10, 10));
        top.setAlignment(Pos.CENTER);
        top.setStyle("-fx-background-color: #CBD0E8;");
        //newGame.setVisible(false);
        root.setTop(top);
        root.setCenter(gridPane);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void updateButtons(){
        for(int y=0; y<3; y++){
            for(int x = 0; x<3;x++){
                buttons[y][x].setText(playField.getState(x,y).getField());
            }
        }
        System.out.println(playField);
    }
    public void newGame(){
        if(logic.getTurn()) {
            playField = new PlayField();
            updateButtons();
        }
        else{
            playField = new PlayField();
            playField = logic.nextStep(playField);
            updateButtons();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}