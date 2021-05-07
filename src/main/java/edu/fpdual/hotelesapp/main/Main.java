package edu.fpdual.hotelesapp.main;

import edu.fpdual.hotelesapp.conector.Conector;

public class Main {
	public static void main(String[] args) {
		Conector co = new Conector();
		System.out.println(co.getMySQLConnection());
	}

}
