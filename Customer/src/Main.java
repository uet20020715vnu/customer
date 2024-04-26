import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
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
                "6. Lưu file \n" +
                "7. Đọc thông tin khách hàng từ file \n" +
                "8. Thoát\n" +
                "Chọn: ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            menu();
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    Controller.displayCustomer();
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
                    Controller.saveFile();
                    break;
                case 7:
                    Controller.readFile();
                    break;
                case 8:
                    System.out.println("Thoát");
                    break;
                default:
                    System.out.println("Nhập sai");
                    break;
            }
        }
        while (choose!=8);

    }



}
