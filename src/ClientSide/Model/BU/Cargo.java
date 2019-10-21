/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide.Model.BU;

import DAO.DAOCargo;

/**
 *
 * @author adston
 */
public class Cargo {
    private int cd_cargo_pergunta;
    private String ds_cargo_pergunta;

    public Cargo() {
    }
    
    public Cargo(int cd_cargo_pergunta, String ds_cargo_pergunta) {
        this.cd_cargo_pergunta = cd_cargo_pergunta;
        this.ds_cargo_pergunta = ds_cargo_pergunta;
    }


    public int getCd_cargo_pergunta() {
        return cd_cargo_pergunta;
    }

    public void setCd_cargo_pergunta(int cd_cargo_pergunta) {
        this.cd_cargo_pergunta = cd_cargo_pergunta;
    }

    public String getDs_cargo_pergunta() {
        return ds_cargo_pergunta;
    }

    public void setDs_cargo_pergunta(String ds_cargo_pergunta) {
        this.ds_cargo_pergunta = ds_cargo_pergunta;
    }

    public boolean saveMe(){
       return DAOCargo.saveCargo(this);
    }
        
    
}
