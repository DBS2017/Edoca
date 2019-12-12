package servicios;

import controlador.ApoderadoC;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validarExistenciaApoderado")
public class ValidarApoderado implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        boolean existe = false;
//        String DNIAPO = value.toString().trim();
//
//        ApoderadoC apoderadoC = new ApoderadoC();
//
//        try {
//
//            if (apoderadoC.validarExistenciaApoderado(DNIAPO)) {
//                existe = true;
//            }
//
//        } catch (Exception e) {
//
//        }
//        if (existe) {
//            FacesMessage msg = new FacesMessage("El número de DNI que ha ingresado ya se encuentra registrado, intente nuevamente");
//            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//            throw new ValidatorException(msg);
//        }
//    }
    }
}
