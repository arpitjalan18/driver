package driverProj;


public class Driver
{
   String FN, LN, Address, DL, DOB, SEX, HAIR, EYES, HGT, WGT = "";
   public Driver(String FN, String LN, String Address, String DL, String DOB, String SEX, String HAIR, String EYES, String HGT, String WGT){
       this.FN = FN;
       this.LN = LN;
       this.Address = Address;
       this.DL = DL;
       this.DOB = DOB;
       this.SEX = SEX;
       this.HAIR = HAIR;
       this.EYES = EYES;
       this.HGT = HGT;
       this.WGT = WGT;
   }
   public void setFN(String FN){
       this.FN = FN;
   }
   public String toString(){
       return FN + " " + LN + " " + Address + " " + DL + " " + DOB + " " + SEX + " " + HAIR  + " " + EYES + " " + HGT + " " + WGT;
   }
}
