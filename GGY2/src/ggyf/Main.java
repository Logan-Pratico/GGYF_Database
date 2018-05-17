package ggyf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.text.Element;
import javax.swing.text.html.ListView;

import com.opencsv.CSVReader;

import ggyf.view.InfoController;
import javafx.scene.control.TextField;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;

public class Main extends Application {
	private static String[] arguments;
	private static Stage primaryStage;
	private static Stage helpStage;
	private static Parent mainLayout;
	private static Parent info;
	public static CSVReader reader;
	private static Parent help;

	public static String fileName;

	@Override
	public void start(Stage primaryStage) throws IOException {
		// Initializes the String to the name of the CSV this application will
		// work with if no arguments are given
		fileName = ".\\GGY.csv";

		// If arguments are given, the program will run with any CSV which is
		// passed through the arguments **Note: An exact path must be given to
		// pass another CSV**
		if (arguments.length > 0) {
			boolean exists = new File(arguments[0]).exists();
			if (exists) {
				System.out.println("Loading Specified File");
				fileName = arguments[0];

			}

		}
		// Initializes the CSVReader to a new CSVReader with either the GGY.csv
		// or another CSV (if specified) as its argument
		reader = new CSVReader(new FileReader(fileName));

		// Opens first Window
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GGYF App");
		showMainView();

	}

	/**
	 * the following method calls are used every time a new window is opened in
	 * the application. If the primaryStage is used, the new window will replace
	 * the previous window. If a local stage variable is created within the
	 * method, the new window will pop up in front of the old window. Each
	 * window is created in scene builder and saved to a folder which contains
	 * its respective controller.
	 * 
	 */

	public static void showMainView() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/test1.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void showSomeInfo() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("info/info.fxml"));
		Parent infos = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Info Window");
		stage.setScene(new Scene(infos));
		stage.show();

	}

	public static void showMe() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/web.fxml"));
		Parent WebInfo = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Get Schwifty");
		stage.setScene(new Scene(WebInfo));
		stage.show();

	}

	public static void thankYou() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("admin/thankYou.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void deleteView() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("delete/DeleteView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void startAdmin() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("admin/login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void enterData() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("admin/dataEntry.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void help() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/helpWindow.fxml"));

		Parent helps = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Help Window");
		stage.setScene(new Scene(helps));
		stage.show();

	}

	public static void DataMissing() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("admin/NullValues.fxml"));

		Parent failure = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Uh-Oh...");
		stage.setScene(new Scene(failure));
		stage.show();
	}

	public static void IncorrectUoP() throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("admin/IncorrectUoP.fxml"));

		Parent helps = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Data Not Found");
		stage.setScene(new Scene(helps));
		stage.show();

	}

	public static void commaEntry() throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("admin/commaEntry.fxml"));

		Parent commas = loader.load();
		Stage stage = new Stage();
		stage.setTitle("Data Not Found");
		stage.setScene(new Scene(commas));
		stage.show();
	}

	public static void main(String[] args) {
		// Sets any arguments in the initial start of the application equal to
		// the String[] arguments
		arguments = args;
		// Launches the application with the arguments
		launch(args);
	}

}