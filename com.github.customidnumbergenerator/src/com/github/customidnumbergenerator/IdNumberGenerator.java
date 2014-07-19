package com.github.customidnumbergenerator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IdNumberGenerator extends Application {
	
	private TextField generatedIdNumberTextField;
	private TextField individualFormatTextField;
	private LabelCustom chosenLabel;
	
	private Parent createContent() {
		
		String style = IdNumberGenerator.class.getResource("/com/github/customidnumbergenerator/Style.css").toExternalForm();
		
		/*
		 * LAYOUT
		 */
		BorderPane layout = new BorderPane();
		layout.setId("layout");
		layout.getStylesheets().add(style);
		
		/*
		 * LAYOUT -> TOP
		 */
		generatedIdNumberTextField = new TextField();
		generatedIdNumberTextField.setText("[generated sequence will be shown here]");
		generatedIdNumberTextField.setEditable(false);
		generatedIdNumberTextField.setId("textfield");
		generatedIdNumberTextField.getStylesheets().add(style);
		generatedIdNumberTextField.setAlignment(Pos.CENTER);
		generatedIdNumberTextField.setDisable(true);
		
		/*
		 * LAYOUT -> CENTER
		 */
		VBox vbox = new VBox(10);
		
		// LAYOUT -> CENTER -> TOP
		HBox hboxItemsToChoose = new HBox(5);
		
		Label item_1 = new Label("Date");
		item_1.setTooltip(new Tooltip("Drag'n'drop to the box below"));
		item_1.setId("label");
		item_1.getStylesheets().add(style);
		item_1.setMaxWidth(Double.MAX_VALUE);
		item_1.setAlignment(Pos.CENTER);
		item_1.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Dragboard db = item_1.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(item_1.getText());
				db.setContent(content);
				me.consume();
			}
		});
		
		Label item_2 = new Label("Text");
		item_2.setTooltip(new Tooltip("Drag'n'drop to the box below"));
		item_2.setId("label");
		item_2.getStylesheets().add(style);
		item_2.setMaxWidth(Double.MAX_VALUE);
		item_2.setAlignment(Pos.CENTER);
		item_2.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Dragboard db = item_2.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(item_2.getText());
				db.setContent(content);
				me.consume();
			}
		});
		
		Label item_3 = new Label("Random");
		item_3.setTooltip(new Tooltip("Drag'n'drop to the box below"));
		item_3.setId("label");
		item_3.getStylesheets().add(style);
		item_3.setMaxWidth(Double.MAX_VALUE);
		item_3.setAlignment(Pos.CENTER);
		item_3.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Dragboard db = item_3.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(item_3.getText());
				db.setContent(content);
				me.consume();
			}
		});
		
		Label item_4 = new Label(" / ");
		item_4.setTooltip(new Tooltip("Drag'n'drop to the box below"));
		item_4.setId("label");
		item_4.getStylesheets().add(style);
		item_4.setMaxWidth(Double.MAX_VALUE);
		item_4.setAlignment(Pos.CENTER);
		item_4.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Dragboard db = item_4.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(item_4.getText());
				db.setContent(content);
				me.consume();
			}
		});
		
		hboxItemsToChoose.getChildren().addAll(item_1, item_2, item_3, item_4);
		HBox.setHgrow(item_1, Priority.ALWAYS);
		HBox.setHgrow(item_2, Priority.ALWAYS);
		HBox.setHgrow(item_3, Priority.ALWAYS);
		hboxItemsToChoose.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(hboxItemsToChoose);
		
		// LAYOUT -> CENTER -> BOTTOM
		
		HBox hboxItemsToGenerate = new HBox(5);
		hboxItemsToGenerate.setMinHeight(35);
		hboxItemsToGenerate.setMinWidth(vbox.getWidth());
		hboxItemsToGenerate.setId("hbox_dnd");
		hboxItemsToGenerate.getStylesheets().add(style);
		hboxItemsToGenerate.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent de) {
				if (de.getGestureSource() != hboxItemsToGenerate && de.getDragboard().hasString()) {
					de.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				de.consume();
			}
		});
		hboxItemsToGenerate.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent de) {
				if (de.getGestureSource() != hboxItemsToGenerate && de.getDragboard().hasString()) {
					hboxItemsToGenerate.setStyle("-fx-background-color: #3a3a3a;");
				}
				de.consume();
			}
		});
		hboxItemsToGenerate.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent de) {
				hboxItemsToGenerate.setStyle("-fx-background-color: #1d1d1d;");
				de.consume();
			}
		});
		hboxItemsToGenerate.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent de) {
				Dragboard db = de.getDragboard();
				if (db.hasString()) {
					LabelCustom label;
					if (db.getString().equals("Date")) {
						label = new LabelCustom("Date");
						label.setInputText(currentDate("-"));
					} else if (db.getString().equals("Text")) {
						label = new LabelCustom("Text");
						label.setInputText("enter your text here");
					} else if (db.getString().equals("Random")) {
						label = new LabelCustom("Random");
						label.setInputText("xxxx");
					} else {
						label = new LabelCustom("/");
						label.setInputText("/");
					}
					label.setId("label");
					label.getStylesheets().add(style);
					label.setAlignment(Pos.CENTER);
					label.setText(db.getString());
					label.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent me) {
							individualFormatTextField.setDisable(false);
							individualFormatTextField.setText(label.getInputText());
							chosenLabel = label;
						}
					});
					hboxItemsToGenerate.getChildren().add(label);
				}
				de.setDropCompleted(true);
				de.consume();
			}
		});
		hboxItemsToGenerate.setAlignment(Pos.CENTER_LEFT);
		
		individualFormatTextField = new TextField();
		individualFormatTextField.setPromptText("[individual text/format input]");
		individualFormatTextField.setId("textfield");
		individualFormatTextField.getStylesheets().add(style);
		individualFormatTextField.setAlignment(Pos.CENTER);
		individualFormatTextField.setStyle("-fx-text-fill: #0080ff;");
		individualFormatTextField.setDisable(true);
		individualFormatTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (chosenLabel.getType().equals("Random")) {
					if (ke.getCode().isLetterKey() || ke.getCode().isArrowKey() || ke.getCode().equals(KeyCode.BACK_SPACE)) {
						String str = individualFormatTextField.getText();
						
						str = str.replaceAll(ke.getCode().toString(), "x");
						individualFormatTextField.setText(str);
						
						str = str.replaceAll(ke.getCode().toString().toLowerCase(), "x");
						individualFormatTextField.setText(str);
						
						individualFormatTextField.positionCaret(str.length());
						chosenLabel.setInputText(individualFormatTextField.getText());
					} else {
						String str = individualFormatTextField.getText();
						
						str = str.substring(0, str.length() - 1);
						individualFormatTextField.setText(str);
						
						individualFormatTextField.positionCaret(str.length());
						chosenLabel.setInputText(individualFormatTextField.getText());
					}
				} else {
					chosenLabel.setInputText(individualFormatTextField.getText());
				}
			}
		});
		
		vbox.getChildren().addAll(hboxItemsToGenerate, individualFormatTextField);
		
		/*
		 * LAYOUT -> BOTTOM
		 */
		HBox hboxButtons = new HBox(10);
		
		Button generateButton = new Button("Generate");
		generateButton.setId("button");
		generateButton.getStylesheets().add(style);
		generateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				String sequence = "";
				int size = hboxItemsToGenerate.getChildren().size();
				LabelCustom labelCustom;
				
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						labelCustom = (LabelCustom) hboxItemsToGenerate.getChildren().get(i);
						if (labelCustom.getType().equals("Random")) {
							sequence += idGenerator(labelCustom.getInputText().length());
						} else {
							sequence += labelCustom.getInputText();
						}
					}
					generatedIdNumberTextField.setDisable(false);
					generatedIdNumberTextField.setText(sequence);
				}
			}
		});
		
		Button clearallButton = new Button("Clear All");
		clearallButton.setId("button");
		clearallButton.getStylesheets().add(style);
		clearallButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				hboxItemsToGenerate.getChildren().clear();
				
				generatedIdNumberTextField.setText("[generated sequence will be shown here]");
				generatedIdNumberTextField.setDisable(true);
				
				individualFormatTextField.setText(null);
				individualFormatTextField.setDisable(true);
			}
		});
		
		Button exitButton = new Button("Exit");
		exitButton.setId("button");
		exitButton.getStylesheets().add(style);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				System.exit(-1);
			}
		});
		
		hboxButtons.getChildren().addAll(generateButton, clearallButton, exitButton);
		hboxButtons.setAlignment(Pos.CENTER);
		
		// Add all main containers to layout
		layout.setTop(generatedIdNumberTextField);
		layout.setCenter(vbox);
		layout.setBottom(hboxButtons);
		
		BorderPane.setMargin(generatedIdNumberTextField, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(vbox, new Insets(10, 10, 10, 10));
		BorderPane.setMargin(hboxButtons, new Insets(10, 10, 15, 10));
		
		return layout;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(createContent()));
		stage.setWidth(400);
		stage.setHeight(255);
		stage.setTitle("Custom ID Number Generator");
		stage.getIcons().add(new Image("/com/github/customidnumbergenerator/Icon_Logo.png"));
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
	/**
	 * GENERATE UNIQUE ID NUMBER BASED 62 CHARACTERS
	 * @param length
	 * @return
	 */
	public String idGenerator(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int l = 0; l < length; l++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String id = sb.toString();
		return id;
	}
	
	/**
	 * GET CURRENT DATE
	 * @param separator
	 * @return
	 */
	public String currentDate(String separator) {
		Calendar date = new GregorianCalendar();
		String day = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(date.get(Calendar.MONTH) + 1);
		String year = Integer.toString(date.get(Calendar.YEAR));
		
		// Add additional "0" for one digit months of the year
		if (month.length() < 2) {
			month = "0" + month;
		}
		
		// Add additional "0" for one digit days of the month
		if (day.length() < 2) {
			day = "0" + day;
		}
		
		// Initialize and return final date String
		String regDate = year + separator + month + separator + day;
		return regDate;
	}
}
