/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


// Classe para ajeitar um barchart num contexto de paises
public class BarChartHandler
{

	BarChart<String, Number> barchart;

	BarChartHandler(BarChart<String, Number> barchart)
	{
		this.barchart = barchart;

	}
	
	// Atuliza com os datos do paises ganhadores, o resto é padrão da API de barchart
	public void 
	update(Country[] winners)
	{
		

		this.clear();

		XYChart.Series series_gold = new XYChart.Series();
		series_gold.setName("Gold");
		for (Country winner : winners)
		{
			series_gold.getData().add(new XYChart.Data(CountryParser.decodeCountrySimbol(winner.name), winner.ouro));
		}

		XYChart.Series series_silver = new XYChart.Series();
		series_silver.setName("Silver");
		for (Country winner : winners)
		{
			series_silver.getData().add(new XYChart.Data(CountryParser.decodeCountrySimbol(winner.name), winner.prata));
		}

		XYChart.Series series_bronze = new XYChart.Series();
		series_bronze.setName("Bronze");
		for (Country winner : winners)
		{
			series_bronze.getData().add(new XYChart.Data(CountryParser.decodeCountrySimbol(winner.name), winner.bronze));
		}

		this.barchart.getData().addAll(series_gold, series_silver,series_bronze);
		
		//Config.getConfig().main_stage.setScene(new Scene(this.barchart, 800, 600));
	}
	
	// Limpa o dados do desse chart
	public void
	clear()
	{
		this.barchart.getData().clear();
	}
	
	// Mudar as cores para cores de medalhas, gold, silver, bronze
	void changeDefaultColor()
	{
		
		// Cores de Medalhas => 'G':"#FDE101",'S':"#D7D7D7",'B':"#A67949"

		//set first bar color
		for (Node n : barchart.lookupAll(".default-color0.chart-bar"))
		{
			n.setStyle("-fx-bar-fill: #FDE101;");
		}
		//second bar color
		for (Node n : barchart.lookupAll(".default-color1.chart-bar"))
		{
			n.setStyle("-fx-bar-fill: #D7D7D7;");
		}

		for (Node n : barchart.lookupAll(".default-color2.chart-bar"))
		{
			n.setStyle("-fx-bar-fill: #A67949;");
		}
	}

}
