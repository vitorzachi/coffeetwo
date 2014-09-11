package br.com.camtwo.cafe.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@SessionScoped
public class MenuController implements Serializable {
    private static final long serialVersionUID = -3550648562534146952L;
    
    private String menu;
    
    public void changeMenu(ComponentSystemEvent event) {
        try {
            this.menu = event.getComponent().getAttributes().get("menu").toString();
        } catch (NullPointerException e) {
            this.menu = "";
        }
    }
    
    public String getMenu() {
        return menu;
    }
    
    public boolean isSelected(String menu) {
        return menu.equals(this.menu);
    }
}
