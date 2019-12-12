package controlador;

import dao.Impl.LoginImpl;
import encriptacion.SessionUtils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Login;

@Named(value = "loginC")
@SessionScoped
public class LoginC implements Serializable {

    private boolean admin;
    private boolean users;
    private boolean admin2;
    private boolean admin3;

    private Login usuario = new Login();

    //Variables de Logeo
    private String User;
    private String Pass;
    private String block;
    private int intentos, number;

    public void increment() {
        number++;
        if (number > 5) {
            number = 0;
            intentos = 0;
        }
    }
//Primero compara si el usuario existe si no mandara un error de que noexiste usuario

    public void startSession() throws Exception {
        LoginImpl dao;
        try {
            dao = new LoginImpl();
            usuario = dao.startSession(User, Pass);
            if (usuario != null) {
                intentos = 0;
                block = "PF('bloc').hide()";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", usuario);
                switch (usuario.getNiveProf()) {
//                    en este parte busca el nuvel del usuario segun eso se le rediccionara a una vista segun el usuario
                    case "4":
                        users = false;
                        admin = false;
                        admin2 = false;
                        admin3 = true;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Resultados/ResultadoTempl.xhtml");
                        break;
                    case "3":
                        users = true;
                        admin = false;
                        admin2 = false;
                        admin3 = false;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Nota/NotaTempl.xhtml");

                        break;
                    case "2":
                        users = false;
                        admin2 = false;
                        admin = true;
                        admin3 = false;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Asistencia/AsistenciaTempl.xhtml");
                        break;
                    case "1":
                        users = false;
                        admin = false;
                        admin2 = true;
                        admin3 = false;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Graficos/DashboardTempl.xhtml");
                        break;
                }
            } else {
                setPass(null);
                usuario = new Login();
                intentos++;
                if (intentos == 3) {
                    block = "PF('bloc').show()";
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña/Usuario Incorrecto"));
            }
        } catch (Exception e) {
            System.out.println("Este es el mensaje de error " + e.getMessage());;
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca"); // Mandamos al Login
    }

    public void securityLogin() throws IOException {
        Login us = SessionUtils.obtenerObjetoSesion();
        if (us != null) {
            switch (us.getNiveProf()) {
                case "4":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Resultados/ResultadoTempl.xhtml");
                    break;
                case "3":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Registro/RegistroTempl.xhtml");
                    break;
                case "2":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Asistencia/AsistenciaTempl.xhtml");
                    break;
                case "1":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca/faces/vistas/Graficos/DashboardTempl.xhtml");
                    break;
            }
        }
    }

    public void securitySession() throws IOException {
        if (SessionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Edooca");
        }
    }

    public void obtenerDatos() {
        System.out.println(SessionUtils.ObtenerCodigoSesion());
        System.out.println(SessionUtils.ObtenerNombreSesion());
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isUsers() {
        return users;
    }

    public void setUsers(boolean users) {
        this.users = users;
    }

    public boolean isAdmin2() {
        return admin2;
    }

    public void setAdmin2(boolean admin2) {
        this.admin2 = admin2;
    }

    public Login getUsuario() {
        return usuario;
    }

    public void setUsuario(Login usuario) {
        this.usuario = usuario;
    }

    public String getUser() {
        return User;
    }

    public boolean isAdmin3() {
        return admin3;
    }

    public void setAdmin3(boolean admin3) {
        this.admin3 = admin3;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
