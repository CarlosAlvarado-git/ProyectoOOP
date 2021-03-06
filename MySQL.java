import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

//JBDC es una appi para conectarnos con base de datos desde java
public class MySQL {
    public final String DB = "proyectooop"; //Final valores que queremos que se mantenga constantes y que no se puedan cambiar durante la ejecucion del programa 
    public final String URL = "jdbc:mysql://localhost:3306/" + DB;
    public final String USER = "root";
    public final String PASS = "";
    public Connection connect; //Establecer conexiones con la base de datos
    PreparedStatement stmt; //Para ejecutar las sentencias SQL y enviarlas a la base de datos
    PreparedStatement stmt2;
    public String stringuso = "";
    //public LinkedList<Producto> Linked_Productos = new LinkedList<Producto>();//no debería de existir
    public LinkedList<Bodega> Linked_Bodegas = new LinkedList<Bodega>();
    public LinkedList<Cantidad> Linked_Cantidad = new LinkedList<Cantidad>();
    //despues
    public LinkedList<Cuerdas> Linked_Instrumentos_cuerdas = new LinkedList<Cuerdas>();//estas son las claves junto a cantidad
    public LinkedList<Viento> Linked_Instrumentos_viento = new LinkedList<Viento>();
    public LinkedList<Percusion> Linked_Instrumentos_percucion = new LinkedList<Percusion>();

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //precargar las calses de JBDC (DriverManager, Connection, Preparedstatement, ResultSet)
            connect = DriverManager.getConnection(URL, USER, PASS);  //Necesitamos enviarle la URL de la base de datos, el usuario y la contraseña 
        } catch (ClassNotFoundException | SQLException ex) { //ClassNotFoundException es una excepción de tiempo de ejecución que se lanza cuando una aplicación intenta cargar una clase y no se encuentra en la ruta del classpath
            System.out.println("error " + ex.getMessage());
        }
        return connect;
    }

    public boolean closeConnection(Connection connect) {
        boolean condicion = false;
        try {
            connect.close();
            condicion = true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);  //La clase Logger de Java permite crear mensajes para el seguimiento o registro de la ejecución de una aplicación. y el level.server nos sirve para asignarle el nivel de importancia del mensaje
        }
        return condicion;
    }

    /*
    public void crear_bodega(String nombre) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("INSERT INTO `bodega1` (`codigo`, `nombre`, `no_ventas`, `no_compras`) VALUES (NULL,'"+ nombre +"', '0', '0')");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }
    */

    /*
    public void crear_producto(String codigo, double precio, String marca, String modelo, String nombre, String tipo_material, int peso) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("INSERT INTO `producto` (`codigo`, `precio`, `marca`, `modelo`, `nombre`, `tipo_material`, `peso`, `activo`) VALUES ('"+codigo+"', '"+precio+"', '"+marca+"', '"+modelo+"', '"+nombre+"', '"+tipo_material+"', '"+peso+"', '1')");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }
    */
