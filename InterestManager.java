public class InterestManager {
   public static void main(String args[]) {
      InterestManager manager = new InterestManager();
      for(int day=1;day<=365;day+=10){
         System.out.println("amount is " + manager.calculateAmount(day, 1000000));
      }
   }

   public double getInterestRate(int day) {
      if(day <= 90) {
         return 0.005;
      }
      else if(day <= 180) {
         return 0.01;
      }
      else if(day <= 364) {
         return 0.02;
      }
      else {
         return 0.056;
      }
   }

   public double calculateAmount(int day, long amount) {
      return amount + amount * getInterestRate(day);
   }
}