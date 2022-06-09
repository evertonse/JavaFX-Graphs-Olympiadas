/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
 
// Classe Simples Adapter para ter um API simples para PopUp
public class PopUp
{
	
	private Alert alert;	
	
	// Cria Alerta com titulo
	PopUp(String header, String content)
	{
		Config config = Config.getConfig();
		AlertType type = AlertType.INFORMATION;
		this.alert = new Alert(type,"");
		
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(config.main_stage);
		alert.getDialogPane().setHeaderText(header);
		alert.getDialogPane().setContentText(content);
	}
	
	// Criar Alerta sem titulo
	PopUp(String content)
	{
		Config config = Config.getConfig();
		AlertType type = AlertType.INFORMATION;
		this.alert = new Alert(type,"");
		
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(config.main_stage);
		alert.getDialogPane().setHeaderText("");
		alert.getDialogPane().setContentText(content);
	}
	
	// Mostra o pop up
	public void
	show()
	{
		alert.showAndWait();
	}
}
