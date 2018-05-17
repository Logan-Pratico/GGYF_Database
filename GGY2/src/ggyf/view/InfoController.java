package ggyf.view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ListView;
import com.opencsv.CSVReader;
import ggyf.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InfoController {

	private Main main;

	@FXML
	private String _test;

	@FXML
	private ListView<String> _name = new ListView();

	@FXML
	private ListView<String> _City = new ListView();

	@FXML
	private ListView<String> _date = new ListView();

	@FXML
	private ListView<String> _Country = new ListView();

	@FXML
	private ListView<String> _organization = new ListView();

	@FXML
	private ListView<String> _workType = new ListView();

	@FXML
	private ListView<String> _website = new ListView();

	@FXML
	private ListView<String> _email = new ListView();

	@FXML
	private ListView<String> _websites = new ListView();

	@FXML
	private ListView<String> _emails = new ListView();

	@FXML
	private TextField _search;

	@FXML
	public void showInfo() throws IOException {
		// Sets searchString equal to the text entered in the textfield of the
		// application (_search).
		String searchString = _search.getText();

		// Only proceed if something has been entered into the string
		if (!searchString.equals("")) {

			try {

				String[] nextline;
				int i = 0;

				// Ensures that all fields are blank. Allows for multiple
				// searches and clears the old data in the listViews.
				_name.getItems().clear();
				_date.getItems().clear();
				_Country.getItems().clear();
				_City.getItems().clear();
				_organization.getItems().clear();
				_workType.getItems().clear();
				_email.getItems().clear();
				_website.getItems().clear();

				// Starts the reader back at the first line of the CSV
				main.reader = new CSVReader(new FileReader(main.fileName));
				if (main.reader.readNext() != null) {
					// This p is concated with each value so that each listView
					// is numbered
					int p = 1;
					while ((nextline = main.reader.readNext()) != null) {

						// turns the row in the CSV into a string and then
						// splits it into values where ever there is a comma
						// The resulting String[] has indexes which correspond
						// to each listView (ie: values[0] == main)
						String data = Arrays.toString(nextline);
						String[] values = data.split(",");

						// Cleans up data for listView
						values[0] = values[0].replace("[", "").replace("]", "");
						values[1] = values[1].replace("[", "").replace("]", "");
						values[2] = values[2].replace("[", "").replace("]", "");
						values[3] = values[3].replace("[", "").replace("]", "");
						values[4] = values[4].replace("[", "").replace("]", "");
						values[5] = values[5].replace("[", "").replace("]", "");
						values[9] = values[9].replace("[", "").replace("]", "");
						values[10] = values[10].replace("[", "").replace("]", "");

						// if 'all' is typed, each listView will add all values
						if (searchString.equalsIgnoreCase("all")) {
							_name.getItems().add(Integer.toString(p).concat(". ").concat(values[0]));
							_date.getItems().add(Integer.toString(p).concat(". ").concat(values[1]));

							_Country.getItems().add(Integer.toString(p).concat(". ").concat(values[2]));
							_City.getItems().add(Integer.toString(p).concat(". ").concat(values[3]));

							_organization.getItems().add(Integer.toString(p).concat(". ").concat(values[4]));
							_workType.getItems().add(Integer.toString(p).concat(". ").concat(values[5]));
							_website.getItems().add(values[9]);
							_email.getItems().add(values[10]);
							p++;

							// if help is typed in the search string, this
							// launches the command menu.
							// Additionally, readAll is called on the reader to
							// ensure that this command is only called once
						} else if (searchString.equalsIgnoreCase("help")) {
							Main.help();
							main.reader.readAll();
							// Something fun included when I was bored :)
						} else if (searchString.equals("Show Me What You Got!")) {
							main.showMe();
							main.reader.readAll();
						}
						// This will iterate through each index in the values
						// array and if it any string contains the searchString
						// it will add the entire values array to the listView
						for (int a = 0; a < values.length; a++) {
							if ((Pattern.compile(Pattern.quote(searchString), Pattern.CASE_INSENSITIVE)
									.matcher(values[a]).find()) && (!searchString.equalsIgnoreCase("all"))) {

								_name.getItems().add(Integer.toString(p).concat(". ").concat(values[0]));
								_date.getItems().add(Integer.toString(p).concat(". ").concat(values[1]));

								_Country.getItems().add(Integer.toString(p).concat(". ").concat(values[2]));
								_City.getItems().add(Integer.toString(p).concat(". ").concat(values[3]));

								_organization.getItems().add(Integer.toString(p).concat(". ").concat(values[4]));
								_workType.getItems().add(Integer.toString(p).concat(". ").concat(values[5]));
								_website.getItems().add(values[9]);
								_email.getItems().add(values[10]);
								a = values.length;
								p++;
							}

						}

					}
				}
				// if the listViews are empty after the CSV has been iterated, a
				// window will pop up saying that nothing was found

				if (_name.getItems().isEmpty() && !searchString.equalsIgnoreCase("help")
						&& !searchString.equals("Show Me What You Got!")) {

					Main.showSomeInfo();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	}

	// This code says that if the organization is clicked, both the website and
	// the email will pop up in a box for that organization
	@FXML
	public void handleClick() throws IOException {
		_emails.getItems().clear();
		_websites.getItems().clear();

		String email = _email.getItems().get(_organization.getSelectionModel().getSelectedIndex());
		String website = _website.getItems().get(_organization.getSelectionModel().getSelectedIndex());

		_emails.getItems().add(email);
		_websites.getItems().add(website);

	}

	// If the admin button is clicked in the window, the login window will be
	// prompted
	@FXML
	public void login() throws IOException {
		main.startAdmin();
	}

	@FXML
	public void deleter() throws IOException {
		main.deleteView();
	}
}
