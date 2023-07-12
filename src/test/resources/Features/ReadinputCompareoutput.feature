Feature: Reading and processing CSV files

  Scenario: Read input and output files
    Given I have the input files in the directory "C:\\Users\\Lenovo\\eclipse-workspace\\QE_ASSESMENT\\datafiles\\in"
    And I have the output files in the directory "C:\\Users\\Lenovo\\eclipse-workspace\\QE_ASSESMENT\\datafiles\\out"
    When I run the program to read the input files from in directory and output files from out directory
    Then the instrument details collection should contain the data from InstrumentDetails.csv
    And the position details collection should contain the data from PositionDetails.csv
    And the output details collection should contain the data from PositionReport.csv
    And the output details collection should contain the person detail IDs present in the position details collection
    And the quantity in output details should match the quantity in position detail
    And I fetch the ISIN number and Unit Price from the instrument details collection for each Instrument ID in Position Details
    And I verify whether the ISIN number in Position Report matches the ISIN number fetched from Instrument Details
    And the total price in output details should match the multiplication of unit price and quantity in position details    