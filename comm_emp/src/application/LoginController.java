package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Pane loginPane;

	public void registration(ActionEvent event) throws IOException, InterruptedException {
		Parent registration_page_parent = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
		Scene registration_page_scene = new Scene(registration_page_parent);
		registration_page_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(registration_page_scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();

	}
	
	@FXML
	private Button exitB;
	@FXML
	private Label warning;
	@FXML
	private TextField emailfield;
	@FXML
	private TextField passwordfield;

	

	@FXML
	private void closeButtonAction(){
	
	    Stage stage = (Stage) exitB.getScene().getWindow();
	 
	    stage.close();
	}
	public void authentication(ActionEvent event) throws IOException, InterruptedException {
		Parent registration_page_parent = FXMLLoader.load(getClass().getResource("Authorised.fxml"));
		Scene registration_page_scene = new Scene(registration_page_parent);

		System.out.println(emailfield.getText());
		System.out.println(passwordfield.getText());

		String user = emailfield.getText();
		String pass = passwordfield.getText();

		Connection connection = null;
		try {	
			java.sql.Statement stmt = null;
			ResultSet rs = null;
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "18052010M+m");
			stmt = connection.createStatement();

			String query = "select password from test where name=" + "'" + user + "'";

			rs = stmt.executeQuery(query);
//			Alert alert = new Alert(AlertType.ERROR);
			if (rs.next()) {
				System.out.println("database returns a value! ! ! ");
				String passcheck = rs.getString("password");

				if (passcheck.equals(pass)) {
					System.out.println("equals");
					registration_page_scene.getStylesheets()
							.add(getClass().getResource("application.css").toExternalForm());
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setScene(registration_page_scene);
					primaryStage.setFullScreen(true);
					primaryStage.setFullScreenExitHint("");
					primaryStage.show();

				}

				else {
					System.out.println("no");
				}

			} else
				
				
//			alert.setTitle("Login Failure");
//			alert.setHeaderText("Please Check Your Login Credentials");
//		
//
//			alert.showAndWait();
				warning.setText("Check your Username and Password !");
			passwordfield.setText("");
			
				System.out.println("Check your Username and Password");

		} catch (Exception e) {
			
			
			
			System.out.println("check!!!!");
			e.printStackTrace();

		}

	}

	public void KeyEvent() {
		passwordfield.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {

				}
			}
		});
	}

}