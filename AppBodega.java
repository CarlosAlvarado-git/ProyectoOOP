import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;
public class AppBodega {
    static String usu = ""; static String cont = "";
    static int NoBodega = 0;
    static String CualProducto = "";
    static int cantidadcomprada = 0;
    public static void main(String [] arg){
        //---------- Creación de paneles y frame
        JFrame app = new JFrame("Control de Bodegas");
        app.getContentPane();
        JPanel contraseña = new JPanel();
        JPanel pinicio = new JPanel();
        JPanel pdeBodegas = new JPanel();
        contraseña.setBounds(0, 0, 1240, 820);
        pinicio.setBounds(0, 0, 1240, 820);
        pdeBodegas.setBounds(0, 0, 1240, 820);
        pdeBodegas.setVisible(false);
        pinicio.setVisible(false);
        //----------------------------------------------------------
        //-------Parte de botones de incio de sesión----------------
        JTextField usuario, contrase;
        usuario = new JTextField();
        contrase = new JTextField();
        usuario.setBounds(100,100, 20, 60);
        contrase.setBounds(100,190, 20, 60);
        JButton iniciodesesion = new JButton("Iniciar Sesión");
        iniciodesesion.setBounds(100,250,30,30);  
        iniciodesesion.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                usu = usuario.getText();
                cont = contrase.getText(); 
                contraseña.setVisible(false);
                pinicio.setVisible(true);
            }  
        });
        //-----------------------------------------------------------
        //---------Parte de pantalla de inicio-----------------------
        JMenuBar barradeMenuBar = new JMenuBar();
        JMenu TodasLasBodegas = new JMenu("Bodegas");
        JMenuItem Bodega1, Bodega2, Bodega3;
        pinicio.setLayout(new BorderLayout());
        Bodega1 = new JMenuItem("Bodega 1");
        Bodega1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                NoBodega = 1;
            }  
        });
        Bodega2 = new JMenuItem("Bodega 2");
        Bodega2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                NoBodega = 2;
            }  
        });
        Bodega3 = new JMenuItem("Bodega 3");
        Bodega3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                NoBodega = 3;
            }  
        });
        TodasLasBodegas.add(Bodega1);TodasLasBodegas.add(Bodega2);TodasLasBodegas.add(Bodega3);
        barradeMenuBar.add(TodasLasBodegas);
        //-----------------------------------------------------------
        //---------------parte de bodegas----------------------------
        /*
            Aquí debería de ir la instancia de la clase de MySQL, y realizar la Query con respecto a la bodega que se haya seleccionado.
            Utilizando NoBodega. Luego recorrer la tabla y asigarle los valors de los encabezados a la variable que corresponte y añadir por medio de un 
            ciclo los valores que tenga cada uno en la variable de datos. 
        */
        String datos[][] = { {"101","Amit","670000"},    
        {"102","Jai","780000"},    
        {"101","Sachin","700000"},{"101","Amit","670000"},    
        {"102","Jai","780000"},    
        {"101","Sachin","700000"}
                                    }; 
        String encabezados[] = {"ID","NAME","SALARY"};
        JTable tabladebodega = new JTable(datos, encabezados);
        tabladebodega.setBounds(30,40,200,300); 
        JButton producNuevo = new JButton("Añadir un producto nuevo");
        JButton producExist = new JButton("Aumentas la cantidad de un produto existente");
        JButton comprar = new JButton("Realizar compra");
        String productos[] = {"15324 Guitarra", "13213546 Piano", "10215 Clarinete"};
        final JComboBox listado = new JComboBox(productos);
        listado.setVisible(false);
        comprar.setVisible(false);
        listado.setBounds(50, 800, 100, 30);
        producNuevo.setBounds(90, 800, 100, 30);
        comprar.setBounds(90, 800, 100, 30);
        producExist.setBounds(90, 800, 100, 30);
        producExist.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                listado.setVisible(true);
                comprar.setVisible(true);
                producExist.setVisible(false);
                producNuevo.setVisible(false);
                comprar.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){
                        CualProducto = "" + listado.getItemAt(listado.getSelectedIndex());
                        cantidadcomprada = 1;
                    }  
                });
            }  
        });
        //-----------------------------------------------------------
        //--------------------agrego las cosas a los paneles---------
        //contraseña.setLayout(null);
        //pinicio.setLayout(null);
        //pdeBodegas.setLayout(null);
        contraseña.add(usuario); contraseña.add(contrase); contraseña.add(iniciodesesion);
        pinicio.add(barradeMenuBar, BorderLayout.NORTH);
        pdeBodegas.add(new JScrollPane(tabladebodega));
        pdeBodegas.add(listado);pdeBodegas.add(producNuevo);pdeBodegas.add(producExist);pdeBodegas.add(comprar);
        //-----------------------------------------------------------
        //------------agregar los paneles al app frame---------------
        //app.setLayout(null); 
        app.add(pinicio); app.add(pdeBodegas); app.add(contraseña);
        app.setSize(contraseña.getWidth(),contraseña.getHeight());   
        app.setVisible(true);   
    }
    

}
