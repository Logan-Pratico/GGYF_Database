package ggyf.admin;

import java.io.IOException;

import ggyf.Main;
import javafx.fxml.FXML;

public class ThankYouController {

	@FXML
	public void reader() throws IOException {
		Main.showMainView();
	}

	@FXML
	public void writer() throws IOException {
		Main.enterData();
	}

}
