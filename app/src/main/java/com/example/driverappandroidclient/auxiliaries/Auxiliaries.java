package com.example.driverappandroidclient.auxiliaries;

public class Auxiliaries {

   public static boolean isNumeric(String strNum) {
      if (strNum == null) {
         return false;
      }
      try {
         double d = Double.parseDouble(strNum);
      } catch (NumberFormatException nfe) {
         return false;
      }
      return true;
   }

   public static boolean isInt(String strNum) {
      if (strNum == null) {
         return false;
      }
      try {
         double d = Integer.parseInt(strNum);
      } catch (NumberFormatException nfe) {
         return false;
      }
      return true;
   }
}
