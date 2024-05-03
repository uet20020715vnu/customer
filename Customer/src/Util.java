public class Util {

    public static boolean isValidEmail(String email) {
        String regex = "^\\w+@\\w+(\\.\\w{2,}){1,2}$";
        return email.matches(regex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0\\d{9}$");
    }

    public static boolean isValidName(String name) {
        return !name.trim().isEmpty();
    }
}
