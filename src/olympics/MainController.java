/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ExCybe
 */
public class MainController implements Initializable
{

	//@DATA
	CountryParser parser;
	
	@FXML
	private ComboBox<String> from_combobox;
	@FXML
	private ComboBox<String> to_combobox;
	@FXML
	private CheckBox winter_checkbox;
	@FXML
	private CheckBox summer_checkbox;

	// Lista de anos que podem ser selecionados por From e To
	ObservableList<String> available_years;
	@FXML
	private Label chart_title;
	@FXML
	private Button open_btn;
	@FXML
	private BarChart<String,Number> chart;
	@FXML
	private AnchorPane background;
	@FXML
	private Button open_btn1;
	@FXML
	private Slider slider;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private Label path_label;

	// Aplicação começa routinas de init
	@Override public void
	initialize(URL url, ResourceBundle rb)
	{
		// Apenas setamos default valores para comboxes apos preencher com numeros
		populateComboBoxes();
		from_combobox.getSelectionModel().selectFirst();
		to_combobox.getSelectionModel().selectFirst();
		update();
	}
	
	// dar update no nosso config singleton e em todos os textos do APP
	void update()
	{
		autoUpdateConfig();
		updateTexts();
	}
	
	// Adicionamos todo os combo box com anos, onde cada ano é divisivel por 4 para combinar com olimpiadas
	private void
	populateComboBoxes()
	{
		available_years = FXCollections.observableArrayList();

		for (int year = 1894; year <= 2016	; year++)
		{
			if (year % 4 == 0)
			{
				available_years.add(String.format("%d", year));
			}
		}
		// Apís criamo a nossa lista de anos, basta adicionarmos aos comboboxes
		to_combobox.setItems(available_years);
		from_combobox.setItems(available_years);

	}


	// Atuliza todos os textos para mostrar a configuração selecionada
	private void
	updateTexts()
	{
		// Nosso singleton é onde está a configuração atual
		Config config = Config.getConfig();
		
		// Se os anos estiverem atulizados e configurados, atulizamos todo o titulo
		if ((config.year_start != -1) || (config.year_end != -1))
		{
			chart_title.setText(
				String.format(
					"Total de Medalhas dos %d primeiros países do ano %d a %d",
					config.country_qntd_display,
					config.year_start,
					config.year_end
				)
			);
			
			// Se for inverno ou verão adicionamos ao titulo, inverno e verão
			if (config.is_winter)
			{
				chart_title.setText(chart_title.getText() + ", no Inverno");
			}
			if (config.is_summer)
			{
				chart_title.setText(chart_title.getText() + ", no Verão");
			}
		}
		
		// Atulizamos o path se já estiver configurado, caso contrario será "undefined"
		path_label.setText("path: " + config.path);

		// Printamos no console a configuração atual
		for (String str : config.listCurrentConfig())
		{
			System.out.println(str);
		}
		System.out.println("____________________________________________________");

	}

	// Baseado nos combopnentes selecionados atulizamos no nosso config
	private void
	autoUpdateConfig()
	{
		Config config = Config.getConfig();
		// Atlizamos config a partir dos comboboxes
		String from = from_combobox.getValue();
		String to = to_combobox.getValue();

		// Update Years no nosso Config object
		if (from != null)
		{
			config.year_start = Integer.parseInt(from);
		}
		if (to != null)
		{
			config.year_end = Integer.parseInt(to);
		}

		// Update nosso Winter/Summer checks no singleton config 
		if (winter_checkbox.isSelected())
		{
			config.is_winter = true;
		}
		else
		{
			config.is_winter = false;
		}

		if (summer_checkbox.isSelected())
		{
			config.is_summer = true;
		}
		else
		{
			config.is_summer = false;
		}
		
		// Update de quantidade de colocados.
		config.country_qntd_display = new Integer((int)slider.getValue());

	}

	@FXML
	// Evento Trigger Ao apertar 'Open...'
	// Função abri um janela para escolhar .csv's e .txt's
	private void onOpen(ActionEvent e)
	{
		FileChooser file_chooser = new FileChooser();
		// Setamos titulo para Escolher csv
		file_chooser.setTitle("Choose a csv");

		Node source = (Node) e.getSource();

		Window stage = source.getScene().getWindow();
		
		// Setamos o directory para o directory de trabalho
		File working_directory = new File(System.getProperty("user.dir"));
		// atrelamos para o nosso filechooser
		file_chooser.setInitialDirectory(working_directory);
		
		// Permitimos mostrar apenas .csv e .txt
		file_chooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("CSV", "*.csv"),
			new FileChooser.ExtensionFilter("TXT", "*.txt")
		);
		
		File chosen_file = file_chooser.showOpenDialog(stage);
		
		// Ao escolher um arquivo, atulizamos o config
		Config config = Config.getConfig();
		if (chosen_file != null)
		{
			config.path = chosen_file.getAbsolutePath();
		}
		update();

	}

	@FXML
	// Evento Trigger para mostrar o barchart
	private void onShow(ActionEvent event)
	{
		// Carregamos o path do nosso singleton config
		String path = Config.getConfig().path;
		// Se o caminho não estiver denifidos, apresentamos PopUp informando o que fazer e retornamos
		if (path.equals("undefined"))
		{
			new PopUp("Esqueceu de escolher o CSV?","Click em 'Open...' e escolha 'year_country_medal.csv'").show();
			return;
		}
		// Caso haja caminho pro CSV, então carregamos o BarChart
		loadBarChart();
			
	}

	// Carregar o BarChart
	void loadBarChart()
	{
		// Utilizamos uma classe auxiliar para o barchart dessa aplicação
		BarChartHandler chart_handle = new BarChartHandler(this.chart);
		// Configurações Singleton
		Config config = Config.getConfig();
		
		Country[] winners = null;
		
		// Se já criamos nosso Parser de Paises, então não preciamos criar de novo 
		if (parser == null)
			parser = new CountryParser(config.path);
		
		// Caso A configuração não esteja pronta, criamos um popup e saimos da função
		if (!config.isAllSet())
		{
			System.out.println("You need to choose all valid options");
			new PopUp("Esqueceu de selecionar Inverno ou Verão?","É possivel escolher os dois").show();
			return;
		}
		
		// Caso todas as configs estão okay, então usamos nosso parsers para pegar os vencedores
		winners = parser.getWinners(config.country_qntd_display, config.year_start, config.year_end);

		// Se nesse ano, ou nessa season não tenha países, esvaziamos o barchart e retornamos
		if (winners == null)
		{
			chart_handle.clear();
			return;
		}

		
		
		// Printamos o console os vencedores e suas medalhas, para garantir que condiz com o mostrado no barchart
		for (int i = 0; i < winners.length; i++)
		{			
			System.out.println(String.format(
					"%s Gold: %d Silver: %d Bronze: %d"
				,winners[i].name
				,winners[i].ouro
				,winners[i].prata
				,winners[i].bronze
				)
			);
		}
		
		// Considerando que temos os dados necessarios:
		// Damos update nos dados com os vencedores ('winners')
		chart_handle.update(winners);
	}
	

	/*_______________Todas essas Funções Apenas dão updates dos textos e configuração__________________*/
	@FXML
	private void onDrag(MouseEvent event)
	{
		update();
	}

	private void onDrag(InputMethodEvent event)
	{
		update();
	}

	@FXML
	private void onSliderRelease(MouseEvent event)
	{
		update();
	}

	@FXML
	private void onKeyPress(KeyEvent event)
	{
		update();
	}
	
	@FXML
	private void onChange(ActionEvent e)
	{
		update();
	}

	/*==================================================================================================*/

}
