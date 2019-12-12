/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Impl.HelpImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Help;

/**
 *
 * @author Usuario
 */
@Named(value = "ayudaC")
@SessionScoped
public class AyudaC implements Serializable {

    private Help help = new Help();
    private HelpImpl dao;
    private List<Help> listaHelp;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("error" + e);
        }

    }

    public AyudaC() {
        listaHelp = new ArrayList<>();
    }

    public void listar() throws Exception {
        try {
            dao = new HelpImpl();
            listaHelp = dao.Ayuda();
        } catch (Exception e) {
            throw e;
        }
    }

    public Help getHelp() {
        return help;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

    public HelpImpl getDao() {
        return dao;
    }

    public void setDao(HelpImpl dao) {
        this.dao = dao;
    }

    public List<Help> getListaHelp() {
        return listaHelp;
    }

    public void setListaHelp(List<Help> listaHelp) {
        this.listaHelp = listaHelp;
    }
}
