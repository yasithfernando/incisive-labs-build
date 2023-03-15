package logics;

public class inputValidation {

    public static boolean inputValidation(Float floatingNumber) {
        try{
        if (floatingNumber <= 0) {
            //todo alert
            System.out.println("number cant be 0");
            return false;
        }
        return true;
    }catch (Exception e){
            System.out.println("Exception"); }
        return false;
    }
}
