package com.mycompany.infornel;

import com.mycompany.infornel.igu.Bienvenido;

public class Infornel {

    public static void main(String[] args) {
        
        Bienvenido Bienv = new Bienvenido();  //Bienvenido --> es el nombre de nuestra clase(la clase de...
                                                //...mi login principal en mi paquete de IGU
                                                //Bienv --> es la variable objeto 
                                                //new Bienvenido() --> creame uno nuevo que sea
                                                //de tipo Bienv

        Bienv.setVisible(true);               //a la pantalla principal hacela visible
        Bienv.setLocationRelativeTo(null);    //que me centre la ventana en el centro
    }
}