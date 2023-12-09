# ZipViz - Demographic Data Visualizer

ZipViz is a Java application that visualizes demographic data based on a given ZIP code. The application fetches income distribution data from an API and displays it in various chart formats including bar charts, line charts, pie charts, and Lorenz curves.

## Features

- Fetch demographic data based on a given ZIP code.
- Visualize income distribution data in various chart formats.
- Export the current chart as an image (.png) or as a CSV file.

## How to Use

1. Enter a ZIP code in the text field.
2. Select a chart type from the combo box.
3. Click the "Submit" button to fetch the data and display the chart.
4. Click the "Export" button to export the current chart as an image or as a CSV file.

## Code Structure

The application is structured into several classes and interfaces:

- `ZipVizApp`: The main entry point for the application.
- `ApplicationWindow`: Represents the main application window.
- `DataInputPanel`: Represents a panel for entering data input.
- `DemographicChartPanel`: Represents a JPanel that displays a chart representing demographic data.
- `ChartFactory`: An interface for creating charts.
- `BarChartFactory`, `LineChartFactory`, `PieChartFactory`, `LorenzChartFactory`, `InitialChartFactory`: Implementations of the `ChartFactory` interface for creating different types of charts.
- `ChartFactoryProvider`: Provides a `ChartFactory` object based on the specified chart type.
- `DataFetcher`: Fetches demographic data from an API based on a given ZIP code.
- `DemographicData`: Represents demographic data, specifically the income distribution data, for a given ZIP code.
- `DataInputObserver`: An interface for observing user's data input.

## Technologies Used

- Java
- JFreeChart for creating charts
- Gson for parsing JSON data
- Swing for creating the GUI

## Setup

Run ZipVizApp.java to start the application.

## Note

This application uses the U.S. Census Bureau's API to fetch demographic data. The API key is now stored as an environment variable. To set the API key use:

```bash
CENSUS_API_KEY=your_api_key;