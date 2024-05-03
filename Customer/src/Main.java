import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void menu(){
        System.out.print("1. Cho phép xem thông tin danh sách khách hàng\n" +
                "2. Cho phép lưu thông tin khách hàng (có thể nhập n thông tin khách hàng trong 1 lần)\n" +
                "3. Cho phép tìm kiếm thông tin khách hàng theo số điện thoại\n" +
                "4. Cho phép chỉnh sửa thông tin khách hàng (có thể sửa đổi 1 hoặc nhiều thông tin)\n" +
                "5. Cho phép delete thông tin của 1 khách hàng\n" +
                "6. Thoát\n" +
                "Chọn: ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choose;
        Controller.readFile();
        do {
            menu();
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    displayCustomer();
                    break;
                case 2:
                    Controller.inputCustomer();
                    break;
                case 3:
                    Controller.searchbyPhone();
                    break;
                case 4:
                    Controller.editCustomer();
                    break;
                case 5:
                    Controller.deleteCustomer();
                    break;
                case 6:
                    System.out.println("Thoát");
                    break;
                default:
                    System.out.println("Nhập sai");
                    break;
            }
        }
        while (choose!=6);

    }
    public static void menuMini() {
        System.out.println("Sửa đổi : \n" +
                "1. Tên: \n" +
                "2. Email: \n" +
                "3. Số điện thoại: \n" +
                "4. Thoát");
    }

    public static void displayCustomer() {
        System.out.println(String.format("%-20s %-30s %-15s%n", "Name", "Email", "Phone"));;
        for (Map.Entry<String, Customer> entry : Controller.customers.entrySet()) {
            entry.getValue().display();
        }
    }

}
