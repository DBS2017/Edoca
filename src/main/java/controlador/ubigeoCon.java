package controlador;

import dao.Impl.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named(value = "ubigeoCon")
@SessionScoped
public class ubigeoCon implements Serializable {

    public ubigeoCon() {
    }
        public List<String> completeTextUbi (String query) throws SQLException, Exception{
            UbigeoImpl Conexion = new UbigeoImpl();
        return Conexion.autocompleteUbigeo(query);
    }
}
