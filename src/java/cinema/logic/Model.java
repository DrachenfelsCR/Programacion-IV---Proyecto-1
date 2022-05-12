/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.logic;

import cinema.data.FacturaDao;
import cinema.data.PeliculaDao;
import cinema.data.SalaDao;
import cinema.data.TandaDRDAO;
import cinema.data.TandaDao;
import cinema.data.UserDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author tidae
 */
public class Model {
    
    private static Model uniqueInstance;
    
    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }
    
    static Map<String,User> usuarios;
    
    ArrayList<User> users;
    UserDao userDao = new UserDao();
    SalaDao salaDao = new SalaDao();
    TandaDao tandaDao = new TandaDao();
    PeliculaDao peliculaDao = new PeliculaDao();
    ArrayList<Sala> salas;
    FacturaDao facturaDao = new FacturaDao();
    TandaDRDAO tan = new TandaDRDAO();
    
    pdfPrinter pdfp = new pdfPrinter();
    
    private Model(){

        usuarios = new HashMap<>();
        usuarios.put("001", new User("001","001","ADM","Juan Perez"));
        usuarios.put("002", new User("002","002","CLI","Ana Arburola"));  
        users =  userDao.findAll();
        
        
    }
    
    public static User get(User id)throws Exception{
        User result = usuarios.get(id.getId());
        if (result==null) throw new Exception("Usuario no existe");
        return result;
    }
    public  User gets(User id,String a)throws Exception{
        User result = userDao.read(id, a);
        if (result==null) throw new Exception("Usuario no existe");
        return result;
    } 
    
    public  User confirmUser(User u)throws Exception{
        User result = userDao.read(u, u.getClave());
        if (result!=null) throw new Exception("Usuario ya existente");
        String instruccionSql = "insert into Usuario(id,clave,rol,user_name) values('"+u.getId()+"','"+u.getClave()+"','"+u.getRol()+"','"+u.getUserName()+"')";
        userDao.insertUser(instruccionSql);
        return u;
    }
    public Sala RegistarSalaC(Sala s) throws Exception{
       Sala result = salaDao.Confirm(s);
       salaDao.read(s);
        return s;
    }
    public ArrayList<Sala> ListaSalas(){
        ArrayList<Sala> faker;
        faker= salaDao.listaSalas();
        return faker;
       
    }
    
    public void RegistrarTanda(Tanda t,String idAsiento)throws Exception{
        tandaDao.read(t,idAsiento);
    }
    
      public Pelicula RegistarPelicula(Pelicula p) throws Exception{   
        peliculaDao.read(p);
        return p;
    }
      
      
       public boolean confirmButaca(String butaca, String tanda ){
        
        return facturaDao.validarButaca(butaca, tanda);
    }
    
    public void confirmButacas(String butaca, String tanda){
        try{
            if(!confirmButaca(butaca, tanda)){
                throw new Exception("Clave incorrecta");
            }
       
        }catch (Exception ex) {
                throw new NotFoundException();
        }  
        
    }
    
    public void changeButacas(String butaca, String tanda, String idUsuario){
        
        facturaDao.updateButacaEstado(butaca, tanda);
        facturaDao.updateButacaUser(idUsuario, butaca, tanda );
     
        
    }
    
    
    public  void insertFactura(Factura fac, int i)throws Exception{

        String instruccionSql = "insert into Factura(id_factura,id_tanda,name_cliente,id_cliente, id_butaca) values('"+fac.getId()+"','"+fac.getTanda()+"','"+fac.getNameCliente()+"','"+fac.getIdCliente()+"','"+fac.getButacas().get(i)+"')";
        facturaDao.insertFactura(instruccionSql);
       
    }
    
    
    public TandaJS getTandasJS(String idTanda){
        
        ArrayList<Tanda> listTanda = tandaDao.findAll(idTanda);
        TandaJS  tandajs = new TandaJS(listTanda.get(0).getIdTanda(),listTanda.get(0).getIdPelicula(), listTanda.get(0).getIdSala(), listTanda.get(0).getIdPrecio(), listTanda.get(0).getIdHora(),
        listTanda.get(0).getIdFecha(), new ArrayList<Asiento>());
        
        for(int i = 0; i < listTanda.size(); i++){
            tandajs.setAsientos(listTanda.get(i).getIdAsiento(), listTanda.get(i).isEstado());
        }
        
        return tandajs;
        
    }
    
    public ArrayList<Factura> getFacturas(String factId){
        
        
        return facturaDao.findAll(factId);
        
    }
    
    public ArrayList<String> getFacturasId(String idCliente){
        
        
        return facturaDao.findAllForUser(idCliente);
        
    }
    
    public int getFacturasCant(){
        
        
        return facturaDao.findCantFact();
        
    }
    public void insertoLaTandaDeDrachenfels(TandaDR t)throws Exception{
        tan.insertaTandaDR(t);
    }
    
    
    public void imprimirFact(Factura f){
        String d = "C:/AAA/images/Fact#" + f.getId() + ".pdf";
        
        try{
            pdfp.createPdf(d, f, getTandasJS(f.getTanda()));
        }catch(Exception e){
            
        }
        
       
    }
    
     public void imprimirTiquetes(ArrayList<Factura> arrFac){
         for (Factura f : arrFac) 
         {
            String d = "C:/AAA/images/tiquete#" +f.getId()+"-"+f.getButacas().get(0)+ ".pdf";
             TandaJS tandaRecuperada = getTandasJS(f.getTanda());
             
            try{
                pdfp.createPdfTiquetes(d, f, tandaRecuperada);
            }catch(Exception e){
                System.out.println("Fallo :(");
            }
         }
       
    }
    
    public ArrayList<Pelicula> recuperarPelicula(){

        return peliculaDao.listaPeliculas();
    }

    public ArrayList<TandaDR> recuperarTandas(){
        return tan.getTandasDR();
    }
    
    
}

