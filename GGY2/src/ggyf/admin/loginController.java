package ggyf.admin;

import java.io.IOException;

import ggyf.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class loginController {

	private Main main;

	@FXML
	public TextField _username;

	@FXML
	public PasswordField _password;

	@FXML
	public void admin() throws IOException {

		String user = _username.getText();
		String pass = _password.getText();

		if (user.equals("Administrator") && (pass.equals("test"))) {
			Main.enterData();
		} else {
			Main.IncorrectUoP();
		}
	}

	@FXML
	public void goBack() throws IOException {
		main.showMainView();
	}
}
