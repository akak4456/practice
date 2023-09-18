public class CarManager {
   public static void main(String args[]) {
      // 앞으로 여기에 코드가 들어감

      Car dogCar = new Car();
      dogCar.speedUp();
      dogCar.speedUp();
      System.out.println(dogCar.getCurrentSpeed());
      dogCar.breakDown();
      System.out.println(dogCar.getCurrentSpeed());
   }
}