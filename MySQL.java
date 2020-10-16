import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySQL {
    public final String DB = "proyectooop";
    public final String URL = "jdbc:mysql://localhost:3306/" + DB;
    public final String USER = "root";
    public final String PASS = "";
    public Connection connect = null;
    PreparedStatement stmt;
    public String stringuso = "";
    public String[] productos = new String[10];
    public LinkedList<String[]> Linked_Productos = new LinkedList<String[]>();
    public LinkedList<String> Linked_Bodegas = new LinkedList<String>();
    public String[] bodegas = new String[3];
    public LinkedList<String> Linked_Instrumentos_cuerdas = new LinkedList<String>();
    public String[] instrumentos_cuerdas = new String[4];
    public LinkedList<String> Linked_Instrumentos_viento = new LinkedList<String>();
    public String[] instrumentos_viento = new String[2];
    public LinkedList<String> Linked_Instrumentos_percucion = new LinkedList<String>();
    public String[] instrumentos_percucion = new String[3];

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
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
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return condicion;
    }

    public void crear_bodega(String nombre)
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

    public void crear_producto(String codigo, int no_bodega, double precio, String marca, String modelo, String nombre, String tipo_material, int peso, int cantidad)
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("INSERT INTO `producto` (`codigo`, `codigo_bodega`, `precio`, `marca`, `modelo`, `nombre`, `tipo_material`, `peso`, `cantidad`) VALUES ('"+ codigo +"', '"+ no_bodega +"', '"+ precio +"', '"+ marca +"', '"+ modelo +"', '" + nombre +"', '" + tipo_material +"', '" + peso +"', '" + cantidad +"')");
        stmt.executeUpdate();
        stmt = connect.prepareStatement("INSERT INTO `activo` (`codigo`, `codigo_producto`, `no.activo`) VALUES (NULL, '"+ codigo +"', '1')");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void crear_instrumento_cuerda(String codigo_producto, String tipo_cuerda, int cantidad_cuerda, int no_resonancia)
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

    public void crear_instrumento_percusion(String codigo_producto, String elemento_percutor, String elemento_vibrante)
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

    public void crear_instrumento_viento(String codigo_producto, String largo)
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

    public void eliminar_producto(String codigo_producto)
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `activo` SET `no.activo` = '0' WHERE `activo`.`codigo_producto` = "+codigo_producto+"");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public void setProducto_precio(String codigo , double precio)
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

    public void setProducto_marca(String codigo , String marca)
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

    public void setProducto_modelo(String codigo , String modelo)
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

    public void setProducto_nombre(String codigo , String nombre)
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

    public void setProducto_tipo_material(String codigo , String tipo_material)
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

    public void setProducto_peso(String codigo , int peso)
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

    public void setProducto_cantidad(String codigo , int cantidad)
    {
        openConnection();
        try{
        stmt = connect.prepareStatement("UPDATE `producto` SET `cantidad` = '"+cantidad+"' WHERE `producto`.`codigo` = '"+codigo+"'");
        stmt.executeUpdate();
        stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);
    }

    public Double getProducto_precio(String codigo)
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

    public String getProducto_marca(String codigo)
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

    public String getProducto_modelo(String codigo)
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

    public String getProducto_nombre(String codigo)
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

    public String getProducto_tipo_material(String codigo)
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

    public int getProducto_peso(String codigo)
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

    public int getProducto_cantidad(String codigo)
    {
        int cantidad = 0;
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `cantidad` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cantidad = rs.getInt("cantidad");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return cantidad;
    }

    public int getProducto_codigo_bodega(String codigo)
    {
        int bodega = 0;
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT `codigo_bodega` FROM `producto` WHERE `producto`.`codigo` = '"+codigo+"'");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            bodega = rs.getInt("codigo_bodega");
            stmt.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection(connect);

        return bodega;
    }

    public LinkedList<String[]> cargarProductos() //hacer la funcion cargar productos
    {
        openConnection();
        try{
            stmt = connect.prepareStatement("SELECT * FROM `producto` INNER JOIN `activo` ON `producto`.`codigo` = `activo`.`codigo_producto` WHERE `activo`.`no_activo` = 1");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                productos[0] = rs.getString("codigo");
                productos[1] = String.valueOf(rs.getInt("codigo_bodega"));
                productos[2] = String.valueOf(rs.getInt("precio"));
                productos[3] = rs.getString("marca");
                productos[4] = rs.getString("modelo");
                productos[5] = rs.getString("nombre");
                productos[6] = rs.getString("tipo_material");
                productos[7] = String.valueOf(rs.getInt("peso"));
                productos[8] = String.valueOf(rs.getString("cantidad"));
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




}
