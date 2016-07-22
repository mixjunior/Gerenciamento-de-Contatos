package br.com.contatos.controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import br.com.contatos.helper.MySqlConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ContatoController implements Initializable{

	@FXML TextField txtNome;
	@FXML TextField txtTelefone;
	@FXML Button btnInserir;
	@FXML ListView lstContatos;

	@FXML public void inserirContato() {

		//Abre uma conexão
		Connection con = MySqlConnect.ConectarDb();

		//Comando com parametros
		String sql="insert into contact(name,phone) values(? , ?)";

		PreparedStatement parametros;

		try {

			parametros= con.prepareStatement(sql);

			//Inserindo os dados nos parametros
			parametros.setString(1, txtNome.getText());
			parametros.setString(2, txtTelefone.getText());

			//Executa o comando.Caso seja um select,deve-se usar executeQuery.
			parametros.executeUpdate();

			con.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*String contato=txtNome.getText()+" - "+txtTelefone.getText();

		lstContatos.getItems().add(contato);

		System.out.println("funfo");*/

		preencherLista();

	}

	private void preencherLista(){

		lstContatos.getItems().clear();

		Connection con = MySqlConnect.ConectarDb();

		String sql="select * from contact";

		try {

			//Criar um resultset que irá conter os resultados
			ResultSet rs=con.createStatement().executeQuery(sql);


			//esse while irá executar até que não acha mais registros no cursor
			while(rs.next()){

				String contato="";

				contato=rs.getString("name");
				contato+="-";
				contato+=rs.getString("phone");

				lstContatos.getItems().add(contato);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		preencherLista();

	}

}
