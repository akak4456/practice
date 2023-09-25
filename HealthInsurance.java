public enum HealthInsurance {
    LEVEL_ONE(1000,0.01f),
    LEVEL_TWO(2000,0.02f),
    LEVEL_THREE(3000,0.032f),
    LEVEL_FOUR(4000,0.045f),
    LEVEL_FIVE(5000,0.056f),
    LEVEL_SIX(6000,0.071f)
    ;
    
    private final int maxSalary;
    private final float ratio;
    HealthInsurance(int maxSalary, float ratio) {
        this.maxSalary = maxSalary;
        this.ratio = ratio;
    }

    public float getRatio() {
        return this.ratio;
    }

    public int getMaxSalary() {
        return this.maxSalary;
    }

    public static HealthInsurance getHealthInsurance(int salary) {
        for(HealthInsurance insurance : HealthInsurance.values()){
            if(insurance.getMaxSalary() >= salary) return insurance;
        }
        return LEVEL_SIX;
    }

    public static void main(String [] args) {
        int salaryArray[] = new int[]{1500,5500,8000};
        HealthInsurance[] insurances = new HealthInsurance[3];
        insurances[0] = HealthInsurance.getHealthInsurance(salaryArray[0]);
        insurances[1] = HealthInsurance.getHealthInsurance(salaryArray[1]);
        insurances[2] = HealthInsurance.getHealthInsurance(salaryArray[2]);

        for(int loop=0;loop<3;loop++) {
            System.out.println(salaryArray[loop] + "=" + insurances[loop] +"," + insurances[loop].getRatio());
        }
    }
}