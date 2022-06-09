/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.io.Serializable;

// Abstração de um Pais, com suas medalhas, temporada e nome
class Country implements Serializable
{

	Country(String name)
	{
		this.name	= name;
		this.ouro	= 0;
		this.prata	= 0;
		this.bronze = 0;
	}

	Country(String name, String season)
	{
		this.name	= name;
		this.season = season;
		this.ouro	= 0;
		this.prata	= 0;
		this.bronze = 0;
	}
	
	// Copy constructor
	Country(Country other)
	{
		this.name	= new String(other.name);
		this.season	= new String(other.season);
		
		this.ouro	= new Integer(other.ouro);
		this.prata	= new Integer(other.prata);
		this.bronze = new Integer(other.bronze);
	}

	public String name;
	public String season = null;
	public int ouro;
	public int prata;
	public int bronze;

	public Country
	AddMedal(String medal)
	{
		switch (medal.toLowerCase())
		{
			case "ouro":
			case "gold":
			{
				this.ouro++;
				break;
			}
			
			case "prata":
			case "silver":
			{
				this.prata++;
				break;
			}
			
			case "bronze":
			{
				this.bronze++;
				break;
			}
		}
		return this;
	}
}
