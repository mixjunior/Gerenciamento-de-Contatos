package br.com.contatos.helper;

import java.sql.Connection;
import java.sql.DriverManager;

//Classe para conectar com o db

public class MySqlConnect {

	//Função que irá se conectar ao banco de dados
	public static Connection ConectarDb(){

		//variável de conexão
		Connection con = null;

		try{
			//Conecta ao driver do Banco.Esse é o driver do mysql
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://10.107.134.39/contatos","root","root");

		}catch(Exception ex){

			System.out.println(ex.getMessage());

		}

		return con;

	}

}
