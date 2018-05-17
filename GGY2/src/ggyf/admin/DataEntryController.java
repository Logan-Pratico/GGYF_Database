package ggyf.admin;

import ggyf.Main;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DataEntryController {

	private Main main;

	@FXML
	public TextField _name;

	@FXML
	public TextField _year;

	@FXML
	public TextField _country;

	@FXML
	public TextField _location;

	@FXML
	public TextField _organization;

	@FXML
	public TextField _workType;

	@FXML
	public TextField _website;

	@FXML
	public TextField _email;

	@FXML
	public void addData() throws IOException {

		if ((_name.getText().equals("")) || (_year.getText().equals("")) || (_organization.getText().equals(""))
				|| (_country.getText().equals("")) || (_location.getText().equals(""))
				|| (_workType.getText().equals("")) || (_website.getText().equals(""))
				|| (_email.getText().equals(""))) {
			main.DataMissing();
		} else if ((_name.getText().contains(",")) || (_year.getText().contains(","))
				|| (_organization.getText().contains(",")) || (_country.getText().contains(","))
				|| (_location.getText().contains(",")) || (_workType.getText().contains(","))
				|| (_website.getText().contains(",")) || (_email.getText().contains(","))) {
			main.commaEntry();

		} else {

			CSVWriter writer = new CSVWriter(new FileWriter(".\\GGY.csv", true));
			String name = _name.getText().concat(",");
			String year = _year.getText().concat(",");
			String country = _country.getText().concat(",");
			String location = _location.getText().concat(",");
			String organization = _organization.getText().concat(",");
			String workType = _workType.getText().concat(",");
			String website = _website.getText().concat(",");
			String email = _email.getText().concat(",");

			// feed in your array (or convert your data to an array)
			main.reader.readAll();

			String cumulativeList = name.concat(year.concat(country.concat(
					location.concat(organization.concat(workType.concat(",,,".concat(website.concat(email))))))));

			String[] entries = (cumulativeList).split(",");
			writer.writeNext(entries);
			writer.close();

			main.thankYou();
		}
	}

	public void goHome() throws IOException {
		main.showMainView();
	}
}
