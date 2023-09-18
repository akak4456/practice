public class SalaryManager {
   public static void main(String args[]) {
      SalaryManager manager = new SalaryManager();
      double monthSalary = manager.getMonthlySalary(20000000);
      System.out.println("month salary is " + monthSalary);
   }
   
   public double getMonthlySalary(int yearlySalary) {
      double monthSalary = yearlySalary / 12.0;
      return monthSalary - calculateTax(monthSalary) - calculateNationalPension(monthSalary) - calculateHelthInsurance(monthSalary);
   }

   public double calculateTax(double monthSalary) {
      return monthSalary * 0.125;
   }

   public double calculateNationalPension(double monthSalary) {
      return monthSalary * 0.081;
   }

   public double calculateHelthInsurance(double monthSalary) {
      return monthSalary * 0.135;
   }
}