/*
    public void crear_instrumento_cuerda(String codigo_producto, String tipo_cuerda, int cantidad_cuerda, int no_resonancia) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("INSERT INTO `instrumento_cuerda` (`codigo`, `codigo_producto`, `tipo_cuerda`, `cantidad_cuerdas`) VALUES (NULL, '"+ codigo_producto +"', '"+ tipo_cuerda +"', '"+ cantidad_cuerda +"')");
        stmt.executeUpdate();
        

        //Obtener el codigo del ultimo instrumento de cuerda creado para poder crear la resonancia
        stmt = connect.prepareStatement("SELECT MAX(codigo) FROM `instrumento_cuerda`");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int numero = rs.getInt("MAX(codigo)");
        
        stmt = connect.prepareStatement("INSERT INTO `resonancia` (`codigo`, `codigo_instrumento_cuerda`, `no_resonancia`) VALUES (NULL, '"+ numero +"', '"+ no_resonancia +"')");
        stmt.executeUpdate();
        

        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void crear_instrumento_percusion(String codigo_producto, String elemento_percutor, String elemento_vibrante) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("INSERT INTO `instrumento_percusion` (`codigo`, `codigo_producto`, `elemento_percutor`, `elemento_vibrante`) VALUES (NULL, '"+ codigo_producto +"', '"+ elemento_percutor+"', '"+ elemento_vibrante +"')");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void crear_instrumento_viento(String codigo_producto, String largo) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("INSERT INTO `instrumento_viento` (`codigo`, `codigo_producto`, `largo`) VALUES (NULL, '"+codigo_producto+"', '"+ largo +"')");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }
    */

    /*
    public void asignar_producto(int codigo_bodega, String codigo_producto, int cantidad) //si
    {
        int compras_existentes =0;
        openConnection();
        try{
            stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+codigo_bodega+"', '"+codigo_producto+"', '"+cantidad+"')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("SELECT `no_compras` FROM `bodega1` WHERE `codigo` = '"+codigo_bodega+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            compras_existentes = rs.getInt("no_compras");
            compras_existentes = compras_existentes + cantidad;
            stmt = connect.prepareStatement("UPDATE `bodega1` SET `no_compras` = '"+compras_existentes+"' WHERE `bodega1`.`codigo` = '"+codigo_bodega+"'");
            stmt.executeUpdate();
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }



    public void comprarProducto(int codigo_cantidad, int cantidad) //si //tiene que ser con el codigo_cantidad, porque no se sabe que producto de que bodega
    {
        int cantidad_existente =0;
        int compras_existentes = 0;
        int codigo_bodega = 0;
        ResultSet rs;

        openConnection();
        try{
        stmt = connect.prepareStatement("SELECT `codigo_bodega` FROM `cantidad` WHERE `codigo` = '"+codigo_cantidad+"' ");
        rs = stmt.executeQuery();
        rs.next();
        codigo_bodega = rs.getInt("codigo_bodega");
        stmt = connect.prepareStatement("SELECT `cantidad` FROM `cantidad` WHERE `codigo` = '"+codigo_cantidad+"'");
        rs = stmt.executeQuery();
        rs.next();
        cantidad_existente = rs.getInt("cantidad");
        cantidad_existente = cantidad_existente + cantidad;
        stmt = connect.prepareStatement("UPDATE `cantidad` SET `cantidad` = '"+cantidad_existente+"' WHERE `cantidad`.`codigo` = '"+codigo_cantidad+"'");
        stmt.executeUpdate();
        stmt = connect.prepareStatement("SELECT `no_compras` FROM `bodega1` INNER JOIN `cantidad` ON `bodega1`.`codigo` = `cantidad`.`codigo_bodega` WHERE `cantidad`.`codigo` = '"+codigo_cantidad+"'");
        rs = stmt.executeQuery();
        rs.next();
        compras_existentes = rs.getInt("no_compras");
        compras_existentes = compras_existentes + cantidad;
        stmt = connect.prepareStatement("UPDATE `bodega1` SET `no_compras` = '"+compras_existentes+"' WHERE `bodega1`.`codigo` = '"+codigo_bodega+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void venderProducto(int codigo_cantidad, int cantidad) // si
    {
        int cantidad_existente =0;
        int compras_existentes = 0;
        int codigo_bodega = 0;
        ResultSet rs;

        openConnection();
        try{
        stmt = connect.prepareStatement("SELECT `codigo_bodega` FROM `cantidad` WHERE `codigo` = '"+codigo_cantidad+"' ");
        rs = stmt.executeQuery();
        rs.next();
        codigo_bodega = rs.getInt("codigo_bodega");
        stmt = connect.prepareStatement("SELECT `cantidad` FROM `cantidad` WHERE `codigo` = '"+codigo_cantidad+"'");
        rs = stmt.executeQuery();
        rs.next();
        cantidad_existente = rs.getInt("cantidad");
        cantidad_existente = cantidad_existente - cantidad;
        stmt = connect.prepareStatement("UPDATE `cantidad` SET `cantidad` = '"+cantidad_existente+"' WHERE `cantidad`.`codigo` = '"+codigo_cantidad+"'");
        stmt.executeUpdate();
        stmt = connect.prepareStatement("SELECT `no_compras` FROM `bodega1` INNER JOIN `cantidad` ON `bodega1`.`codigo` = `cantidad`.`codigo_bodega` WHERE `cantidad`.`codigo` = '"+codigo_cantidad+"'");
        rs = stmt.executeQuery();
        rs.next();
        compras_existentes = rs.getInt("no_compras");
        compras_existentes = compras_existentes - cantidad;
        stmt = connect.prepareStatement("UPDATE `bodega1` SET `no_compras` = '"+compras_existentes+"' WHERE `bodega1`.`codigo` = '"+codigo_bodega+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void eliminar_producto(String codigo_producto) // si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `activo` = '0' WHERE `producto`.`codigo` = "+codigo_producto+"");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void aparecer_producto(String codigo_producto) // si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `activo` = '1' WHERE `producto`.`codigo` = "+codigo_producto+"");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_precio(String codigo , double precio) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `precio` = "+precio+" WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_marca(String codigo , String marca) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `marca` = '"+marca+"' WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_modelo(String codigo , String modelo) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `modelo` = '"+modelo+"' WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_nombre(String codigo , String nombre) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `nombre` = '"+nombre+"' WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_tipo_material(String codigo , String tipo_material) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `tipo_material` = '"+tipo_material+"' WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_peso(String codigo , int peso) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `peso` = '"+peso+"' WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }



    public Double getProducto_precio(String codigo) //si
    {
        Double numero = 0.0;
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `precio` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            numero = rs.getDouble("precio");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return numero;
    }

    public String getProducto_marca(String codigo) //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `marca` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stringuso = rs.getString("marca");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return stringuso;
    }

    public String getProducto_modelo(String codigo) //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `modelo` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stringuso = rs.getString("modelo");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return stringuso;
    }

    public String getProducto_nombre(String codigo) //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `nombre` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stringuso = rs.getString("nombre");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return stringuso;
    }

    public String getProducto_tipo_material(String codigo) //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `tipo_material` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stringuso = rs.getString("tipo_material");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return stringuso;
    }

    public int getProducto_peso(String codigo) //si
    {
        int peso = 0;
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `peso` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            peso = rs.getInt("peso");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return peso;
    }


    public String getcuerdas_tipocuerda(String codigo_producto) //si
    {
        String tipo_cuerda = "";
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `tipo_cuerda` FROM `instrumento_cuerda` WHERE `codigo_producto` = '"+codigo_producto+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            tipo_cuerda = rs.getString("tipo_cuerda");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return tipo_cuerda;
    }

    public int getcuerdas_cantidad_cuerdas(String codigo_producto) //si
    {
        int cantidad_cuerdas = 0;
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `cantidad_cuerdas` FROM `instrumento_cuerda` WHERE `codigo_producto` = '"+codigo_producto+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cantidad_cuerdas = rs.getInt("cantidad_cuerdas");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return cantidad_cuerdas;
    }

    public void setCuerdas_tipo_cuerda(String codigo_producto, String tipo_cuerda) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `instrumento_cuerda` SET `tipo_cuerda` = '"+tipo_cuerda+"' WHERE `instrumento_cuerda`.`codigo_producto` = '"+codigo_producto+"' ");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setCuerdas_cantidad_cuerda(String codigo_producto, int cantidad) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `instrumento_cuerda` SET `cantidad_cuerdas` = '"+cantidad+"' WHERE `instrumento_cuerda`.`codigo_producto` = '"+codigo_producto+"' ");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public String getviento_largo(String codigo_producto) //si
    {
        String largo = "";
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `largo` FROM `instrumento_viento` WHERE `codigo_producto` = '"+codigo_producto+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            largo = rs.getString("largo");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return largo;
    }

    public void setviento_largo(String codigo_producto, String largo) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `instrumento_viento` SET `largo` = '"+largo+"' WHERE `instrumento_viento`.`codigo` = '"+codigo_producto+"' ");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public String getPercucion_elementoPercutor(String codigo_producto) //si
    {
        String percutor = "";
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `elemento_percutor` FROM `instrumento_percusion` WHERE `codigo_producto` = '"+codigo_producto+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            percutor = rs.getString("elemento_percutor");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return percutor;
    }

    public String getPercucion_elementoVibrante(String codigo_producto) //si
    {
        String vibrante = "";
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `elemento_vibrante` FROM `instrumento_percusion` WHERE `codigo_producto` = '"+codigo_producto+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            vibrante = rs.getString("elemento_vibrante");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return vibrante;
    }

    public void setPercucion_elementoPercutor(String codigo_producto, String elemento_percutor) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `instrumento_percusion` SET `elemento_percutor` = '"+elemento_percutor+"' WHERE `instrumento_percusion`.`codigo_producto` = '"+codigo_producto+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setPercucion_elementoVibrante(String codigo_producto, String elemento_vibrante) //si
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `instrumento_percusion` SET `elemento_vibrante` = '"+elemento_vibrante+"' WHERE `instrumento_percusion`.`codigo_producto` = '"+codigo_producto+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void traslado_bodega(String codigo_producto, int no_bodegaActual, int no_bodegaDestino, int cantidad)
    {
        int cantidad_actual = 0;
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `cantidad` FROM `cantidad` WHERE `codigo_producto` = '"+codigo_producto+"' AND `codigo_bodega` = '"+no_bodegaActual+"' ");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cantidad_actual = rs.getInt("cantidad");
            if(cantidad < cantidad_actual)
            {
                cantidad_actual = cantidad_actual - cantidad;
                stmt = connect.prepareStatement("UPDATE `cantidad` SET `cantidad` = '"+cantidad_actual+"' WHERE `cantidad`.`codigo_producto` = '"+codigo_producto+"' AND `cantidad`.`codigo_bodega` = '"+no_bodegaActual+"'");
                stmt.executeUpdate();
                stmt = connect.prepareStatement("SELECT `cantidad` FROM `cantidad` WHERE `codigo_producto` = '"+codigo_producto+"' AND `codigo_bodega` = '"+no_bodegaDestino+"' ");
                rs = stmt.executeQuery();
                if(rs.next() == false)
                {
                    stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+no_bodegaDestino+"', '"+codigo_producto+"', '"+cantidad+"')");
                    stmt.executeUpdate();
                }
                else{
                    cantidad_actual = rs.getInt("cantidad");
                    cantidad_actual = cantidad_actual + cantidad;
                    stmt = connect.prepareStatement("UPDATE `cantidad` SET `cantidad` = '"+cantidad_actual+"' WHERE `cantidad`.`codigo_producto` = '"+codigo_producto+"' AND `cantidad`.`codigo_bodega` = '"+no_bodegaDestino+"'");
                    stmt.executeUpdate();
                }
                stmt.close();
            }
            else if(cantidad == cantidad_actual)
            {
                stmt = connect.prepareStatement("DELETE FROM `cantidad` WHERE `cantidad`.`codigo_bodega` = '"+no_bodegaActual+"' AND `cantidad`.`codigo_producto` = '"+codigo_producto+"' ");
                stmt.executeUpdate();
                stmt = connect.prepareStatement("SELECT `cantidad` FROM `cantidad` WHERE `codigo_producto` = '"+codigo_producto+"' AND `codigo_bodega` = '"+no_bodegaDestino+"' ");
                rs = stmt.executeQuery();
                if(rs.next() == false)
                {
                    stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+no_bodegaDestino+"', '"+codigo_producto+"', '"+cantidad+"')");
                    stmt.executeUpdate();
                }
                else{
                    cantidad_actual = rs.getInt("cantidad");
                    cantidad_actual = cantidad_actual + cantidad;
                    stmt = connect.prepareStatement("UPDATE `cantidad` SET `cantidad` = '"+cantidad_actual+"' WHERE `cantidad`.`codigo_producto` = '"+codigo_producto+"' AND `cantidad`.`codigo_bodega` = '"+no_bodegaDestino+"'");
                    stmt.executeUpdate();
                }
                stmt.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }


    public LinkedList<Producto> cargarProductos() //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `producto` WHERE `activo` = 1");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Producto productos = new Producto(rs.getString("codigo"), rs.getDouble("precio"),rs.getString("marca"),rs.getString("modelo"),rs.getString("nombre"),rs.getString("tipo_material"),rs.getInt("peso") );  
                Linked_Productos.add(productos);                
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return Linked_Productos;
    }  
*/
    public void escribirBodegas(Bodega b){
        openConnection();
        try{
            int Noventas = b.getVentas();
            int Novcompras = b.getCompras();
            int num = b.getNoBodega();
            stmt = connect.prepareStatement("UPDATE `bodega1` SET `no_compras` = '"+Novcompras+"' WHERE `bodega1`.`codigo` = '"+num+"' "); 
            stmt.executeUpdate(); ////Para ejecutar las sentencias SQL y enviarlas a la base de datos
            stmt = connect.prepareStatement("UPDATE `bodega1` SET `no_ventas` = '"+Noventas+"' WHERE `bodega1`.`codigo` = '"+num+"'");
            stmt.executeUpdate();
        }   
        catch(Exception e){

        }
    }
    public void escribirInstrumento_Percusion(Percusion p, Cantidad c)
    {
        openConnection();
        try{

            String id = p.getId();
            double precio = p.getPrecio();
            String marca = p.getMarca();
            String modelo = p.getModelo();
            String nombre = p.getNombre();
            String material = p.getMaterial();
            String percutor = p.getPercutor();
            String vibrante = p.getVibrante();
            double peso = p.getPeso();
            int cantidad = c.getCantidad();
            int noBodega = c.getBodega();

            stmt = connect.prepareStatement("INSERT INTO `producto` (`codigo`, `precio`, `marca`, `modelo`, `nombre`, `tipo_material`, `peso`, `activo`) VALUES ('"+id+"', '"+precio+"','"+marca+"', '"+modelo+"', '"+nombre+"', '"+material+"', '"+peso+"', '1')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("INSERT INTO `instrumento_percusion` (`codigo`, `codigo_producto`, `elemento_percutor`, `elemento_vibrante`) VALUES (NULL, '"+id+"', '"+percutor+"', '"+vibrante+"')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+noBodega+"', '"+id+"', '"+cantidad+"')");
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void escribirInstrumento_Cuerda(Cuerdas p, Cantidad c)
    {
        openConnection();
        try{

            String id = p.getId();
            double precio = p.getPrecio();
            String marca = p.getMarca();
            String modelo = p.getModelo();
            String nombre = p.getNombre();
            String material = p.getMaterial();
            String tipoCuerda = p.getTipoCuerdas();
            int  resonancia = p.getResonancia();
            int noCuerdas = p.getNoCuerdas();
            double peso = p.getPeso();
            int cantidad = c.getCantidad();
            int noBodega = c.getBodega();

            stmt = connect.prepareStatement("INSERT INTO `producto` (`codigo`, `precio`, `marca`, `modelo`, `nombre`, `tipo_material`, `peso`, `activo`) VALUES ('"+id+"','"+precio+"', '"+marca+"', '"+modelo+"', '"+nombre+"', '"+material+"', '"+peso+"', '1')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("INSERT INTO `instrumento_cuerda` (`codigo`, `codigo_producto`, `tipo_cuerda`, `resonancia`, `cantidad_cuerdas`) VALUES (NULL, '"+id+"', '"+tipoCuerda+"', '"+resonancia+"', '"+noCuerdas+"')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+noBodega+"', '"+id+"', '"+cantidad+"')");
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void escribirInstrumento_Viento(Viento v, Cantidad c){
        openConnection();
        try{

            String id = v.getId();
            double precio = v.getPrecio();
            String marca = v.getMarca();
            String modelo = v.getModelo();
            String nombre = v.getNombre();
            String material = v.getMaterial();
            int largo = v.getLargo();
            double peso = v.getPeso();
            int cantidad = c.getCantidad();
            int noBodega = c.getBodega();

            stmt = connect.prepareStatement("INSERT INTO `producto` (`codigo`, `precio`, `marca`, `modelo`, `nombre`, `tipo_material`, `peso`, `activo`) VALUES ('"+id+"','"+precio+"', '"+marca+"', '"+modelo+"', '"+nombre+"', '"+material+"', '"+peso+"', '1')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("INSERT INTO `instrumento_viento` (`codigo`, `codigo_producto`, `largo`) VALUES (NULL, '"+id+"', '"+largo+"')");
            stmt.executeUpdate();
            stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+noBodega+"', '"+id+"', '"+cantidad+"')");
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void actualizarCantidad(Cantidad c, int bar){
        openConnection();
        try{
            if(bar == 1){
                stmt = connect.prepareStatement("UPDATE `cantidad` SET `cantidad` = '"+c.getCantidad()+"' WHERE `cantidad`.`codigo_Producto` = '"+c.getIdProducto()+"' AND `cantidad`.`codigo_bodega` = '"+c.getBodega()+"'");
                stmt.executeUpdate();
            }
            else{
                stmt = connect.prepareStatement("INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES (NULL, '"+c.getBodega()+"', '"+c.getIdProducto()+"', '"+c.getCantidad()+"')");
            stmt.executeUpdate();
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public LinkedList<Cuerdas> cargarIntrumento_cuerdas() //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `instrumento_cuerda` INNER JOIN `producto` ON `instrumento_cuerda`.`codigo_producto` = `producto`.`codigo` WHERE `producto`.`activo` = 1");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                int codigo = rs.getInt("codigo");
                String tipo_cuerda = rs.getString("tipo_cuerda");
                int no_resonancia = rs.getInt("resonancia");
                int cantidad_cuerdas = rs.getInt("cantidad_cuerdas");
                String codigo_producto = rs.getString("codigo_producto");
                Double precio = rs.getDouble("precio");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String nombre = rs.getString("nombre");
                String tipo_material = rs.getString("tipo_material");
                double peso = rs.getDouble("peso");           
                Cuerdas c = new Cuerdas(codigo_producto, precio, marca, modelo, nombre, tipo_material, peso, tipo_cuerda, no_resonancia, cantidad_cuerdas);
                Linked_Instrumentos_cuerdas.add(c);
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return Linked_Instrumentos_cuerdas;
    }  
    

    public LinkedList<Viento> cargarIntrumento_viento() //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `instrumento_viento` INNER JOIN `producto` ON `instrumento_viento`.`codigo_producto` = `producto`.`codigo` WHERE `producto`.`activo` = 1");
            ResultSet rs = stmt.executeQuery(); //Ejecuta las sentencias SQL y retorna un result set (resultado de la consulta)
            while(rs.next())
            {
                int largo = rs.getInt("largo");
                String codigo_producto = rs.getString("codigo_producto");
                Double precio = rs.getDouble("precio");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String nombre = rs.getString("nombre");
                String tipo_material = rs.getString("tipo_material");
                double peso = rs.getDouble("peso");
                Viento v = new Viento(codigo_producto, precio, marca, modelo, nombre, tipo_material, peso, largo);
                Linked_Instrumentos_viento.add(v);
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return Linked_Instrumentos_viento;
    }

    public LinkedList<Percusion> cargarIntrumento_percucion() //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `instrumento_percusion` INNER JOIN `producto` ON `instrumento_percusion`.`codigo_producto` = `producto`.`codigo` WHERE `producto`.`activo` = 1");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String elemento_percutor = rs.getString("elemento_percutor");
                String elemento_vibrante = rs.getString("elemento_vibrante");
                String codigo_producto = rs.getString("codigo_producto");
                Double precio = rs.getDouble("precio");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String nombre = rs.getString("nombre");
                String tipo_material = rs.getString("tipo_material");
                double peso = rs.getDouble("peso");   
                Percusion p = new Percusion(codigo_producto, precio, marca, modelo, nombre, tipo_material, peso, elemento_percutor, elemento_vibrante);
                Linked_Instrumentos_percucion.add(p);
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return Linked_Instrumentos_percucion;
    }

    public LinkedList<Bodega> cargarBodegas() //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `bodega1`");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Bodega bodega = new Bodega(rs.getInt("codigo"), rs.getInt("no_ventas"), rs.getInt("no_compras"));
                String nom = rs.getString("nombre");
                Linked_Bodegas.add(bodega);                
            }
            //crear los objetos bodega
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return Linked_Bodegas;
    }

    public LinkedList<Cantidad> cargarCantidad() //si
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `cantidad` INNER JOIN `producto` ON `cantidad`.`codigo_producto` = `producto`.`codigo` WHERE `producto`.`activo` = 1");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Cantidad cantidades = new Cantidad(rs.getString("codigo_producto"), rs.getInt("cantidad"), rs.getInt("codigo_bodega"));
                Linked_Cantidad.add(cantidades);                
            }
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return Linked_Cantidad;
    }



}

