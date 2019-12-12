package encriptacion;

import javax.faces.context.FacesContext;
import modelo.Login;

public class SessionUtils {

    public static Login obtenerObjetoSesion() {
        return (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public static String ObtenerNombreSesion() {
        Login us = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getNomProf();
        } else {
            return null;
        }
    }

    public static String ObtenerCodigoSesion() {
        Login us = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getCodProf();
        } else {
            return null;
        }
    }

}
