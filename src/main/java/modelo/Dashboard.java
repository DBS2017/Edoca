package modelo;

import javax.inject.Named;
import javax.enterprise.context.Dependent;


public class Dashboard {

    private String sexest;
    private int sexcount;
   // private String sexo;

    public int getSexcount() {
        return sexcount;
    }

    public void setSexcount(int sexcount) {
        this.sexcount = sexcount;
    } 

    public String getSexest() {
        return sexest;
    }

    public void setSexest(String sexest) {
        this.sexest = sexest;
    }

   // public String getCount() {
   //     return count;
   // }

   // public void setCount(String count) {
   //     this.count = count;
   // }

    //public String getSexo() {
    //    return sexo;
   // }

   // public void setSexo(String sexo) {
   //     this.sexo = sexo;
   // }
    
  //  public Dashboard() {
  //  }
    
}
