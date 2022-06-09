/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class CountryParser
{

	// String de Tradução entre Codigo e Pais
	static private String noc_country_data
		= "NOC,region,notes\n"
		+ "AFG,Afghanistan,\n"
		+ "AHO,Curacao,Netherlands Antilles\n"
		+ "ALB,Albania,\n"
		+ "ALG,Algeria,\n"
		+ "AND,Andorra,\n"
		+ "ANG,Angola,\n"
		+ "ANT,Antigua,Antigua and Barbuda\n"
		+ "ANZ,Australia,Australasia\n"
		+ "ARG,Argentina,\n"
		+ "ARM,Armenia,\n"
		+ "ARU,Aruba,\n"
		+ "ASA,American Samoa,\n"
		+ "AUS,Australia,\n"
		+ "AUT,Austria,\n"
		+ "AZE,Azerbaijan,\n"
		+ "BAH,Bahamas,\n"
		+ "BAN,Bangladesh,\n"
		+ "BAR,Barbados,\n"
		+ "BDI,Burundi,\n"
		+ "BEL,Belgium,\n"
		+ "BEN,Benin,\n"
		+ "BER,Bermuda,\n"
		+ "BHU,Bhutan,\n"
		+ "BIH,Bosnia and Herzegovina,\n"
		+ "BIZ,Belize,\n"
		+ "BLR,Belarus,\n"
		+ "BOH,Czech Republic,Bohemia\n"
		+ "BOL,Boliva,\n"
		+ "BOT,Botswana,\n"
		+ "BRA,Brazil,\n"
		+ "BRN,Bahrain,\n"
		+ "BRU,Brunei,\n"
		+ "BUL,Bulgaria,\n"
		+ "BUR,Burkina Faso,\n"
		+ "CAF,Central African Republic,\n"
		+ "CAM,Cambodia,\n"
		+ "CAN,Canada,\n"
		+ "CAY,Cayman Islands,\n"
		+ "CGO,Republic of Congo,\n"
		+ "CHA,Chad,\n"
		+ "CHI,Chile,\n"
		+ "CHN,China,\n"
		+ "CIV,Ivory Coast,\n"
		+ "CMR,Cameroon,\n"
		+ "COD,Democratic Republic of the Congo,\n"
		+ "COK,Cook Islands,\n"
		+ "COL,Colombia,\n"
		+ "COM,Comoros,\n"
		+ "CPV,Cape Verde,\n"
		+ "CRC,Costa Rica,\n"
		+ "CRO,Croatia,\n"
		+ "CRT,Greece,Crete\n"
		+ "CUB,Cuba,\n"
		+ "CYP,Cyprus,\n"
		+ "CZE,Czech Republic,\n"
		+ "DEN,Denmark,\n"
		+ "DJI,Djibouti,\n"
		+ "DMA,Dominica,\n"
		+ "DOM,Dominican Republic,\n"
		+ "ECU,Ecuador,\n"
		+ "EGY,Egypt,\n"
		+ "ERI,Eritrea,\n"
		+ "ESA,El Salvador,\n"
		+ "ESP,Spain,\n"
		+ "EST,Estonia,\n"
		+ "ETH,Ethiopia,\n"
		+ "EUN,Russia,\n"
		+ "FIJ,Fiji,\n"
		+ "FIN,Finland,\n"
		+ "FRA,France,\n"
		+ "FRG,Germany,\n"
		+ "FSM,Micronesia,\n"
		+ "GAB,Gabon,\n"
		+ "GAM,Gambia,\n"
		+ "GBR,United Kingdom,\n"
		+ "GBS,Guinea-Bissau,\n"
		+ "GDR,Germany,\n"
		+ "GEO,Georgia,\n"
		+ "GEQ,Equatorial Guinea,\n"
		+ "GER,Germany,\n"
		+ "GHA,Ghana,\n"
		+ "GRE,Greece,\n"
		+ "GRN,Grenada,\n"
		+ "GUA,Guatemala,\n"
		+ "GUI,Guinea,\n"
		+ "GUM,Guam,\n"
		+ "GUY,Guyana,\n"
		+ "HAI,Haiti,\n"
		+ "HKG,China,Hong Kong\n"
		+ "HON,Honduras,\n"
		+ "HUN,Hungary,\n"
		+ "INA,Indonesia,\n"
		+ "IND,India,\n"
		+ "IOA,Individual Olympic Athletes,Individual Olympic Athletes\n"
		+ "IRI,Iran,\n"
		+ "IRL,Ireland,\n"
		+ "IRQ,Iraq,\n"
		+ "ISL,Iceland,\n"
		+ "ISR,Israel,\n"
		+ "ISV,\"Virgin Islands, US\",Virgin Islands\n"
		+ "ITA,Italy,\n"
		+ "IVB,\"Virgin Islands, British\",\n"
		+ "JAM,Jamaica,\n"
		+ "JOR,Jordan,\n"
		+ "JPN,Japan,\n"
		+ "KAZ,Kazakhstan,\n"
		+ "KEN,Kenya,\n"
		+ "KGZ,Kyrgyzstan,\n"
		+ "KIR,Kiribati,\n"
		+ "KOR,South Korea,\n"
		+ "KOS,Kosovo,\n"
		+ "KSA,Saudi Arabia,\n"
		+ "KUW,Kuwait,\n"
		+ "LAO,Laos,\n"
		+ "LAT,Latvia,\n"
		+ "LBA,Libya,\n"
		+ "LBR,Liberia,\n"
		+ "LCA,Saint Lucia,\n"
		+ "LES,Lesotho,\n"
		+ "LIB,Lebanon,\n"
		+ "LIE,Liechtenstein,\n"
		+ "LTU,Lithuania,\n"
		+ "LUX,Luxembourg,\n"
		+ "MAD,Madagascar,\n"
		+ "MAL,Malaysia,\n"
		+ "MAR,Morocco,\n"
		+ "MAS,Malaysia,\n"
		+ "MAW,Malawi,\n"
		+ "MDA,Moldova,\n"
		+ "MDV,Maldives,\n"
		+ "MEX,Mexico,\n"
		+ "MGL,Mongolia,\n"
		+ "MHL,Marshall Islands,\n"
		+ "MKD,Macedonia,\n"
		+ "MLI,Mali,\n"
		+ "MLT,Malta,\n"
		+ "MNE,Montenegro,\n"
		+ "MON,Monaco,\n"
		+ "MOZ,Mozambique,\n"
		+ "MRI,Mauritius,\n"
		+ "MTN,Mauritania,\n"
		+ "MYA,Myanmar,\n"
		+ "NAM,Namibia,\n"
		+ "NBO,Malaysia,North Borneo\n"
		+ "NCA,Nicaragua,\n"
		+ "NED,Netherlands,\n"
		+ "NEP,Nepal,\n"
		+ "NFL,Canada,Newfoundland\n"
		+ "NGR,Nigeria,\n"
		+ "NIG,Niger,\n"
		+ "NOR,Norway,\n"
		+ "NRU,Nauru,\n"
		+ "NZL,New Zealand,\n"
		+ "OMA,Oman,\n"
		+ "PAK,Pakistan,\n"
		+ "PAN,Panama,\n"
		+ "PAR,Paraguay,\n"
		+ "PER,Peru,\n"
		+ "PHI,Philippines,\n"
		+ "PLE,Palestine,\n"
		+ "PLW,Palau,\n"
		+ "PNG,Papua New Guinea,\n"
		+ "POL,Poland,\n"
		+ "POR,Portugal,\n"
		+ "PRK,North Korea,\n"
		+ "PUR,Puerto Rico,\n"
		+ "QAT,Qatar,\n"
		+ "RHO,Zimbabwe,\n"
		+ "ROT,NA,Refugee Olympic Team\n"
		+ "ROU,Romania,\n"
		+ "RSA,South Africa,\n"
		+ "RUS,Russia,\n"
		+ "RWA,Rwanda,\n"
		+ "SAA,Germany,\n"
		+ "SAM,Samoa,\n"
		+ "SCG,Serbia,Serbia and Montenegro\n"
		+ "SEN,Senegal,\n"
		+ "SEY,Seychelles,\n"
		+ "SIN,Singapore,\n"
		+ "SKN,Saint Kitts,Turks and Caicos Islands\n"
		+ "SLE,Sierra Leone,\n"
		+ "SLO,Slovenia,\n"
		+ "SMR,San Marino,\n"
		+ "SOL,Solomon Islands,\n"
		+ "SOM,Somalia,\n"
		+ "SRB,Serbia,\n"
		+ "SRI,Sri Lanka,\n"
		+ "SSD,South Sudan,\n"
		+ "STP,Sao Tome and Principe,\n"
		+ "SUD,Sudan,\n"
		+ "SUI,Switzerland,\n"
		+ "SUR,Suriname,\n"
		+ "SVK,Slovakia,\n"
		+ "SWE,Sweden,\n"
		+ "SWZ,Swaziland,\n"
		+ "SYR,Syria,\n"
		+ "TAN,Tanzania,\n"
		+ "TCH,Czech Republic,\n"
		+ "TGA,Tonga,\n"
		+ "THA,Thailand,\n"
		+ "TJK,Tajikistan,\n"
		+ "TKM,Turkmenistan,\n"
		+ "TLS,Timor-Leste,\n"
		+ "TOG,Togo,\n"
		+ "TPE,Taiwan,\n"
		+ "TTO,Trinidad,Trinidad and Tobago\n"
		+ "TUN,Tunisia,\n"
		+ "TUR,Turkey,\n"
		+ "TUV,NA,Tuvalu\n"
		+ "UAE,United Arab Emirates,\n"
		+ "UAR,Syria,United Arab Republic\n"
		+ "UGA,Uganda,\n"
		+ "UKR,Ukraine,\n"
		+ "UNK,NA,Unknown\n"
		+ "URS,Russia (URSS),\n"
		+ "URU,Uruguay,\n"
		+ "USA,U.S.A,\n"
		+ "UZB,Uzbekistan,\n"
		+ "VAN,Vanuatu,\n"
		+ "VEN,Venezuela,\n"
		+ "VIE,Vietnam,\n"
		+ "VIN,Saint Vincent,\n"
		+ "VNM,Vietnam,\n"
		+ "VNM,Vietnam,\n"
		+ "WIF,Trinidad,West Indies Federation\n"
		+ "YAR,Yemen,North Yemen\n"
		+ "YEM,Yemen,\n"
		+ "YMD,Yemen,South Yemen\n"
		+ "YUG,Serbia,Yugoslavia\n"
		+ "ZAM,Zambia,\n"
		+ "ZIM,Zimbabwe,";


	 
/* -> Dicionário que associa um Ano a países
		por exemplo: year_countries["2020"] iria nos retornar 
		todos os paises que participaram das olimpiadas em 2020 */
	private Map< Integer, HashMap< String, Country>> year_countries;
	
	// Dicionário que associa cara NOC-code, com o nome de um País
	static private Map<String, String> code_to_country;

	// Path para o csv que relaciona uma medalha, o tipo dela (ex:"ouro"), o seu pais, qual ano, e se foi inverno ou verão
	private String country_medal_path;

	//String que define o filename de serialização do nosso dic de anos -> year_countries
	private String FILENAME = "year_countries.dat";
	
	// Contrutor apenas inicializa nossas variaveis
	// e carrega o dicionario de anos
	CountryParser(String country_medal_path)
	{
		// pegue o path para csv
		this.country_medal_path = country_medal_path;
		
		// Init dic de anos
		year_countries = new HashMap<Integer, HashMap<String, Country>>();
		
		// Popula nosso dic de anos
		parse();

	}
	
	// Simplesmente retorna nosso dic. de anos
	Map< Integer, HashMap<String, Country>> getData()
	{
		return year_countries;
	}

	// Serializa nosso dic de anos
	private void
	_save(String filename, Map< Integer, HashMap< String, Country>> data)
	{
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filename)
				);
			// Armazenando Lista
			out.writeObject(data);
			out.close();

		} 
		catch(Exception e) 
		{
			System.out.println("[[Failed to Save 'Data']]");
		}
	}
	
	// Da load no nosso dic de anos, se tiver sucesso retorna true.
	private boolean 
	_load(String filename)
	{
		boolean success;
		try {
				
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(filename)
				);
			
			this.year_countries = (Map< Integer, HashMap< String, Country>>)in.readObject();
			
			in.close();
			System.out.println(String.format("[[Sucess to Load Data from %s]]", filename));
			success =  true;
		}
	 	catch(Exception e) 
		{
			System.out.println(String.format("[[Failed to Load Data from %s]]", filename));
			
			success =  false; 
		}
		
		return success;
	}
	
	// Função onde faz a associação do nosso dic. de anos, veja a descrição na variavel -> year_countries
	// Essa função assumo que o .csv esteja já limpado e bem formatado, foi passado um script em .py antes
	// Logo o formato do csv já é conhecido, (Sem aspas, nenhum valor 'NA')
	private void
	parse()
	{
		// Se nosso dic de anos já existir, não precisa popular novamente, basta retornas
		if(!year_countries.isEmpty())
		{
			return;
		}
		// Se já existir um dic de anos serializado, não precisamos popular novamnete
		if(_load(FILENAME))
		{
			return;
		}
		
		// Caso seja a primeira vez, vamos então popular esse dic com anos e paises
		// O caminho para o csv segue como 'path'
		String path = this.country_medal_path;
		String line;

		try
		{
			// Criamos um Reader de arquiovo
			BufferedReader br = new BufferedReader(new FileReader(path));

			// Lemos cada linha até não chegar EOF
			while ((line = br.readLine()) != null)
			{
				String[] values = line.split(",");
				// a linha pode ser dividica em Pais, Ano, Temporada e tipo de medalha
				Integer year			= Integer.parseInt(values[0]);
				String season			= values[1];
				String country_name	= values[2];
				String medal = values[3];
				
				// Prevenção, avisa quase algum nome seja nulo
				if (country_name == null)
				{
					System.out.print("[[WARNING Country Name == null]]");
				}
				
				// Printamos ao console para verificar os paises que vão ser adicionados
				System.out.println(String.format(
						"Year:%d "
					  +"Season:%s "
					  +"Country:%s "
					  +"Medal:%s\n",
						year,
						season,
						country_name,
						medal
					)
				);
				
				// Se o anos não existe:
				if (!year_countries.containsKey(year))
				{
					year_countries.put(year, new HashMap<String, Country>());
				}

				// Agora temos certeza que aquele ano existe
				// Se ainda n existe o pais criamos o objeto Country
				if (!year_countries.get(year).containsKey(country_name))
				{
					year_countries.get(year).put(country_name, new Country(country_name,season));
				}
				
				
				// Agora temos certeza que existe tanto ano quando pais na nossa estrutura
				// Basta então adicionar a medalha que precisamos ao nosso País
				// essa linha poderia ser, por exemplo:
				// -> year_counties[1998]["Brazil"].AdicioneMedalha("Ouro")
				year_countries.get(year).get(country_name).AddMedal(medal.toLowerCase());
				
				
			}
		// Salvamos nosso dicionário de anos:paises como binário, ou seja, serializamos
		_save(FILENAME, this.year_countries);
		} 
		/*============= Exception handling================*/ 
		catch (FileNotFoundException e)
		{
			System.out.println("Files wasn't Found!");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("IO Exception Ocurred");
			e.printStackTrace();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static public String
	decodeCountrySimbol(String code)
	{
		String decoded_name = code;
		// Se já temos o dicionário que relaciona code com país
		// apenas retornamos o país decodificida
		if (code_to_country != null)
		{
			decoded_name = code_to_country.get(code);
		}
		// Caso seja a primeira vez que entramos nesse função
		// Iremos cirar um dicionario que relaciona o noc (code) de um  país com seu nome
		// por exemplo BR:Brazil, FR:France, e por aí vai.
		else
		{
			code_to_country = new HashMap<String, String>();

			try
			{
				// Quebramos a  string que tem essa relação e atribuimos na nosso dicionário,
				// Nesse caso, code_to_country é o  dicionário, implementado através de um hashmap
				for (String line : noc_country_data.split("\n"))
				{
					String[] values = line.split(",");

					String noc = values[0];
					String country_name = values[1];
					code_to_country.put(noc, country_name);
				}
				
				if(code_to_country.containsKey(code))
				{
					decoded_name = code_to_country.get(code);
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return decoded_name;
	}

	public Country[] 
	getWinners(int winners_qntd, int year_start, int year_end)
	{
		// Garantindo que os numeros então okay
		if (year_start < 0 || year_end < 0 || winners_qntd <= 0)
		{
			return null;
		}
		Config config = Config.getConfig();
		
		// Criamos um container de Paises, onde esse container é um conjuntos
		// Se adicionar um pais com mesmo nome, ele junta as medalhas de um país e o outro
		CountryContainer container = new CountryContainer(150);
		
		// Para cada ano  entre começo e fim
		for (Integer i = year_start; i <= year_end; i++)
		{
			// Se esse ano existir
			if (this.year_countries.containsKey(i))
			{
				// Então dicionamos cada pais desse ano ao nosso Container de Paises
				for (Map.Entry<String, Country> entry : this.year_countries.get(i).entrySet()) 
				{
					//String country_name		= entry.getKey();
					Country country_object		= entry.getValue();
					
					// Printamos para ver que está tudo certo
					System.out.println(String.format(
							"Pais: %s, Season: %s "+
							"ouro: %d prata: %d bronze: %d\n", 
							country_object.name,country_object.season,
							country_object.ouro,country_object.prata,country_object.bronze
						)
					);
						
					// Se Verão estiver ativo, adicionamos um pais apenas se ele atuou nessa temporada
					if (config.is_summer)
					{
						if (country_object.season.toLowerCase().equals("summer"))
						{
							// Copy contruct another  object, so it doesnt modify the original object
							container.Append(new Country(country_object));
						}
					}
					// Se inverno estiver ativo, adicionamos um pais apenas se ele atuou nessa temporada
					if (config.is_winter)
					{
						if (country_object.season.toLowerCase().equals("winter"))
						{
							// Copy contruct another  object, so it doesnt modify the original object
							container.Append(new Country(country_object));
						}
					}
				}		
			}
		}
		
		// Se o container não houver paises, retornamos null
		if (container.length <= 0)
			return null;
		
		Country[] country_array = container.ToArray();
		// Agora basta dar um sorte nos países por medalha, e os estara em ordem de colocação
		CountryContainer.BubbleSortCountries(country_array, country_array.length);
		
		Country[] winners = new Country[winners_qntd];
		
		// Selecionamos a quantidade de colocados desejada e retornamos
		for (int i = 0; i < winners_qntd; i++)
		{
			winners[i] = country_array[i];
		}
		
		return winners;
	}
}

