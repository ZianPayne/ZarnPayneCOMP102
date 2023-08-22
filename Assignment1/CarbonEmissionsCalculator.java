// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2022T1, Assignment 1
 * Name: Z'Arn Payne
 * Username: paynezar
 * ID: 300607781
 */

import ecs100.*;

/** Program for calculating carbon emissions */

public class CarbonEmissionsCalculator{

    public static final double EMISSION_FACTOR_POWER = 0.0977;   // emissions factor of electricity per kWh
    public static final double EMISSION_FACTOR_WASTE = 0.299;    // emissions factor of food waste per kg
    public static final double EMISSION_FACTOR_WOOD = 0.0670;    // emissions factor of wood in fireplaces per kg
    public static final double EMISSION_FACTOR_VEHICLE = 0.171;  // emissions factor of private petrol vehicles per km  Source:https://www.beehive.govt.nz/sites/default/files/2021-01/Clean%20Car%20Import%20Standard%20Explainer_0.pdfregistrations

    public static final double ANNUAL_AVERAGE_POWER_EMISSION_NZ = 266.5;   //Annual average carbon emissions in NZ (kg) from electricity use
    public static final double ANNUAL_AVERAGE_FOOD_WASTE_EMISSION_NZ = 18; //Annual average carbon emissions in NZ (kg) from food waste
    public static final double ANNUAL_AVERAGE_WOOD_EMISSION_NZ = 69.8;     //Annual average carbon emissions in NZ (kg) from wood burning (Assuming everyone has a cord of wood and burns it)
    public static final double ANNUAL_AVERAGE_VEHICLE_EMISSION_NZ = 1710;  //Annual average carbon emissions in NZ (kg) from vehicle travel (Taking average person drives 10,000km per year in nz)

    public static final int DAYS_IN_WEEK = 7;            //Days in a week
    public static final double DAYS_IN_MONTH = 30.437;   //Average number of days in a month
    public static final int MONTHS_IN_YEAR = 12;         //Months in a year
    public static final double WEEKS_IN_YEAR = 52.1775;  //Average number of weeks in a year
    public static final double DAYS_IN_YEAR = 365.25;    //Average number of days in a year
    
    /** 
     * Asks user for Monthly Electricity and Weekly Food Waste
     * 
     * Calculates and prints carbon emissions
     */
    public void calculateEmissions(){
        /* Asks user for power, waste, vehicle usage*/
        UI.println("--------------------------------\nCarbon Emmisions Calculator");
        double inputPower = UI.askDouble("Monthly Electricity Consumption (kWh): ");
        double inputWood = UI.askDouble("Daily Wood Burned (kg): ");
        double inputWaste = UI.askDouble("Weekly Food Waste (kg):");
        double inputVehicle = UI.askDouble("Weekly Milage in Car if you drive (km):"); //New factor, based on average emissions of petrol vehicles
 
        /* Works out emissions for power, wood, food waste, vehicle. Then prints emissions along with daily average*/
        double emissionPower = (inputPower * EMISSION_FACTOR_POWER);
        double emissionWood = (inputWood * EMISSION_FACTOR_WOOD);
        double emissionWaste = (inputWaste * EMISSION_FACTOR_WASTE);
        double emissionVehicle = (inputVehicle * EMISSION_FACTOR_VEHICLE);
       
        double emissionDaily = (emissionWaste / DAYS_IN_WEEK + emissionWood + emissionPower / DAYS_IN_MONTH + emissionVehicle / DAYS_IN_WEEK);
        UI.printf("CO2 emissions caused by electricity (kg CO2-e) in the month: " + ("%.2f%n"), emissionPower);
        UI.printf("CO2 emissions caused by wood burning (kg CO2-e) daily: " + ("%.2f%n"), emissionWood);
        UI.printf("CO2 emissions caused by waste (kg CO2-e) in the week: " + ("%.2f%n"), emissionWaste);
        UI.printf("CO2 emissions caused by Driving (kg CO2-e) in the week: " + ("%.2f%n"), emissionVehicle);
        UI.printf("Daily average CO2 emissions (kg CO2-e): " + ("%.2f%n"), emissionDaily);      
        
        /* Asks user for household size, compares emissions to nz average, displaying a percentage */
        double householdSize = UI.askDouble("Number of individuals in the household: ");
        double emissionsCompare = (((emissionPower * MONTHS_IN_YEAR) + (emissionWood * DAYS_IN_YEAR) + (emissionWaste * WEEKS_IN_YEAR) + (emissionVehicle * WEEKS_IN_YEAR)) / ((ANNUAL_AVERAGE_POWER_EMISSION_NZ + ANNUAL_AVERAGE_WOOD_EMISSION_NZ +ANNUAL_AVERAGE_FOOD_WASTE_EMISSION_NZ + ANNUAL_AVERAGE_VEHICLE_EMISSION_NZ) * householdSize)); //Compares emmisions to national avg as decimal
        UI.printf("You emit " + ("%%%.2f%s%n"),emissionsCompare * 100, " CO2 emissions of the national average");
    }
    
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Calculate Emissions", this::calculateEmissions);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);    // Expand the Text pane
    }

    public static void main(String[] args){
        CarbonEmissionsCalculator cec = new CarbonEmissionsCalculator();
        cec.setupGUI();
    }

}
