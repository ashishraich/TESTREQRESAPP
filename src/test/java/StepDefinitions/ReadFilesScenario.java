package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFilesScenario {
    private String inputDirectoryPath;
    private String outputDirectoryPath;
    private List<Map<String, String>> instrumentDetails;
    private List<Map<String, String>> positionDetails;
    private List<Map<String, String>> outputDetails;
    private boolean containsAllIDs;
    private boolean quantityVerified;
    private boolean isinMatched;

    @Given("I have the input files in the directory {string}")
    public void setInputDirectoryPath(String inputDirectoryPath) {
        this.inputDirectoryPath = inputDirectoryPath;
    }

    @Given("I have the output files in the directory {string}")
    public void setOutputDirectoryPath(String outputDirectoryPath) {
        this.outputDirectoryPath = outputDirectoryPath;
    }

    @When("I run the program to read the input files from in directory and output files from out directory")
    public void run() {
        readInputFiles();
        readOutputFile();
        checkIDsExist();
        verifyQuantity();
        fetchISINNumberAndUnitPrice();
        verifyISINNumberInPositionReport();
        verifyTotalPrice();
    }

    @Then("the instrument details collection should contain the data from InstrumentDetails.csv")
    public void verifyInstrumentDetails() {
        System.out.println("Instrument Details:");
        for (Map<String, String> instrument : instrumentDetails) {
            System.out.println(instrument);
        }
    }

    @Then("the position details collection should contain the data from PositionDetails.csv")
    public void verifyPositionDetails() {
        System.out.println("\nPosition Details:");
        for (Map<String, String> position : positionDetails) {
            System.out.println(position + ", Total Price Match: " + position.get("Total Price Match"));
        }
    }

    @Then("the output details collection should contain the data from PositionReport.csv")
    public void verifyOutputDetails() {
        System.out.println("\nOutput Details:");
        for (Map<String, String> output : outputDetails) {
            System.out.println(output);
        }
    }

    @Then("the output details collection should contain the person detail IDs present in the position details collection")
    public void verifyIDCheckResult() {
        if (containsAllIDs) {
            System.out.println("All person detail IDs from outputDetails are present in positionDetails.");
        } else {
            System.out.println("Not all person detail IDs from outputDetails are present in positionDetails.");
        }
    }

    @Then("the quantity in output details should match the quantity in position detail")
    public void the_quantity_in_output_details_should_match_the_quantity_in_position_detail() {
        if (quantityVerified) {
            System.out.println("Quantity in outputDetails matches the quantity in positionDetails.");
        } else {
            System.out.println("Quantity in outputDetails does not match the quantity in positionDetails.");
        }
    }

    @Then("I fetch the ISIN number and Unit Price from the instrument details collection for each Instrument ID in Position Details")
    public void i_fetch_the_isin_number_and_unit_price_from_the_instrument_details_collection_for_each_instrument_id_in_position_details() {
        for (Map<String, String> positionDetail : positionDetails) {
            String instrumentID = positionDetail.get("Instrument ID");
            String isinNumber = "";
            String unitPrice = "";

            for (Map<String, String> instrument : instrumentDetails) {
                String instrumentIDFromDetails = instrument.get("ID");
                if (instrumentID.equals(instrumentIDFromDetails)) {
                    isinNumber = instrument.get("ISIN Number");
                    unitPrice = instrument.get("Unit Price");
                    break;
                }
            }

            positionDetail.put("ISIN Number", isinNumber);
            positionDetail.put("Unit Price", unitPrice);
        }
    }

    @Then("I verify whether the ISIN number in Position Report matches the ISIN number fetched from Instrument Details")
    public void i_verify_whether_the_isin_number_in_position_report_matches_the_isin_number_fetched_from_instrument_details() {
        isinMatched = true;
        for (Map<String, String> positionDetail : positionDetails) {
            String positionISINNumber = positionDetail.get("ISIN Number");
            String outputISINNumber = "";

            for (Map<String, String> outputDetail : outputDetails) {
                String outputID = outputDetail.get("ID");
                if (positionDetail.get("Instrument ID").equals(outputID)) {
                    outputISINNumber = outputDetail.get("ISIN Number");
                    break;
                }
            }

            if (!positionISINNumber.equals(outputISINNumber)) {
                isinMatched = false;
                break;
            }
        }
    }

    @Then("the total price in output details should match the multiplication of unit price and quantity in position details")
    public void verifyTotalPrice() {
        for (Map<String, String> outputDetail : outputDetails) {
            String outputID = outputDetail.get("ID");
            String outputQuantity = outputDetail.get("Quantity");
            String outputTotalPrice = outputDetail.get("Total Price");

            for (Map<String, String> positionDetail : positionDetails) {
                String positionID = positionDetail.get("Instrument ID");
                String positionQuantity = positionDetail.get("Quantity");
                String positionUnitPrice = positionDetail.get("Unit Price");

                if (outputID.equals(positionID) && outputQuantity.equals(positionQuantity)) {
                    double unitPrice = Double.parseDouble(positionUnitPrice);
                    int quantity = Integer.parseInt(positionQuantity);
                    double totalPrice = unitPrice * quantity;

                    if (String.format("%.2f", totalPrice).equals(outputTotalPrice)) {
                        positionDetail.put("Total Price Match", "Yes");
                    } else {
                        positionDetail.put("Total Price Match", "No");
                    }
                    break;
                }
            }
        }
    }

    private void readInputFiles() {
        instrumentDetails = readCSVFile(inputDirectoryPath, "InstrumentDetails.csv");
        positionDetails = readCSVFile(inputDirectoryPath, "PositionDetails.csv");
    }

    private void readOutputFile() {
        outputDetails = readCSVFile(outputDirectoryPath, "PositionReport.csv");
    }

    private void checkIDsExist() {
        containsAllIDs = true;
        for (Map<String, String> outputDetail : outputDetails) {
            String outputID = outputDetail.get("ID");
            boolean idExists = false;

            for (Map<String, String> positionDetail : positionDetails) {
                String positionID = positionDetail.get("Instrument ID");
                if (outputID.equals(positionID)) {
                    idExists = true;
                    break;
                }
            }

            if (!idExists) {
                containsAllIDs = false;
                break;
            }
        }
    }

    private void verifyQuantity() {
        quantityVerified = true;
        for (Map<String, String> outputDetail : outputDetails) {
            String outputID = outputDetail.get("ID");
            String outputQuantity = outputDetail.get("Quantity");
            boolean quantityMatch = false;

            for (Map<String, String> positionDetail : positionDetails) {
                String positionID = positionDetail.get("Instrument ID");
                String positionQuantity = positionDetail.get("Quantity");
                if (outputID.equals(positionID) && outputQuantity.equals(positionQuantity)) {
                    quantityMatch = true;
                    break;
                }
            }

            if (!quantityMatch) {
                quantityVerified = false;
                break;
            }
        }
    }

    private void fetchISINNumberAndUnitPrice() {
        for (Map<String, String> positionDetail : positionDetails) {
            String instrumentID = positionDetail.get("Instrument ID");
            String isinNumber = "";
            String unitPrice = "";

            for (Map<String, String> instrument : instrumentDetails) {
                String instrumentIDFromDetails = instrument.get("ID");
                if (instrumentID.equals(instrumentIDFromDetails)) {
                    isinNumber = instrument.get("ISIN Number");
                    unitPrice = instrument.get("Unit Price");
                    break;
                }
            }

            positionDetail.put("ISIN Number", isinNumber);
            positionDetail.put("Unit Price", unitPrice);
        }
    }

    private void verifyISINNumberInPositionReport() {
        isinMatched = true;
        for (Map<String, String> positionDetail : positionDetails) {
            String positionISINNumber = positionDetail.get("ISIN Number");
            String outputISINNumber = "";

            for (Map<String, String> outputDetail : outputDetails) {
                String outputID = outputDetail.get("ID");
                if (positionDetail.get("Instrument ID").equals(outputID)) {
                    outputISINNumber = outputDetail.get("ISIN Number");
                    break;
                }
            }

            if (!positionISINNumber.equals(outputISINNumber)) {
                isinMatched = false;
                break;
            }
        }
    }

    private List<Map<String, String>> readCSVFile(String directoryPath, String fileName) {
        List<Map<String, String>> data = new ArrayList<>();
        Path filePath = Paths.get(directoryPath, fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            String[] headers = null;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (headers == null) {
                    headers = values;
                } else {
                    Map<String, String> row = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        if (i < values.length) {
                            row.put(headers[i].trim(), values[i].trim());
                        }
                    }
                    data.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
