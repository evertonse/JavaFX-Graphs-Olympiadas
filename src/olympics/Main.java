/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.util.ArrayList;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application 
{

	
	public static void main(String[] args) 
	{
		launch(args);
	}

	// Nosso APP começa por aqui
	@Override
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root, 680, 800	);

		stage.setTitle("Medalhas-Olipiadas");
		stage.setScene(scene);
		
		// Adicionamos o main Stage e Scene para o nosso singleton config
		// Pode ser utilizado por outras classes que precisam de acesso à
		// window principal
		Config.getConfig().main_stage = stage;
		Config.getConfig().main_scene = scene;
		
		// Configurações que ficam bonita com o trabalho
		stage.setMinHeight(800);
		stage.setMinWidth(1020);
		stage.setMaxWidth(1020);
		stage.show();
	}


}
