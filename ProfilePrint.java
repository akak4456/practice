public class ProfilePrint {
   byte age;
   String name;
   boolean isMarried;

   public void setAge(byte age) {
      this.age = age;
   }

   public byte getAge() {
      return age;
   }

   public void setName(String name){
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setMarried(boolean flag) {
      this.isMarried = flag;
   }

   public boolean isMarried() {
      return isMarried;
   }

   public static void main(String args[]) {
      ProfilePrint print = new ProfilePrint();

      print.setAge((byte)26);
      print.setName("JO");
      print.setMarried(false);
      System.out.println(print.getAge() + ":" + print.getName() + ":" + print.isMarried());
   }
}