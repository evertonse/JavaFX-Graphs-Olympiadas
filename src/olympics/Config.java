/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Singleton Congiration info. Feito para ser a configuração para toda a
 * application
 */
public class Config
{

	// single instance
	private static Config instance;
	// Começa com combobox desativados
	public boolean is_winter = false, is_summer = false;
	
	// Anos de começo e fim setados a -1 para indicar indefinido
	public int year_start = -1, year_end = -1;
	// Stage principal
	public Stage main_stage = null;
	// Scene principal, setado no começo do APP
	public Scene main_scene = null;
	// Quantidade de colocados
	public Integer country_qntd_display = null;
	// Caminho para csv
	String path;

	// Private constructor
	private Config()
	{
		path = "undefined";
	}

	// Retorna singleton
	static public Config
	getConfig()
	{
		if (Config.instance == null)
		{
			Config.instance = new Config();
		}
		return Config.instance;
	}

	// Lista no consoleas configurações atuais
	public String[]
	listCurrentConfig()
	{
		String winter_config = "false",
			summer_config		= "false",
			year_start_config = "indefinido",
			year_end_config	= "indefinido";

		if (is_winter)
		{
			winter_config = "true";
		}
		if (is_summer)
		{
			summer_config = "true";
		}
		if (year_start != -1)
		{
			year_start_config = String.format("%d", this.year_start);
		}
		if (year_end != -1)
		{
			year_end_config = String.format("%d", this.year_end);
		}
		String[] configuration =
		{
			String.format("winter.%s", winter_config),
			String.format("summer.%s", summer_config),
			String.format("year_start.%s", year_start_config),
			String.format("year_end.%s", year_end_config),
			String.format("path.%s", this.path),
		};
		return configuration;
	}

	// Confere se os anos são validos e se está ativo um dos combo box pelo menos, o de winter e summer
	public boolean
	isAllSet()
	{
		if (is_winter || is_summer)
		{
			if ((year_start != -1) && (year_end != -1))
			{
				if (year_start <= year_end)
				{
					return true;
				}
			}
		}
		return false;
	}

}
